package lab3;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//        main.compulsory();
//        System.out.println();
//        main.homework();
//        System.out.println();
        main.bonus();
    }

    public void compulsory() {
        Map<LocalDate, TimeInterval> map1 = new HashMap<>();
        map1.put(LocalDate.of(2021, 5, 20), new TimeInterval(LocalTime.of(20, 0), LocalTime.of(23, 0)));
        Concert concert1 = new Concert("ZC/DC", "The best concert ever", "acdc.jpg", map1, 100);
        System.out.println(concert1);
        System.out.println();
        map1.put(LocalDate.of(2021, 5, 21), new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0)));
        Church church1 = new Church("St. Joseph", "The oldest church in Bucharest", "church.jpg", map1);
        System.out.println(church1);
        System.out.println();
        map1.put(LocalDate.of(2021, 5, 22), new TimeInterval(LocalTime.of(12, 0), LocalTime.of(14, 0)));
        Statue statue1 = new Statue("Statue of Liberty", "The most famous statue in the world", "statue.jpg", map1);
        System.out.println(statue1);
        System.out.println();
        Trip trip1 = new Trip("Bucharest", LocalDate.of(2021, 5, 20), LocalDate.of(2021, 5, 23));
        trip1.addAttraction(concert1);
        trip1.addAttraction(church1);
        trip1.addAttraction(statue1);
        System.out.println(trip1);
        System.out.println();
    }

    public void homework() {
        Map<LocalDate, TimeInterval> mapConcert1 = new HashMap<>();
        mapConcert1.put(LocalDate.of(2021, 5, 20), new TimeInterval(LocalTime.of(20, 0), LocalTime.of(23, 0)));
        Concert concert1 = new Concert("ZC/DC", "The best concert ever", "acdc.jpg", mapConcert1, 100);
        Map<LocalDate, TimeInterval> mapChurch1 = new HashMap<>();
        mapChurch1.put(LocalDate.of(2021, 5, 20), new TimeInterval(LocalTime.of(14, 0), LocalTime.of(16, 0)));
        Church church1 = new Church("St. Joseph", "The oldest church in Bucharest", "church.jpg", mapChurch1);
        Map<LocalDate, TimeInterval> mapStatue1 = new HashMap<>();
        mapStatue1.put(LocalDate.of(2021, 5, 20), new TimeInterval(LocalTime.of(12, 0), LocalTime.of(14, 0)));
        Statue statue1 = new Statue("Statue of Liberty", "The most famous statue in the world", "statue.jpg", mapStatue1);
        TravelPlan travelPlan1 = new TravelPlan();
        travelPlan1.addAttraction(concert1, LocalDate.of(2021, 5, 20));
        travelPlan1.addAttraction(church1, LocalDate.of(2021, 5, 21));
        travelPlan1.addAttraction(statue1, LocalDate.of(2021, 5, 22));
        System.out.println(travelPlan1);
        System.out.println();
        Trip trip1 = new Trip("Bucharest", LocalDate.of(2021, 5, 20), LocalDate.of(2021, 5, 23));
        trip1.addAttraction(concert1);
        trip1.addAttraction(church1);
        trip1.addAttraction(statue1);
        trip1.displayVisitableAttractions(LocalDate.of(2021, 5, 20));
    }

    /**
     * Creates instances of Attractions and a Trip and makes a TravelPlan for them
     * Uses a graph and coloring heuristic: DSatur and RLF
     * The graph: vertices are the attractions and edges connect attractions of the same type
     * Compares the two heuristics
     */
    //to be added? if i need to visit ALL attractions then add the attractions with a date that is after the end date of the trip to the travel plan according to their timetable on the least saturated day
    public void bonus() {
        Map<LocalDate, TimeInterval> mapConcert1 = new HashMap<>();
        mapConcert1.put(LocalDate.of(2021, 5, 20), new TimeInterval(LocalTime.of(20, 0), LocalTime.of(23, 0)));
        Concert concert1 = new Concert("AC/DC", "The best concert ever", "acdc.jpg", mapConcert1, 100);
        Map<LocalDate, TimeInterval> mapConcert2 = new HashMap<>();
        mapConcert2.put(LocalDate.of(2021, 5, 21), new TimeInterval(LocalTime.of(20, 0), LocalTime.of(23, 0)));
        Concert concert2 = new Concert("Metallica", "The best concert ever", "metallica.jpg", mapConcert2, 100);
        Map<LocalDate, TimeInterval> mapChurch1 = new HashMap<>();
        mapChurch1.put(LocalDate.of(2021, 5, 20), new TimeInterval(LocalTime.of(14, 0), LocalTime.of(16, 0)));
        mapChurch1.put(LocalDate.of(2021, 5, 21), new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0)));
        Church church1 = new Church("St. Joseph", "The oldest church in Bucharest", "church.jpg", mapChurch1);
        Church church2 = new Church("St. Mary", "The biggest church in Bucharest", "church.jpg", mapChurch1);
        Church church3 = new Church("St. Peter", "The smallest church in Bucharest", "church.jpg", mapChurch1);
        Map<LocalDate, TimeInterval> mapStatue1 = new HashMap<>();
        mapStatue1.put(LocalDate.of(2021, 5, 20), new TimeInterval(LocalTime.of(0, 1), LocalTime.of(23, 59)));
        mapStatue1.put(LocalDate.of(2021, 5, 21), new TimeInterval(LocalTime.of(0, 1), LocalTime.of(23, 59)));
        Statue statue1 = new Statue("Statue of Liberty", "The most famous statue in the world", "statue.jpg", mapStatue1);
        Map<LocalDate, TimeInterval> mapStatue2 = new HashMap<>();
        mapStatue2.put(LocalDate.of(2021, 5, 20), new TimeInterval(LocalTime.of(0, 1), LocalTime.of(23, 59)));
        mapStatue2.put(LocalDate.of(2021, 5, 21), new TimeInterval(LocalTime.of(0, 1), LocalTime.of(23, 59)));
        Statue statue2 = new Statue("Statue of Franz Kafka", "Nice", "statue.jpg", mapStatue2);
        Trip trip1 = new Trip("Bucharest", LocalDate.of(2021, 5, 20), LocalDate.of(2021, 5, 21));
        trip1.addAttraction(concert1);
        trip1.addAttraction(church1);
        trip1.addAttraction(statue1);
        trip1.addAttraction(concert2);
        trip1.addAttraction(statue2);
        trip1.addAttraction(church2);
        trip1.addAttraction(church3);
        Graph graph = new Graph(7);
        graph.constructGraph(trip1.getAttractions());
        int[] result = new int[7];
        graph.DSatur(trip1, result);
        TravelPlan travelPlan1 = new TravelPlan();
        for (int i = 0; i < 7; i++) {
            if (!trip1.getStart().plusDays(result[i]).isAfter(trip1.getEnd())) {
                travelPlan1.addAttraction(trip1.getAttractions().get(i), trip1.getStart().plusDays(result[i]));
            }
        }
        System.out.println(travelPlan1);
        System.out.println();
        int[] result2 = new int[7];
        graph.RLF(trip1, result2);
        TravelPlan travelPlan2 = new TravelPlan();
        for (int i = 0; i < 7; i++) {
            if (!trip1.getStart().plusDays(result[i]).isAfter(trip1.getEnd())) {
                travelPlan2.addAttraction(trip1.getAttractions().get(i), trip1.getStart().plusDays(result2[i]));
            }
        }
        System.out.println(travelPlan2);
    }

}
