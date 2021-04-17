package ro.johann.dm.test.api.steps;

public class Errors {

  public static RuntimeException decisionNotFoundByName(String name) {
    return new RuntimeException(String.format("Decision not found by name %s.", name));
  }

  public static RuntimeException criteriaNotFoundByName(String name) {
    return new RuntimeException(String.format("Criteria not found by name %s.", name));
  }

  public static RuntimeException alternativeNotFoundByName(String name) {
    return new RuntimeException(String.format("Alternative not found by name %s.", name));
  }

  public static RuntimeException propertyNotFoundByNames(String alternativeName, String criteriaName) {
    return new RuntimeException(String.format(
      "Property not found by alternative name %s and criteria name %s", alternativeName, criteriaName));
  }
}
