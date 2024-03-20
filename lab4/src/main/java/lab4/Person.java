package lab4;

public class Person {
    String name;
    int age;
    String destination;
    public Person(String name, int age, String destination){
        this.name=name;
        this.age=age;
        this.destination=destination;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", destination='" + destination + '\'' +
                '}';
    }
}
