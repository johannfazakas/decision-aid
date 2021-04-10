package ro.johann.dm.test.api.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.johann.dm.test.api.service.decision.transfer.CriteriaOutput;
import ro.johann.dm.test.api.service.decision.transfer.DecisionOutput;

import java.util.List;

@Data
@EqualsAndHashCode
public class Storage {

    private int responseStatusCode;

    private DecisionOutput decision;
    private List<DecisionOutput> decisions;

    private CriteriaOutput criteria;

    public void cleanUp() {
        this.responseStatusCode = 0;
        this.decision = null;
        this.decisions = List.of();
        this.criteria = null;
    }
}
