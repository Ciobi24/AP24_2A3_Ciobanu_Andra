package lab4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Driver extends Person{
    Person passenger;
    public Driver(String name, int age, String destination) {
        super(name, age, destination);
    }
}
