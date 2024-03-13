package lab2;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

/**
 * Ciobanu Andra 2A3
 * Main class includes the compulsory, homework and bonus methods
 */
public class Main {
    public static void main(String[] args) {
        Main lab2 = new Main();
//        lab2.compulsory();
        System.out.println("\n\nHomework:\n\n");
        lab2.homework();
        System.out.println("\n\nBonus:\n\n");
        lab2.bonus();
    }

    //    public void compulsory(){
//        Client client1 = new Client();
//        client1.setName("Client1");
//        client1.setClientType(Client.Type.REGULAR);
//        client1.setStartInterval(LocalTime.of(8, 0));
//        client1.setEndInterval(LocalTime.of(10, 10));
//        Client client2 = new Client("Client2");
//        System.out.println(client1);
//        System.out.println(client2);
//        Client client3 = new Client("Client3", LocalTime.of(10, 40), LocalTime.of(12, 30), Client.Type.PREMIUM);
//        System.out.println(client3);
//        Vehicle vehicle1 = new Vehicle("Vehicle1");
//        Depot depot1 = new Depot("Depot1");
//        depot1.setVehicles(vehicle1);
//        System.out.println(vehicle1);
//        System.out.println(depot1);
//        Main lab2 = new Main();
//    }

    /**
     * Homework method: creates clients, vehicles, depots
     * Adds them to the problem and solves it
     */
    public void homework() {
        Client client1 = new Client("Client1", LocalTime.of(21, 0), LocalTime.of(21, 10), Client.Type.REGULAR);
        Client client2 = new Client("Client2", LocalTime.of(21, 0), LocalTime.of(21, 10), Client.Type.REGULAR);
        Client client3 = new Client("Client3", LocalTime.of(10, 40), LocalTime.of(12, 30), Client.Type.PREMIUM);
        Client client4 = new Client("Client4", LocalTime.of(17, 0), LocalTime.of(21, 10), Client.Type.REGULAR);
        Client client5 = new Client("Client5", LocalTime.of(17, 0), LocalTime.of(21, 10), Client.Type.REGULAR);
        Client client6 = new Client("Client6", LocalTime.of(17, 0), LocalTime.of(21, 10), Client.Type.REGULAR);
        Client client7 = new Client("Client7", LocalTime.of(17, 0), LocalTime.of(21, 10), Client.Type.REGULAR);
        Vehicle vehicle1 = new Truck("Truck1", 100);
        Vehicle vehicle2 = new Drone("Drone1", 50);
        Vehicle vehicle3 = new Truck("Truck2", 150);
        Depot depot1 = new Depot("Depot1");
        depot1.setVehicles(vehicle1);
        Depot depot2 = new Depot("Depot2");
        depot2.setVehicles(vehicle2, vehicle3);
        Problem problem = new Problem();
        problem.setDepots(depot1, depot2);
        problem.setClients(client1, client2, client3, client4, client5, client6, client7);
        Solution solution = new Solution(problem);
        solution.solveHomework();
    }

