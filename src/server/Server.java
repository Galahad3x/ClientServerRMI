package server;

import common.OperatorInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.getRegistry(null);
        Operator operator = new Operator();
        OperatorInterface remote_operator = (OperatorInterface) UnicastRemoteObject.exportObject(operator, 0);
        registry.bind("Operator", remote_operator);
        System.out.println("Server is ready");
    }
}
