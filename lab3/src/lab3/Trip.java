
package lab3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Trip {
        private String city;
        private LocalDate start;
        private LocalDate end;
        private List<Attraction> attractions=new ArrayList<>();
        public Trip(){}

        public Trip(String city, LocalDate start, LocalDate end) {
                this.city = city;
                this.start = start;
                this.end = end;
        }

        public Trip(String city, LocalDate start, LocalDate end, List<Attraction> attractions) {
                this.city = city;
                this.start = start;
                this.end = end;
                this.attractions = attractions;
                Collections.sort(this.attractions);
        }

        public String getCity() {
                return city;
        }

        public LocalDate getStart() {
                return start;
        }

        public LocalDate getEnd() {
                return end;
        }

        public List<Attraction> getAttractions() {
                return attractions;
        }

        public void setCity(String city) {
                this.city = city;
        }

        public void setStart(LocalDate start) {
                this.start = start;
        }

        public void setEnd(LocalDate end) {
                this.end = end;
        }

        public void setAttractions(List<Attraction> attractions) {
                this.attractions = attractions;
                Collections.sort(this.attractions);
        }
        public void addAttraction(Attraction attraction){
                attractions.add(attraction);
                Collections.sort(this.attractions);
        }
        public int compareByOpeningHour(Attraction a1, Attraction a2,LocalDate date){
                return ((Visitable) a1).getOpeningHour(date).compareTo(((Visitable) a2).getOpeningHour(date));
        }
        public void displayVisitableAttractions(LocalDate date){
                List<Attraction>visitables=new ArrayList<Attraction>();
                for(Attraction attraction:attractions){
                        if(attraction instanceof Visitable && !(attraction instanceof Payable)){
                               visitables.add(attraction);
                        }
                }
                Collections.sort(visitables,new Comparator<Attraction>() {
                        @Override
                        public int compare(Attraction a1, Attraction a2) {
                                return compareByOpeningHour(a1, a2, date);
                        }
                });
                for(Attraction attraction:visitables){
                        System.out.println(attraction);
                }
        }
        @Override
        public String toString() {
                return "Trip{" +
                        "city='" + city + '\'' +
                        ", start=" + start +
                        ", end=" + end +
                        ", attractions=" + attractions +
                        '}';
        }
}
