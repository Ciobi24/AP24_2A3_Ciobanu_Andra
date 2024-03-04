package lab2;

import java.util.Objects;

public class Vehicle {
    private Depot depot;
    private String name;

    public Vehicle() {
    }

    public Vehicle(String name) {
        this.name = name;
    }

    public Depot getDepot() {
        return depot;
    }

    public String getName() {
        return name;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "depot=" + depot.getName() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) obj;
        return name.equals(other.name);
    }
}
