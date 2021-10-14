package client;

import common.LoggerInterface;
import common.OperatorInterface;
import server.Operator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(null);
        OperatorInterface operator = (OperatorInterface) registry.lookup("Operator");
        float response = operator.add(6, 10);
        System.out.println("5 + 7 = " + response);
        response = operator.substract(5, 7);
        System.out.println("5 - 7 = " + response);
        response = operator.multiply(9, 6);
        System.out.println("5 * 7 = " + response);
        response = operator.divide(50, 5);
        System.out.println("30 / 7 = " + response);
        run();
    }

    public static void run() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(null);
        LoggerInterface logger = (LoggerInterface) registry.lookup("Logger");
        for(String line : logger.retrieveLogs("add")){
            System.out.println(line);
        }
    }
}
