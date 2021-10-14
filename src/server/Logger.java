package server;

import common.LoggerInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Logger implements LoggerInterface {

    @Override
    public String[] retrieveLogs(String type) {
        String[] lines = new String[0];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(type + ".txt"));
            lines = new String[(int) reader.lines().count()];
            for(int i = 0; i < reader.lines().count(); i++){
                lines[i] = reader.readLine();
            }
        } catch (Exception e){
            System.err.println("EXCEPTION");
        }
        return lines;
    }
}
