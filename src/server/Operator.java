package server;

import common.OperatorInterface;

import java.rmi.RemoteException;

public class Operator implements OperatorInterface {
    @Override
    public float add(float a, float b) throws RemoteException {
        return a + b;
    }

    @Override
    public float substract(float a, float b) throws RemoteException {
        return a - b;
    }

    @Override
    public float multiply(float a, float b) throws RemoteException {
        return a * b;
    }

    @Override
    public float divide(float a, float b) throws RemoteException {
        return a / b;
    }
}
