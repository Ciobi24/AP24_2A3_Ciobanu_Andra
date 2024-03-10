package lab2;

import java.time.LocalTime;
import java.util.Objects;

/**
 * Client class includes the name, start and end interval and the type of the client
 */
public class Client {
    public enum Type {
        PREMIUM,
        REGULAR
    }

    private String name;
    private LocalTime startInterval;
    private LocalTime endInterval;
    private Type clientType;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
        this.startInterval = null;
        this.endInterval = null;
    }

    public Client(String name, LocalTime startInterval, LocalTime endInterval, Type clientType) {
        this.name = name;
        this.startInterval = startInterval;
        this.endInterval = endInterval;
        this.clientType = clientType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartInterval(LocalTime startInterval) {
        this.startInterval = startInterval;
    }

    public void setEndInterval(LocalTime endInterval) {
        this.endInterval = endInterval;
    }

    public void setClientType(Type clientType) {
        this.clientType = clientType;
    }

    public String getName() {
        return name;
    }

    public LocalTime getStartInterval() {
        return startInterval;
    }

    public LocalTime getEndInterval() {
        return endInterval;
    }

    public Type getClientType() {
        return clientType;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", startInterval=" + startInterval +
                ", endInterval=" + endInterval +
                ", clientType=" + clientType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Client)) return false;
        Client other = (Client) o;
        return name.equals(other.name) && startInterval.equals(other.startInterval) && endInterval.equals(other.endInterval);
    }

}
