package ro.johann.da.decision.domain

import com.fasterxml.jackson.annotation.JsonValue

enum class DecisionStatus(@JsonValue val value: String) {
  DEFINE("define"),
  AID("aid"),
}