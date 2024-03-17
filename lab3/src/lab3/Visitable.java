package lab3;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Map;
public interface Visitable {
        Map<LocalDate,TimeInterval> getTimetable();
        default LocalTime getOpeningHour(LocalDate date) {
                return getTimetable().get(date).getStart();
        }
}
