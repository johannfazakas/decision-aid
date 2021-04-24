package ro.johann.da.decision.domain

import com.fasterxml.jackson.annotation.JsonValue

enum class CriteriaType(@JsonValue val value: String) {
  MINIMUM("minimum"),
  MAXIMUM("maximum")
}