package ro.johann.dm.test.api.common;

public class Errors {
    public static RuntimeException decisionNotFoundByName(String name) {
        return new RuntimeException(String.format("Decision not found by name %s", name));
    }
}
