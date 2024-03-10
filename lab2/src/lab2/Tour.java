package lab2;

import java.time.LocalTime;
import java.util.Arrays;

/**
 * Tour class represents a tour of a vehicle
 * A tour has a vehicle and a list of clients that are visited between the start and end time of their chosen interval, and the arrival time of the vehicle at each client
 */
public class Tour {
    private Vehicle vehicle;
    private Client[] clients;

    private LocalTime time;
    private LocalTime[] arrivalTime;

    public Tour() {
        this.time = LocalTime.of(7, 0);
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Set the clients of the tour.
     * Make sure that the clients are unique.
     * @param clients
     */
    public void setClients(Client... clients) {
        Client[] clients1 = new Client[clients.length];
        int i = 0;
        for (int j = 0; j < clients.length; j++) {
            boolean isDuplicate = false;
            for (int k = j + 1; k < clients.length; k++) {
                if (clients[j] != null && clients[j].equals(clients[k])) {
                    isDuplicate = true;
                }
            }
            if (!isDuplicate && clients[j] != null) {
                clients1[i++] = new Client(clients[j].getName(), clients[j].getStartInterval(), clients[j].getEndInterval(), clients[j].getClientType());
            }
        }
        this.clients = Arrays.copyOf(clients1, i);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Client[] getClients() {
        return clients;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalTime[] getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime[] arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Add an arrival time to the list of arrival times
     * @param arrivalTime
     */
    public void addArrivalTime(LocalTime arrivalTime) {
        if (this.arrivalTime == null) {
            this.arrivalTime = new LocalTime[1];
            this.arrivalTime[0] = arrivalTime;
        } else {
            LocalTime[] arrivalTimes = new LocalTime[this.arrivalTime.length + 1];
            for (int i = 0; i < this.arrivalTime.length; i++) {
                arrivalTimes[i] = this.arrivalTime[i];
            }
            arrivalTimes[this.arrivalTime.length] = arrivalTime;
            this.arrivalTime = arrivalTimes;
        }
    }

    /**
     * Add a client to the list of clients
     * Make sure that the client is unique
     * @param client
     * @return
     */
    public int addClient(Client client) {
        if (clients == null) {
            clients = new Client[1];
            clients[0] = client;
            return 1;
        }
        Client[] clients1 = new Client[this.clients.length + 1];
        boolean isDuplicate = false;
        for (int j = 0; j < clients.length; j++) {
            if (clients[j] != null && clients[j].equals(client)) {
                isDuplicate = true;
                break;
            }
        }

        if (!isDuplicate) {
            for (int j = 0; j < clients.length; j++) {
                clients1[j] = new Client(clients[j].getName(), clients[j].getStartInterval(), clients[j].getEndInterval(), clients[j].getClientType());
            }
            clients1[clients.length] = new Client(client.getName(), client.getStartInterval(), client.getEndInterval(), client.getClientType());
            this.clients = Arrays.copyOf(clients1, clients1.length);
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder("Tour{" +
                "vehicle=" + vehicle.getName() +
                ", clients=");
        if (clients != null) {
            for (Client client : clients) {
                if (client != null) {
                    toReturn.append(client.getName());
                    toReturn.append(" interval= [ ");
                    toReturn.append(client.getStartInterval()).append(" - ").append(client.getEndInterval()).append(" ], ");
                }
            }
            toReturn.append(", arrivalTimes=").append(Arrays.toString(arrivalTime));
            toReturn.append('}');
        }
        return toReturn.toString();
    }
}
