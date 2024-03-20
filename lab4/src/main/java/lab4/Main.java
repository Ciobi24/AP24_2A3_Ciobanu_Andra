package lab4;
import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    Main main=new Main();
    main.compulsory();
    }
    public void compulsory(){
        List<Person> group = generateRandomPersons(10);

        List<Person> drivers = group.stream()
                .filter(person -> person instanceof Driver)
                .collect(Collectors.toList());

        List<Person> passengers = group.stream()
                .filter(person -> person instanceof Person && !(person instanceof Driver))
                .collect(Collectors.toList());
        for(Person driver:drivers) {
            System.out.println(driver);
        }
        for(Person passenger:passengers) {
            System.out.println(passenger);
        }
        System.out.println();
        LinkedList<Person> driversLinkedList=new LinkedList<>(drivers);
        Collections.sort(driversLinkedList, Comparator.comparingInt(Person::getAge));
        for(Person driver:driversLinkedList) {
            System.out.println(driver);
        }
        TreeSet<Person> passengersTreeSet = new TreeSet<>(Comparator.comparing(Person::getName));
        passengersTreeSet.addAll(passengers);
        for(Person passenger:passengersTreeSet) {
            System.out.println(passenger);
        }

    }
    public List<Person> generateRandomPersons(int numberOfPersons) {
        List<Person> persons = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfPersons; i++) {
            String name = "Person" + i;
            int age = random.nextInt(100);
            int indexDestination = random.nextInt(numberOfPersons-1);
            String destination = "Destination" + indexDestination;

            if (random.nextBoolean()) {
                persons.add(new Person(name, age, destination));
            } else {
                persons.add(new Driver(name, age, destination));
            }
        }

        return persons;
    }
}