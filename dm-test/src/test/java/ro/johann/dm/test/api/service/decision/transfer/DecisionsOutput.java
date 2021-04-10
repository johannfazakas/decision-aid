package ro.johann.dm.test.api.service.decision.transfer;

import lombok.Data;

import java.util.List;

@Data
public class DecisionsOutput {
    List<DecisionOutput> items;
}
