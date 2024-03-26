package lab4;

import java.util.*;
import java.util.stream.Collectors;

import com.github.javafaker.Faker;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//    main.compulsory();
//        main.homework();
        main.bonus();
    }

    public void compulsory() {
        List<Person> group = generateRandomPersons(10);

        List<Person> drivers = group.stream()
                .filter(person -> person instanceof Driver)
                .collect(Collectors.toList());

        List<Person> passengers = group.stream()
                .filter(person -> person instanceof Person && !(person instanceof Driver))
                .collect(Collectors.toList());
        for (Person driver : drivers) {
            System.out.println(driver);
        }
        for (Person passenger : passengers) {
            System.out.println(passenger);
        }
        System.out.println();
        LinkedList<Person> driversLinkedList = new LinkedList<>(drivers);
        Collections.sort(driversLinkedList, Comparator.comparingInt(Person::getAge));
        for (Person driver : driversLinkedList) {
            System.out.println(driver);
        }
        TreeSet<Person> passengersTreeSet = new TreeSet<>(Comparator.comparing(Person::getName));
        passengersTreeSet.addAll(passengers);
        for (Person passenger : passengersTreeSet) {
            System.out.println(passenger);
        }

    }

    public List<Person> generateRandomPersons(int numberOfPersons) {
        List<Person> persons = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfPersons; i++) {
            String name = "Person" + i;
            int age = random.nextInt(100);
            int indexDestination = random.nextInt(numberOfPersons - 1);
            String destination = "Destination" + indexDestination;

            if (random.nextBoolean()) {
                persons.add(new Person(name, age, destination));
            } else {
                persons.add(new Driver(name, age, destination));
            }
        }

        return persons;
    }

    RoadMap generateRoadMap(int numberOfRoads, int numberOfStops) {
        Faker faker = new Faker();
        List<Road> roads = new ArrayList<>();
        for (int i = 0; i < numberOfRoads; i++) {
            List<String> stops = new ArrayList<>();
            for (int j = 0; j < numberOfStops; j++) {
                stops.add(faker.address().city());
            }
            roads.add(new Road(stops));
        }
        return new RoadMap(roads);
    }

    List<Person> generateRandomPersons(int numberOfPersons, RoadMap roadMap) {
        List<Person> persons = new ArrayList<>();
        Random random = new Random();
        Faker faker = new Faker();
        for (int i = 0; i < numberOfPersons; i++) {
            String name = faker.name().fullName();
            int age = random.nextInt(100);
            // Get the list of roads
            List<Road> roads = roadMap.getRoads();

            // Generate a random index for the roads
            int roadIndex = random.nextInt(roads.size());

            // Get the road at the random index
            Road road = roads.get(roadIndex);

            // Get the list of stops for the selected road
            List<String> stops = road.getStops();

            // Generate a random index for the stops
            int stopIndex = random.nextInt(stops.size());

            // Get the stop at the random index
            String destination = stops.get(stopIndex);
            if (random.nextBoolean()) {
                persons.add(new Person(name, age, destination));
            } else {
                persons.add(new Driver(name, age, destination));
            }
        }
        return persons;
    }

    public void homework() {
        Problem problem = new Problem();
        RoadMap roadMap = generateRoadMap(50, 10);
        problem.setRoadMap(roadMap);
        problem.setPersons(generateRandomPersons(200, roadMap));
        problem.findDestinationsOfDrivers();
        problem.findDestinationsOfPersons();
        problem.matchingGreedy();
        problem.printDriverAndPassenger();
        System.out.println(roadMap);
    }

    public void bonus(){
        Problem problem = new Problem();
        RoadMap roadMap = generateRoadMap(50, 10);
        problem.setRoadMap(roadMap);
        problem.setPersons(generateRandomPersonsAndDrivers(40, 20, roadMap));
        problem.findDestinationsOfDrivers();
        problem.findDestinationsOfPersons();
        //GREEDY ALGORITHM
        problem.matchingGreedy();
        problem.printDriverAndPassenger();
        System.out.println();
        System.out.println();
        System.out.println();
        //HOPCROFT KARP ALGORITHM
        problem.matchingHopcroftKarp();
        problem.printDriverAndPassenger();
        System.out.println();
        System.out.println();
        System.out.println(roadMap);
        System.out.println();
        System.out.println();
        //MAX INDEPENDENT SET
        problem.maxIndependentSet();


    }
    public List<Person> generateRandomPersonsAndDrivers(int numberOfDrivers, int numberOfPersons, RoadMap roadMap) {
        List<Person> persons = new ArrayList<>();
        Random random = new Random();
        Faker faker = new Faker();

        // Generate drivers
        for (int i = 0; i < numberOfDrivers; i++) {
            String name = faker.name().fullName();
            int age = faker.number().numberBetween(18, 100);
            String destination = generateRandomDestination(roadMap);
            persons.add(new Driver(name, age, destination));
        }

        // Generate persons
        for (int i = 0; i < numberOfPersons; i++) {
            String name = faker.name().fullName();
            int age = faker.number().numberBetween(18, 100);
            String destination = generateRandomDestination(roadMap);

            // 10% chance to match a driver's destination
            if (random.nextFloat() <= 0.1) {
                int driverIndex = random.nextInt(numberOfDrivers);
                destination = ((Driver) persons.get(driverIndex)).getDestination();
            }

            persons.add(new Person(name, age, destination));
        }

        return persons;
    }
    public String generateRandomDestination(RoadMap roadMap) {
        Random random = new Random();

        // Get the list of roads
        List<Road> roads = roadMap.getRoads();

        // Generate a random index for the roads
        int roadIndex = random.nextInt(roads.size());

        // Get the road at the random index
        Road road = roads.get(roadIndex);

        // Get the list of stops for the selected road
        List<String> stops = road.getStops();

        // Generate a random index for the stops
        int stopIndex = random.nextInt(stops.size());

        // Get the stop at the random index
        String destination = stops.get(stopIndex);

        return destination;
    }
}