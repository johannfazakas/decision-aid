package ro.johann.dm.test.api.service.decision.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DecisionOutput {
    String id;
    String name;
    List<CriteriaOutput> criteria = List.of();
}
