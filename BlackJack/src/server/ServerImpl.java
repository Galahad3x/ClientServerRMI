package server;

import client.Client;
import common.Card;
import common.ClientInt;
import common.GameIsFullException;
import common.ServerInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ServerImpl extends UnicastRemoteObject implements ServerInt {

    static int NUM_PLAYERS = 4;
    List<Card> queue = new LinkedList<>();
    Queue<ClientInt> players = new LinkedList<>();
    Queue<String> names = new LinkedList<>();
    Queue<Boolean> statuses = new LinkedList<>();

    private void new_game() {
        players = new LinkedList<>();
        names = new LinkedList<>();
        statuses = new LinkedList<>();
        queue = new LinkedList<>();
        String[] suits = new String[]{"SWORDS", "COUPS", "COINS", "CLUBS"};
        for(String suit : suits){
            for (int i = 1; i <= 12; i++){
                queue.add(new Card(i, suit));
            }
        }
        Collections.shuffle(queue);
    }

    protected ServerImpl() throws RemoteException {
        new_game();
    }

    @Override
    public void register(ClientInt client, String name) throws RemoteException, GameIsFullException {
        synchronized (this){
            if (players.size() < NUM_PLAYERS){
                players.add(client);
                names.add(name);
                statuses.add(true);
                System.out.println("Player " + name + " joined the table");
                this.notify();
            }else{
                throw new GameIsFullException();
            }
        }
    }

    public int get_player_number(){
        return players.size();
    }

    private boolean should_game_end(){
        return !statuses.contains(true);
    }

    @Override
    public void play_game() throws RemoteException {
        try {
            //First cards
            for(int i = 0; i < NUM_PLAYERS; i++){
                ClientInt player = players.poll();
                String name = names.poll();
                System.out.println("Player " + name + " receives its first card");
                assert player != null;
                player.give_card(queue.get(0));
                queue.remove(0);
                players.add(player);
                names.add(name);
            }
            while (!should_game_end()) {
                ClientInt actual_player = players.poll();
                String actual_name = names.poll();
                Boolean stat = statuses.poll();
                assert stat != null;
                if(stat) {
                    System.out.println("Player " + actual_name + " is thinking... ");
                    assert actual_player != null;
                    if(actual_player.make_a_move()){
                        actual_player.give_card(queue.get(0));
                        System.out.println("Player " + actual_name + " drew a card");
                        queue.remove(0);
                        statuses.add(true);
                    }else{
                        System.out.println("Player " + actual_name + " stands");
                        statuses.add(false);
                    }
                }else{
                    System.out.println("Player " + actual_name + " is not actually playing right now, skipping...");
                    statuses.add(false);
                }
                players.add(actual_player);
                names.add(actual_name);
            }
            String winner = "The House Always Wins";
            int current_diff = 21;
            for (int i = 0; i < NUM_PLAYERS; i++){
                ClientInt player = players.poll();
                String name = names.poll();
                int sum = 0;
                assert player != null;
                for(Card crd : player.get_hand()){
                    sum += crd.number;
                }
                System.out.println("Player: " + name + ": " + sum);
                if (sum <= 21){
                    if((21 - sum) < current_diff){
                        winner = name + " is the winner";
                        current_diff = 21 - sum;
                    }
                }
                players.add(player);
            }
            System.out.println("Result: " + winner);
            for (int i = 0; i < NUM_PLAYERS; i++) {
                ClientInt player = players.poll();
                assert player != null;
                player.send_result(winner);
            }
            new_game();
        } catch (Exception e){
            new_game();
        }
    }
}
