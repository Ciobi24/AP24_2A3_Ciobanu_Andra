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
     *
     * @param problem
     */
    public void solveHomework(Problem problem) {
        tours = new Tour[100];
        int i = 0;
        boolean notFinished = true;
        while (notFinished) {
            notFinished = false;
            Client closestClient = null;
            int minDistance = Integer.MAX_VALUE;
            LocalTime minTime = LocalTime.of(7, 0);
            tours[i] = new Tour();
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
                            int clientDistance;
                            tours[i].setVehicle(vehicle);
                            //incep turul, caut clienti care sa nu fie vizitati in niciun tur
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
                                    //am clienti nevizitati inca, caut cel mai apropiat
                                    notFinished = true;
                                    Random rand = new Random();
                                    clientDistance = rand.nextInt(20) + 1;
                                    if (clientDistance < minDistance) {
                                        LocalTime newTime = tours[i].getTime().plusMinutes(clientDistance);
                                        if (newTime.isBefore(client.getEndInterval())) {
                                            minDistance = clientDistance;
                                            closestClient = client;
                                            if (newTime.isBefore(client.getStartInterval())) {
                                                minTime = client.getStartInterval();
                                            } else {
                                                minTime = newTime;
                                            }
                                        }
                                    }
                                }
                            }

                            if (notFinished && tours[i].addClient(closestClient) == 1) {
                                tours[i].setTime(minTime);
                                tours[i].addArrivalTime(minTime);
                            }


                        }

                    }
                }
            }
            if (i + 1 < 100) {
                i++;
            } else {
                break;
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
