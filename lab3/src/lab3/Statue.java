package lab3;

import java.time.LocalDate;
import java.util.Map;

public class Statue extends Attraction implements Visitable {
    private Map<LocalDate, TimeInterval> timetable;

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return timetable;
    }

    public void setTimetable(Map<LocalDate, TimeInterval> timetable) {
        this.timetable = timetable;
    }


    public Statue(String name, String description, String image, Map<LocalDate, TimeInterval> timetable) {
        super(name, description, image);
        this.timetable = timetable;
    }

    public Statue(Map<LocalDate, TimeInterval> timetable) {
        this.timetable = timetable;
    }

    @Override
    public String toString() {
        return "Statue{" + "name=" + getName() + ", description=" + getDescription() + ", image=" + getImage() +
                ", timetable=" + timetable +
                '}';
    }
}