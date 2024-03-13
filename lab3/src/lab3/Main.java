package lab3;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.compulsory();
        System.out.println();
        main.homework();
    }

    public void compulsory() {
        Map<LocalDate, TimeInterval> map1 = new HashMap<>();
        map1.put(LocalDate.of(2021, 5, 20), new TimeInterval(LocalTime.of(20, 0), LocalTime.of(23, 0)));
        Concert concert1 = new Concert("AC/DC", "The best concert ever", "acdc.jpg", map1, 100);
        System.out.println(concert1);
        map1.put(LocalDate.of(2021, 5, 21), new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0)));
        Church church1 = new Church("St. Joseph", "The oldest church in Bucharest", "church.jpg", map1);
        System.out.println(church1);
        map1.put(LocalDate.of(2021, 5, 22), new TimeInterval(LocalTime.of(12, 0), LocalTime.of(14, 0)));
        Statue statue1 = new Statue("Statue of Liberty", "The most famous statue in the world", "statue.jpg", map1);
        System.out.println(statue1);
        Trip trip1 = new Trip("Bucharest", LocalDate.of(2021, 5, 20), LocalDate.of(2021, 5, 23));
        trip1.addAttraction(concert1);
        trip1.addAttraction(church1);
        trip1.addAttraction(statue1);
        System.out.println(trip1);
    }

    public void homework() {
        Map<LocalDate, TimeInterval> map1 = new HashMap<>();
        map1.put(LocalDate.of(2021, 5, 20), new TimeInterval(LocalTime.of(20, 0), LocalTime.of(23, 0)));
        Concert concert1 = new Concert("AC/DC", "The best concert ever", "acdc.jpg", map1, 100);
        map1.put(LocalDate.of(2021, 5, 21), new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0)));
        Church church1 = new Church("St. Joseph", "The oldest church in Bucharest", "church.jpg", map1);
        map1.put(LocalDate.of(2021, 5, 22), new TimeInterval(LocalTime.of(12, 0), LocalTime.of(14, 0)));
        Statue statue1 = new Statue("Statue of Liberty", "The most famous statue in the world", "statue.jpg", map1);
        TravelPlan travelPlan1 = new TravelPlan();
        travelPlan1.addAttraction(concert1, LocalDate.of(2021, 5, 20));
        travelPlan1.addAttraction(church1, LocalDate.of(2021, 5, 21));
        travelPlan1.addAttraction(statue1, LocalDate.of(2021, 5, 22));
        System.out.println(travelPlan1);
    }
}