package client;

import common.ClientInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClientImp extends UnicastRemoteObject implements ClientInt{
    public ClientImp() throws RemoteException {}

    @Override
    public int ask_for_number(int min, int max) throws RemoteException {
        int number;
        do {
            System.out.println("The server asks for a number between " + min + " and " + max + ": ");
            Scanner scanner = new Scanner(System.in);
            String number_string = scanner.nextLine();
            number = Integer.parseInt(number_string);
        } while (number < min || number > max);
        return number;
    }

    @Override
    public void notify_result(String result) {
        System.out.println("You are a " + result + "!!1");
    }
}
