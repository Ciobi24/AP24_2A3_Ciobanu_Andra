package lab4;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Problem {
    List<String> destinationOfDriver;
    Map<String, List<Person>> destinationOfPersons;
    RoadMap roadMap;
    List<Person> persons;

    public void findDestinationsOfDrivers() {
        destinationOfDriver = persons.stream().filter(person -> person instanceof Driver).map(person -> (Driver) person)
                .map(Driver::getDestination)
                .collect(Collectors.toList());
    }

    public void findDestinationsOfPersons() {
        destinationOfPersons = persons.stream()
                .collect(Collectors.groupingBy(Person::getDestination));
    }
    public void printDriverAndPassenger(){
        List<Driver> drivers = persons.stream().filter(person -> person instanceof Driver).map(person -> (Driver) person).collect(Collectors.toList());
        for(Driver driver: drivers){
            if (driver.getPassenger() != null) {
                System.out.println("driver "+driver.getName()+" destination "+ driver.getDestination() + "  passenger " + driver.getPassenger().getName()+" destination "+ driver.getPassenger().getDestination());
            } else {
                System.out.println("driver "+driver.getName() +" destination "+ driver.getDestination()+ " does not have a passenger");
            }
        }

    }
    //incep cu lista de destinati
    //iau driverul si cauta in mapa o cheie care e pe un drum comun cu destinatia (sa fie inainte)
    //daca la cheie am un pasager, nu driver great
    //daca nu, mai caut etc pana gasesc sau nu mai am unde cauta
    //next destination, next driver
    public void matching() {
        for (String destination : destinationOfDriver) {
            List<Driver> driversOfDestination = destinationOfPersons.get(destination).stream().filter(person -> person instanceof Driver).map(person -> (Driver) person).collect(Collectors.toList());
            for (Driver driver : driversOfDestination) {
                for (String destinationPassenger : destinationOfPersons.keySet()) {
                    boolean found = false;
                    if (destinationPassenger.equals(destination)) {
                        Person passenger = destinationOfPersons.get(destinationPassenger).stream().filter(person -> !(person instanceof Driver)).findFirst().orElse(null);
                        if (passenger != null && driver.getPassenger()==null) {
                            driver.setPassenger(passenger);
                            List<Person> personsAtDestination = destinationOfPersons.get(destination);
                            personsAtDestination.remove(driver);
                            personsAtDestination.remove(passenger);
                            destinationOfPersons.put(destination, personsAtDestination);
                            found = true;
                        }
                    }
                    if(!found){
                        for (Road destinationRoad : roadMap.getRoads().stream().filter(road -> road.getStops().contains(destinationPassenger)).collect(Collectors.toList())) {
                            if (!found && destinationRoad.getStops().indexOf(destinationPassenger) < destinationRoad.getStops().indexOf(destination)) {
                                Person passenger = destinationOfPersons.get(destinationPassenger).stream().filter(person -> !(person instanceof Driver)).findFirst().orElse(null);
                                if (passenger != null && driver.getPassenger()==null) {
                                    driver.setPassenger(passenger);
                                    List<Person> personsAtDestinationPassenger = destinationOfPersons.get(destinationPassenger);
                                    personsAtDestinationPassenger.remove(passenger);
                                    destinationOfPersons.put(destinationPassenger, personsAtDestinationPassenger);

                                    List<Person> personsAtDestination = destinationOfPersons.get(destination);
                                    personsAtDestination.remove(driver);
                                    destinationOfPersons.put(destination, personsAtDestination);
                                    found = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

