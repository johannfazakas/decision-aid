package ro.johann.dm.test.api.steps;

public class Errors {
    public static RuntimeException decisionNotFoundByName(String name) {
        return new RuntimeException(String.format("Decision not found by name %s", name));
    }
}
