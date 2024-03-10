package lab2;

import java.util.Arrays;

/**
 * Depot class represents a depot that contains vehicles.
 */
public class Depot {
    private String name;
    private Vehicle[] vehicles;

    public Depot() {
    }

    public Depot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVehicles(Vehicle... vehicles) {
        Vehicle[] vehicles1 = new Vehicle[vehicles.length];
        int i = 0;
        for (int j = 0; j < vehicles.length; j++) {
            boolean isDuplicate = false;
            for (int k = j + 1; k < vehicles.length; k++) {
                if (vehicles[j].equals(vehicles[k])) {
                    isDuplicate = true;
                }
            }
            if (!isDuplicate) {
                if (vehicles[j] instanceof Drone) {
                    vehicles1[i] = new Drone(vehicles[j].getName(), ((Drone) vehicles[j]).getDuration());
                    vehicles1[i++].setDepot(this);
                } else if (vehicles[j] instanceof Truck) {
                    vehicles1[i] = new Truck(vehicles[j].getName(), ((Truck) vehicles[j]).getCapacity());
                    vehicles1[i++].setDepot(this);
                    vehicles[j].setDepot(this);
                }
            }

        }
        this.vehicles = Arrays.copyOf(vehicles1, i);
    }

    @Override
    public String toString() {
        return "Depot{" +
                "name='" + name + '\'' +
                ", vehicles=" + Arrays.toString(vehicles) +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Depot)) {
            return false;
        }
        Depot other = (Depot) obj;
        return name.equals(other.name);
    }
}
