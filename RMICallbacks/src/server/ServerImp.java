package server;

import common.ClientInt;
import common.ServerInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Random;

public class ServerImp extends UnicastRemoteObject implements ServerInt {
    int min = 1, max = 5000;

    HashMap<ClientInt, Integer> saved_ints = new HashMap<>();

    public ServerImp() throws RemoteException {}

    @Override
    public void register(ClientInt client, String name) throws RemoteException {
        saved_ints.put(client, new Random().nextInt(max - min) + min);
        System.out.println(saved_ints.get(client));
        int client_number = client.ask_for_number(min, max);
        if (saved_ints.get(client) == client_number){
            client.notify_result("winner");
        }else{
            client.notify_result("loser");
        }
    }
}
