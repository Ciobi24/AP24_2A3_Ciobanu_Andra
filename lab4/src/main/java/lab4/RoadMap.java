package lab4;

import java.util.List;

public class RoadMap {
    List<Road> roads;
    public RoadMap(List<Road> roads){
        this.roads=roads;
    }
    public List<Road> getRoads() {
        return roads;
    }

    @Override
    public String toString() {
        return "RoadMap{" +
                "roads=" + roads +
                '}';
    }
}
