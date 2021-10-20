package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInt extends Remote {
    void register(ClientInt client, String name) throws RemoteException;
}
