package client;

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
        float response = operator.add(5, 7);
        System.out.println("5 + 7 = " + response);
        response = operator.substract(5, 7);
        System.out.println("5 - 7 = " + response);
        response = operator.multiply(5, 7);
        System.out.println("5 * 7 = " + response);
        response = operator.divide(30, 7);
        System.out.println("30 / 7 = " + response);
    }
}
