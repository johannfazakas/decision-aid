package ro.johann.da.test.api.service.decision.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class DecisionOutput {
  String id;
  String name;
  String description;
  String status;
  AidSummary aidSummary;
  List<CriteriaOutput> criteria;
  List<AlternativeOutput> alternatives;
  List<PropertyOutput> properties;

  public void addAlternative(AlternativeOutput alternative) {
    alternatives.add(alternative);
  }

  public void addCriteria(CriteriaOutput criterion) {
    criteria.add(criterion);
  }
}
