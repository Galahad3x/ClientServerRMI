package client;

import common.ClientInt;
import common.ServerInt;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(null);
        ServerInt server = (ServerInt) registry.lookup("server");
        ClientInt client = new ClientImp();
        server.register(client, "Joel");
    }
}
