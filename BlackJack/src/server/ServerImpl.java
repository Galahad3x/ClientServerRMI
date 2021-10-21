package server;

import common.Card;
import common.ClientInt;
import common.GameIsFullException;
import common.ServerInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ServerImpl extends UnicastRemoteObject implements ServerInt {

    List<Card> queue = new LinkedList<>();
    Queue<ClientInt> players = new LinkedList<>();
    Queue<Boolean> statuses = new LinkedList<>();

    protected ServerImpl() throws RemoteException {
        String[] suits = new String[]{"SWORDS", "COUPS", "COINS", "CLUBS"};
        for(String suit : suits){
            for (int i = 1; i <= 12; i++){
                queue.add(new Card(i, suit));
            }
        }
        Collections.shuffle(queue);
    }

    @Override
    public void register(ClientInt client) throws RemoteException, GameIsFullException {
        synchronized (this){
            if (players.size() < 2){
                players.add(client);
                statuses.add(true);
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
            while (!should_game_end()) {
                ClientInt actual_player = players.poll();
                Boolean stat = statuses.poll();
                assert stat != null;
                if(stat) {
                    assert actual_player != null;
                    if(actual_player.make_a_move()){
                        actual_player.give_card(queue.get(0));
                        queue.remove(0);
                    }else{
                        statuses.add(false);
                    }
                    players.add(actual_player);
                }
            }
            for (ClientInt player : players){
                List<Card> hand = player.get_hand();
                int sum = 0;
                for (Card crd : hand){
                    sum += crd.number;
                }
                System.out.println("Sum is " + sum);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
