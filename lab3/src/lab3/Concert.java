package lab3;

import java.time.LocalDate;
import java.util.Map;

public class Concert  extends Attraction implements Visitable, Payable{
    private Map<LocalDate, TimeInterval> timetable;
    private double ticketPrice;

    public Concert(Map<LocalDate, TimeInterval> timetable, double ticketPrice) {
        this.timetable = timetable;
        this.ticketPrice = ticketPrice;
    }

    public Concert(String name, Map<LocalDate, TimeInterval> timetable, double ticketPrice) {
        super(name);
        this.timetable = timetable;
        this.ticketPrice = ticketPrice;
    }

    public Concert(String name, String description, String image, Map<LocalDate, TimeInterval> timetable, double ticketPrice) {
        super(name, description, image);
        this.timetable = timetable;
        this.ticketPrice = ticketPrice;
    }

    public void setTimetable(Map<LocalDate, TimeInterval> timetable) {
        this.timetable = timetable;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return timetable;
    }

    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public String toString() {
        return "Concert{" + "name=" + getName() + ", description=" + getDescription() + ", image=" + getImage() +
                ", timetable=" + timetable +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}