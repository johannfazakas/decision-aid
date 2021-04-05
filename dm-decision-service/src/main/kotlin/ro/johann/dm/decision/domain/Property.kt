package ro.johann.dm.decision.domain

import java.util.UUID

sealed class Property() {
  abstract val id: UUID
  abstract val value: Any
  abstract val criteria: Criteria
  abstract val type: PropertyType
}

class IntProperty(
  override val id: UUID,
  override val value: Int,
  override val criteria: Criteria
): Property() {
  override val type = PropertyType.INT
}

class DoubleProperty(
  override val id: UUID,
  override val value: Double,
  override val criteria: Criteria
): Property() {
  override val type = PropertyType.DOUBLE
}

class StringProperty(
  override val id: UUID,
  override val value: String,
  override val criteria: Criteria
): Property() {
  override val type = PropertyType.STRING
}