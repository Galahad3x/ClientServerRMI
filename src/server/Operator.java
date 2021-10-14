package server;

import common.OperatorInterface;

import java.io.FileWriter;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class Operator implements OperatorInterface {

    private final Object add_bl = new Object();
    private final Object sub_bl = new Object();
    private final Object mul_bl = new Object();
    private final Object div_bl = new Object();

    @Override
    public float add(float a, float b) throws RemoteException {
        try {
            synchronized (add_bl){
                FileWriter writer = new FileWriter("add.txt", true);
                writer.write(LocalDateTime.now() + "//" + a + " + " + b + " = " + (a+b) + "\n");
                writer.close();
            }
        } catch (Exception e){
            System.err.println("EXCEPTION");
        }
        return a + b;
    }

    @Override
    public float substract(float a, float b) throws RemoteException {
        try {
            synchronized (sub_bl){
                FileWriter writer = new FileWriter("substract.txt", true);
                writer.write(LocalDateTime.now() + "//" + a + " - " + b + " = " + (a-b) + "\n");
                writer.close();
            }
        } catch (Exception e){
            System.err.println("EXCEPTION");
        }
        return a - b;
    }

    @Override
    public float multiply(float a, float b) throws RemoteException {
        try {
            synchronized (mul_bl){
                FileWriter writer = new FileWriter("multiply.txt", true);
                writer.write(LocalDateTime.now() + "//" + a + " * " + b + " = " + (a*b) + "\n");
                writer.close();
            }
        } catch (Exception e){
            System.err.println("EXCEPTION");
        }
        return a * b;
    }

    @Override
    public float divide(float a, float b) throws RemoteException {
        try {
            synchronized (add_bl){
                FileWriter writer = new FileWriter("divide.txt", true);
                writer.write(LocalDateTime.now() + "//" + a + " / " + b + " = " + (a/b) + "\n");
                writer.close();
            }
        } catch (Exception e){
            System.err.println("EXCEPTION");
        }
        return a / b;
    }
}
