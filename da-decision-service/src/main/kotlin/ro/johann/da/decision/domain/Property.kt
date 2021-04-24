package ro.johann.da.decision.domain

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "property")
data class Property(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: UUID = UUID.randomUUID(),
  @Column(nullable = false)
  var value: Double,
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "alternative_id")
  val alternative: Alternative,
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "criteria_id")
  val criteria: Criteria
)

//sealed class Property() {
//  abstract val id: UUID
//  abstract val value: Any
//  abstract val criteria: Criteria
//  abstract val type: PropertyType
//}
//
//class IntProperty(
//  override val id: UUID,
//  override val value: Int,
//  override val criteria: Criteria
//): Property() {
//  override val type = PropertyType.INT
//}
//
//class DoubleProperty(
//  override val id: UUID,
//  override val value: Double,
//  override val criteria: Criteria
//): Property() {
//  override val type = PropertyType.DOUBLE
//}
//
//class StringProperty(
//  override val id: UUID,
//  override val value: String,
//  override val criteria: Criteria
//): Property() {
//  override val type = PropertyType.STRING
//}