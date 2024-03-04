package lab2;

import java.time.LocalTime;
/**
 * Ciobanu Andra 2A3
 * Main compulsory
 */
public class Main {
    public static void main(String[] args) {
        Main lab2 = new Main();
        lab2.compulsory();

    }
    public void compulsory(){
        Client client1 = new Client();
        client1.setName("Client1");
        client1.setClientType(Client.Type.REGULAR);
        client1.setStartInterval(LocalTime.of(8, 0));
        client1.setEndInterval(LocalTime.of(10, 10));
        Client client2 = new Client("Client2");
        System.out.println(client1);
        System.out.println(client2);
        Client client3 = new Client("Client3", LocalTime.of(10, 40), LocalTime.of(12, 30), Client.Type.PREMIUM);
        System.out.println(client3);
        Vehicle vehicle1 = new Vehicle("Vehicle1");
        Depot depot1 = new Depot("Depot1");
        depot1.setVehicles(vehicle1);
        System.out.println(vehicle1);
        System.out.println(depot1);
        Main lab2 = new Main();
    }
    public void homework(){

    }
    public void bonus(){

    }
}
