package ro.johann.dm.test.api.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.johann.dm.test.api.service.decision.transfer.Decision;

import java.util.List;

@Data
@EqualsAndHashCode
public class Storage {

    private int responseStatusCode;

    private Decision decision;
    private List<Decision> decisions;

    public void cleanUp() {
        this.responseStatusCode = 0;
        this.decision = null;
        this.decisions = List.of();
    }
}
