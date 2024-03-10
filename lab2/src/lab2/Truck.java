package lab2;

/**
 * Truck is a Vehicle with a capacity
 */
public class Truck extends Vehicle {
    private int capacity;

    public Truck(String name, int capacity) {
        super(name);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
