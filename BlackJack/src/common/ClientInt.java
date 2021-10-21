package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientInt extends Remote {
    public boolean make_a_move() throws RemoteException;
    void give_card(Card card) throws RemoteException;
    void send_result(String result) throws RemoteException;
    List<Card> get_hand() throws RemoteException;
}
