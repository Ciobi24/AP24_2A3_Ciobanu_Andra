package lab4;

import java.util.List;

public class Road {
    List<String> stops;
    public Road(List<String> stops){
        this.stops=stops;
    }
    public List<String> getStops() {
        return stops;
    }

    @Override
    public String toString() {
        return "Road{" +
                "stops=" + stops +
                '}';
    }
}
