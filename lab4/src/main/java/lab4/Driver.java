package lab4;

public class Driver extends Person{
    Person passenger;
    public Driver(String name, int age, String destination){
        super(name, age, destination);
    }

    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }

    public Person getPassenger() {
        return passenger;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "passenger=" + passenger +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", destination='" + destination + '\'' +
                '}';
    }
}
