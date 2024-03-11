package lab2;

import java.util.Arrays;

/**
 * The Problem class is used to store the problem data.
 * Vehicle Routing Problem
 */
public class Problem {
    private Depot[] depots;
    private Client[] clients;
    private int[][] distanceMatrix;

    public Problem() {
    }

    /**
     * Set the depots of the problem.
     * Make sure that the depots are unique.
     *
     * @param depots
     */
    public void setDepots(Depot... depots) {
        Depot[] depots1 = new Depot[depots.length];
        int i = 0;
        for (int j = 0; j < depots.length; j++) {
            boolean isDuplicate = false;
            for (int k = j + 1; k < depots.length; k++) {
                if (depots[j].equals(depots[k])) {
                    isDuplicate = true;
                }
            }
            if (!isDuplicate) {
                depots1[i] = new Depot(depots[j].getName());
                depots1[i++].setVehicles(depots[j].getVehicles());
            }
        }
        this.depots = Arrays.copyOf(depots1, i);
    }

    /**
     * Set the clients of the problem.
     * Make sure that the clients are unique.
     *
     * @param clients
     */
    public void setClients(Client... clients) {
        Client[] clients1 = new Client[clients.length];
        int i = 0;
        for (int j = 0; j < clients.length; j++) {
            boolean isDuplicate = false;
            for (int k = j + 1; k < clients.length; k++) {
                if (clients[j].equals(clients[k])) {
                    isDuplicate = true;
                }
            }
            if (!isDuplicate) {
                clients1[i++] = new Client(clients[j].getName(), clients[j].getStartInterval(), clients[j].getEndInterval(), clients[j].getClientType());
            }
        }
        this.clients = Arrays.copyOf(clients1, i);
    }


    public Depot[] getDepots() {
        return depots;
    }

    public Client[] getClients() {
        return clients;
    }

    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public void setDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }
}
