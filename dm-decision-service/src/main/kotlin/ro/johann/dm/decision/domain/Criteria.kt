package ro.johann.dm.decision.domain

import java.util.UUID

sealed class Criteria() {
  abstract val id: UUID
  abstract val name: String
  abstract val type: CriteriaType
  abstract val propertyType: PropertyType
}

class QuantitativeCriteria(
  override val id: UUID,
  override val name: String,
  override val propertyType: PropertyType,
  val quantitativeType: QuantitativeCriteriaType,
  val unitOfMeasure: String
): Criteria() {
  override val type = CriteriaType.QUALITATIVE
}

class QualitativeCriteria(
  override val id: UUID,
  override val name: String,
  override val propertyType: PropertyType,
  val options: List<QualitativeCriteriaOption>
): Criteria() {
  override val type = CriteriaType.QUANTITATIVE
}

