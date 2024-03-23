package lab4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@AllArgsConstructor
@Setter
public class RoadMap {
    List<Road> roads;
}
