package request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InQueryRequest {

    private List<String> firstNames;
    private List<String> lastNames;
}
