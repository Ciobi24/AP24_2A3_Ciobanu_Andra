package lab3;

import java.time.LocalTime;

public class TimeInterval extends Pair<LocalTime,LocalTime> {
    public TimeInterval(LocalTime start, LocalTime end) {
        super(start, end);
    }
    public LocalTime getStart() {
        return getFirst();
    }
    public LocalTime getEnd() {
        return getSecond();
    }
    public boolean overlapsWith(TimeInterval other) {
        return getStart().isBefore(other.getEnd()) && getEnd().isAfter(other.getStart());
    }
    public boolean contains(LocalTime time) {
        return getStart().isBefore(time) && getEnd().isAfter(time);
    }
    public boolean contains(TimeInterval other) {
        return getStart().isBefore(other.getStart()) && getEnd().isAfter(other.getEnd());
    }
    public boolean equals(Object other) {
        if (other instanceof TimeInterval otherInterval) {
            return getStart().equals(otherInterval.getStart()) && getEnd().equals(otherInterval.getEnd());
        }
        return false;
    }
    public String toString() {
        return getStart() + " - " + getEnd();
    }
}