    public Problem problemGenerator() {
        Problem problem = new Problem();
        Random random = new Random();
        int numberOfClients = random.nextInt(5) + 1;
        int numberOfDepots = random.nextInt(3) + 1;
        Client[] clients = new Client[numberOfClients];
        for (int i = 0; i < numberOfClients; i++) {
            LocalTime startInterval = LocalTime.of(random.nextInt(4) + 8, random.nextInt(50) + 1);
            int minutes = random.nextInt(8 * 60) + 1;
            LocalTime endInterval = startInterval.plusMinutes(minutes);
            clients[i] = new Client("Client" + i, startInterval, endInterval, Client.Type.REGULAR);
        }
        Depot[] depots = new Depot[numberOfDepots];
        for (int i = 0; i < numberOfDepots; i++) {
            depots[i] = new Depot("Depot" + i);
            int numberOfVehicles = random.nextInt(5) + numberOfClients;
            Vehicle[] vehicles = new Vehicle[numberOfVehicles];
            for (int j = 0; j < numberOfVehicles; j++) {
                if (random.nextBoolean()) {
                    vehicles[j] = new Truck("Truck" + j, random.nextInt(100) + 50);
                } else {
                    vehicles[j] = new Drone("Drone" + j, random.nextInt(50) + 20);
                }
            }
            depots[i].setVehicles(vehicles);
        }
        int[][] matrixCost = new int[numberOfClients + numberOfDepots][numberOfClients + numberOfDepots];
        for (int i = 0; i < numberOfClients + numberOfDepots; i++) {
            for (int j = 0; j < numberOfClients + numberOfDepots; j++) {
                matrixCost[i][j] = 0;
            }
        }
        //consideram gridul din care apartine subgraful nostru ca fiind o  matrice de (numberOfClients + numberOfDepots) x (numberOfClients + numberOfDepots)
        //subgraful nostru are numberOfClients + numberOfDepots noduri deci il putem incadra  deasupra diagonalei principale indiferent de forma sa
        //pornind de la (x,y) random generam random ce client/depou este la acele coordonate
        //generam random un numar de vecini (maxim 4) si apoi pentru acele locatii generam random clientul/depoul etc
        //astfel asiguram conexitatea si proprietatea de grid graf
        int x = random.nextInt(numberOfClients + numberOfDepots - 1), y = random.nextInt(numberOfClients + numberOfDepots - 1 - x) + x;
        int indexOfObject = random.nextInt(numberOfClients + numberOfDepots - 1);
        ArrayList<Integer> addedObjects = new ArrayList<>();
        addedObjects.add(indexOfObject);

        return problem;
    }

    public void bonus() {
        Client client1 = new Client("Client1", LocalTime.of(8, 0), LocalTime.of(22, 0), Client.Type.REGULAR);
        Client client2 = new Client("Client2", LocalTime.of(15, 20), LocalTime.of(17, 15), Client.Type.REGULAR);
        Client client3 = new Client("Client3", LocalTime.of(10, 40), LocalTime.of(12, 30), Client.Type.PREMIUM);
        Client client4 = new Client("Client4", LocalTime.of(17, 10), LocalTime.of(21, 10), Client.Type.REGULAR);
//        Client client5 = new Client("Client5", LocalTime.of(17, 0), LocalTime.of(21, 10), Client.Type.REGULAR);
//        Client client6 = new Client("Client6", LocalTime.of(17, 0), LocalTime.of(21, 10), Client.Type.REGULAR);
//        Client client7 = new Client("Client7", LocalTime.of(17, 0), LocalTime.of(21, 10), Client.Type.REGULAR);
        Vehicle vehicle1 = new Truck("Truck1", 100);
        Vehicle vehicle2 = new Drone("Drone1", 50);
        Vehicle vehicle3 = new Truck("Truck2", 150);
        Depot depot1 = new Depot("Depot1");
        depot1.setVehicles(vehicle1);
        Depot depot2 = new Depot("Depot2");
        depot2.setVehicles(vehicle2, vehicle3);
        Problem problem = new Problem();
        problem.setDepots(depot1, depot2);
        problem.setClients(client1, client2, client3, client4);
        int[][] matrixCost = new int[9][9];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                matrixCost[i][j] = 0;
            }
        }
        matrixCost[0][4] = matrixCost[4][0] = 3;
        matrixCost[1][5] = matrixCost[5][1] = 2;
        matrixCost[1][2] = matrixCost[2][1] = 1;
        matrixCost[2][4] = matrixCost[4][2] = 4;
        matrixCost[2][3] = matrixCost[3][2] = 5;
        matrixCost[5][2] = matrixCost[2][5] = 6;
        problem.setDistanceMatrix(matrixCost);
        Solution solution = new Solution(problem);
        solution.solveBonus();
    }
}
