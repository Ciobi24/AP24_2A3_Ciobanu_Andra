package lab2;

import java.time.LocalTime;
import java.util.Random;

/**
 * The Solution class represents the solution to the problem.
 * It contains a list of tours, each tour representing a vehicle's route.
 * A tour has only one vehicle and a list of clients that are visited between the start and end time of their chosen interval.
 */
public class Solution {
    private Problem problem;

    public Solution(Problem problem) {
        this.problem = problem;
    }

    public Problem getProblem() {
        return problem;
    }

    private static Tour[] tours;

    /**
     * This methode implements a greedy algorithm to solve the problem.
     * The distances between the clients are generated randomly.
     * Each step, the algorithm chooses the closest client to the current client or depot.
     * Each tour ends when there are no more clients to visit.
     */
    public void solveHomework() {
        tours = new Tour[100];
        int i = 0;
        //incep nou tur, caut depou
        for (Depot depot : problem.getDepots()) {
            if (depot != null) {
                //am gasit un depou, caut vehicul
                for (Vehicle vehicle : depot.getVehicles()) {
                    boolean alreadyInTour = false;
                    //verific sa nu aiba deja tur
                    for (Tour tour : tours) {
                        if (vehicle != null && tour != null) {
                            if (vehicle.equals(tour.getVehicle())) {
                                alreadyInTour = true;
                                break;
                            }
                        }
                    }
                    if (!alreadyInTour) {
                        //vehiculul nu are tur, il adaug
                        Client closestClient = null;
                        tours[i] = new Tour();
                        int clientDistance;
                        tours[i].setVehicle(vehicle);
                        boolean notFinished = true;
                        LocalTime minTime = LocalTime.of(7, 0);
                        //incep turul, caut clienti care sa nu fie vizitati in niciun tur
                        while (notFinished && minTime.isBefore(LocalTime.of(22, 0))) {
                            minTime = LocalTime.of(22, 0);
                            notFinished = false;
                            for (Client client : problem.getClients()) {
                                boolean isVisited = false;
                                for (Tour tour1 : tours) {
                                    if (tour1 != null && tour1.getClients() != null && !isVisited) {
                                        for (Client client1 : tour1.getClients()) {
                                            if (client != null && client.equals(client1)) {
                                                isVisited = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (!isVisited && client != null) {
                                    //am clienti nevizitati inca, caut cel mai apropiat care imi da cel mai bun timp
                                    notFinished = true;
                                    Random rand = new Random();
                                    clientDistance = rand.nextInt(120) + 1;
                                    LocalTime newTime = tours[i].getTime().plusMinutes(clientDistance);
                                    if (newTime.isBefore(client.getEndInterval())) {
                                        if (newTime.isBefore(client.getStartInterval())) {
                                            newTime = client.getStartInterval();
                                        }
                                    }
                                    if (newTime.isBefore(minTime)) {
                                        minTime = newTime;
                                        closestClient = client;
                                    }
                                }
                            }

                            if (notFinished && tours[i].addClient(closestClient) == 1) {
                                tours[i].setTime(minTime);
                                tours[i].addArrivalTime(minTime);
                            }
                        }
                        if (i + 1 < 100) {
                            i++;
                        } else {
                            break;
                        }
                    }

                }
            }
        }


//afisez tururile
        for (Tour tour : tours) {
            if (tour != null) {
                Client[] clients = tour.getClients();
                if (clients != null && clients.length > 0) {
                    System.out.println(tour);
                }
            }
        }
    }

    public int getDistanceDepot(Depot depot, Client client) {
        int indexDepot = 0, indexClient = problem.getDepots().length;
        for (Depot depot1 : problem.getDepots()) {
            if (depot1.equals(depot)) {
                break;
            }
            indexDepot++;
        }
        for (Client client1 : problem.getClients()) {
            if (client1.equals(client)) {
                break;
            }
            indexClient++;
        }
        return problem.getDistanceMatrix()[indexDepot][indexClient];
    }

    public int getDistanceClients(Client client1, Client client2) {
        int indexClient1 = problem.getDepots().length, indexClient2 = problem.getDepots().length;
        for (Client client : problem.getClients()) {
            if (client.equals(client1)) {
                break;
            }
            indexClient1++;
        }
        for (Client client : problem.getClients()) {
            if (client.equals(client2)) {
                break;
            }
            indexClient2++;
        }
        return problem.getDistanceMatrix()[indexClient1][indexClient2];
    }

    /**
     * This method implements a greedy algorithm to solve the bonus problem.
     * The distances are known (in the matrixCost), and the algorithm chooses the closest client to the current client or depot.
     * Each tour starts from a depot at 7:00, gets to the clients if they can be visited in their interval, and returns to the depot, using the shortest path.
     */
    public void solveBonus() {
        tours = new Tour[100];
        int i = 0;
        //incep nou tur, caut depou
        for (Depot depot : problem.getDepots()) {
            if (depot != null) {
                //am gasit un depou, caut vehicul
                for (Vehicle vehicle : depot.getVehicles()) {
                    boolean alreadyInTour = false;
                    //verific sa nu aiba deja tur
                    for (Tour tour : tours) {
                        if (vehicle != null && tour != null) {
                            if (vehicle.equals(tour.getVehicle())) {
                                alreadyInTour = true;
                                break;
                            }
                        }
                    }
                    if (!alreadyInTour) {
                        //vehiculul nu are tur, il adaug
                        Client closestClient = null;
                        System.out.println(1);
                        tours[i] = new Tour();
                        int clientDistance;
                        tours[i].setVehicle(vehicle);
                        boolean notFinished = true;
                        LocalTime minTime = LocalTime.of(7, 0);
                        //incep turul, caut clienti care sa nu fie vizitati in niciun tur
                        while (notFinished && minTime.isBefore(LocalTime.of(22, 0))) {
                            minTime = LocalTime.of(22, 0);
                            notFinished = false;
                            for (Client client : problem.getClients()) {
                                boolean isVisited = false;
                                for (Tour tour1 : tours) {
                                    if (tour1 != null && tour1.getClients() != null && !isVisited) {
                                        for (Client client1 : tour1.getClients()) {
                                            if (client != null && client.equals(client1)) {
                                                isVisited = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (!isVisited && client != null) {
                                    //am clienti nevizitati inca, caut cel mai apropiat care imi da cel mai bun timp
                                    notFinished = true;
                                    if (tours[i].getClients() == null) {
                                        clientDistance = getDistanceDepot(depot, client);
                                    } else {
                                        clientDistance = getDistanceClients(tours[i].getClients()[tours[i].getClients().length - 1], client);
                                    }
                                    if (clientDistance != 0) {
                                        LocalTime newTime = tours[i].getTime().plusMinutes(clientDistance);
                                        if (newTime.isBefore(client.getEndInterval())) {
                                            if (newTime.isBefore(client.getStartInterval())) {
                                                newTime = client.getStartInterval();
                                            }
                                        }
                                        if (newTime.isBefore(minTime)) {
                                            minTime = newTime;
                                            closestClient = client;
                                        }
                                    }
                                }
                            }
                            System.out.println(2);
                            if (notFinished && tours[i].addClient(closestClient) == 1 && minTime.isBefore(LocalTime.of(22, 0))) {
                                tours[i].setTime(minTime);
                                tours[i].addArrivalTime(minTime);
                            }

                        }
                        System.out.println(3);
                        if (i + 1 < 100) {
                            i++;
                        } else {
                            break;
                        }
                    }

                }
            }
        }


//afisez tururile
        for (Tour tour : tours) {
            if (tour != null) {
                Client[] clients = tour.getClients();
                if (clients != null && clients.length > 0) {
                    System.out.println(tour);
                }
            }
        }
    }
}
