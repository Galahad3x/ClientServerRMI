package client;

import common.Card;
import common.ClientInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ClientImpl extends UnicastRemoteObject implements ClientInt {

    List<Card> hand = new LinkedList<>();
    boolean status = true;

    protected ClientImpl() throws RemoteException {
    }

    @Override
    public boolean make_a_move() throws RemoteException {
        if(!status){
            System.out.println("Already standed, skipping turn");
            return false;
        }
        Scanner scanner = new Scanner(System.in);
        String read;
        while (true) {
            System.out.println("Do you want to play or stand? [p/s]");
            read = scanner.nextLine();
            if (read.equals("p")){
                return true;
            }else if (read.equals("s")){
                return false;
            }
        }
    }

    @Override
    public void give_card(Card card) throws RemoteException {
        hand.add(card);
        int sum = 0;
        for (Card crd : hand){
            sum += crd.number;
        }
        if (sum >= 21){
            System.out.println("You got too much boi");
            status = false;
        }else{
            System.out.println("You currently have " + sum);
        }
    }

    @Override
    public void send_result(String result) throws RemoteException {
        System.out.println(result);
    }

    @Override
    public List<Card> get_hand() throws RemoteException {
        return hand;
    }
}
