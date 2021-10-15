package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LoggerInterface extends Remote {
    List<String> retrieveLogs(String type) throws RemoteException;
}
