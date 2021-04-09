package ro.johann.dm.test.api.decision.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Decision {
    String id;
    String name;
}
