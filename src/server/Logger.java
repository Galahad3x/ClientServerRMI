package server;

import common.LoggerInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Logger implements LoggerInterface {

    @Override
    public List<String> retrieveLogs(String type) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(type + ".txt"));
            String line;
            while ((line = reader.readLine()) != null){
                lines.add(line);
            }
        } catch (Exception e){
            System.err.println("EXCEPTION");
        }
        return lines;
    }
}
