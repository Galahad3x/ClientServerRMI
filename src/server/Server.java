package server;

import common.LoggerInterface;
import common.OperatorInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.getRegistry(null);
        Operator operator = new Operator();
        Logger logger = new Logger();
        OperatorInterface remote_operator = (OperatorInterface) UnicastRemoteObject.exportObject(operator, 0);
        LoggerInterface logger_remote = (LoggerInterface) UnicastRemoteObject.exportObject(logger, 0);
        System.out.println("List before: ");
        System.out.println(Arrays.toString(registry.list()));
        registry.rebind("Operator", remote_operator);
        registry.rebind("Logger", logger_remote);
        System.out.println("List after: ");
        System.out.println(Arrays.toString(registry.list()));
        System.out.println("Server is ready");
    }
}
