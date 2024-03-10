package lab2;

import java.util.Objects;

/**
 * Vehicle class represents a vehicle in a depot
 * A vehicle has a name and one depot
 */
public abstract class Vehicle {
    private Depot depot;
    private String name;

    protected Vehicle() {
    }

    protected Vehicle(String name) {
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
