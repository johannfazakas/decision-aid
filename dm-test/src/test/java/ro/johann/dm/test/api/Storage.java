package ro.johann.dm.test.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.johann.dm.test.api.decision.transfer.Decision;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode
public class Storage {

    private int responseStatusCode;
    private Map<String, Decision> decisions = new HashMap<>();

    public void clear() {
        this.responseStatusCode = 0;
        this.decisions.clear();
    }

    public void addDecision(String alias, Decision decision) {
        decisions.put(alias, decision);
    }

    public Decision getDecision(String alias) {
        return decisions.get(alias);
    }
}
