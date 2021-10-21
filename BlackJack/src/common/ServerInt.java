package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInt extends Remote {
    public void register(ClientInt client) throws RemoteException, GameIsFullException;
    public int get_player_number() throws RemoteException;
    void play_game() throws RemoteException;
}
