package server;

import common.ClientInt;
import common.ServerInt;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    private static Registry startRegistry(Integer port)
            throws RemoteException {
        if(port == null) {
            port = 1099;
        }
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            registry.list( );
            // The above call will throw an exception
            // if the registry does not already exist
            return registry;
        }
        catch (RemoteException ex) {
            // No valid registry at that port.
            System.out.println("RMI registry cannot be located ");
            Registry registry= LocateRegistry.createRegistry(port);
            System.out.println("RMI registry created at port " + port);
            return registry;
        }
    }

    public static void main(String[] args) throws RemoteException, InterruptedException {
        Registry registry = startRegistry(null);
        ServerInt server = new ServerImpl();
        registry.rebind("server", server);
        while (true) {
            System.out.println("Server is available");
            synchronized (server) {
                while (server.get_player_number() < ServerImpl.NUM_PLAYERS) {
                    System.out.println("There are " + server.get_player_number() + " players connected");
                    server.wait();
                }
            }
            System.out.println("Game is starting");
            server.play_game();
            System.out.println("Game is over, new game");
        }
    }
}
