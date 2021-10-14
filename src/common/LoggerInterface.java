package common;

import java.rmi.Remote;

public interface LoggerInterface extends Remote {
    public String[] retrieveLogs(String type);
}
