package lab2;

/**
 * Drone is a Vehicle that has a duration
 */
public class Drone extends Vehicle {
    private int duration;

    public Drone(String name, int duration) {
        super(name);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}
