package lab4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@AllArgsConstructor
@ToString
@Getter
public class Road {
    List<String> stops;
}
