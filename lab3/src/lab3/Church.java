package lab3;

import java.time.LocalDate;
import java.util.Map;

public class Church extends Attraction implements Visitable{
    private Map<LocalDate, TimeInterval> timetable;

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return timetable;
    }

    public void setTimetable(Map<LocalDate, TimeInterval> timetable) {
        this.timetable = timetable;
    }


    public Church(String name, String description, String image, Map<LocalDate, TimeInterval> timetable) {
        super(name, description, image);
        this.timetable = timetable;
    }

    public Church(Map<LocalDate, TimeInterval> timetable) {
        this.timetable = timetable;
    }

    @Override
    public String toString() {
        return "Church{" + "name=" + getName() + ", description=" + getDescription() + ", image=" + getImage() +
                ", timetable=" + timetable +
                '}';
    }
}
