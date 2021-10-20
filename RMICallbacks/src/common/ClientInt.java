package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInt extends Remote {
    int ask_for_number(int min, int max) throws RemoteException;
    void notify_result(String result) throws RemoteException;
}
