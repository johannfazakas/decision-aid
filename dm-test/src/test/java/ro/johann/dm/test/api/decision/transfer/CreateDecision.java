package ro.johann.dm.test.api.decision.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@Value
public class CreateDecision {
    String name;
}
