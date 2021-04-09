package ro.johann.dm.decision.domain

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
@Table(name = "criteria")
data class Criteria(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: UUID = UUID.randomUUID(),
  @Column(nullable = false)
  val name: String,
  @Column(nullable = false)
  var weight: Int,
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "decision_id")
  val decision: Decision
)

//sealed class Criteria() {
//  abstract val id: UUID
//  abstract val name: String
//  abstract val weight: Int
//  abstract val type: CriteriaType
//  abstract val propertyType: PropertyType
//}
//
//class QuantitativeCriteria(
//  override val id: UUID,
//  override val name: String,
//  override val weight: Int,
//  override val propertyType: PropertyType,
//  val quantitativeType: QuantitativeCriteriaType,
//  val unitOfMeasure: String
//): Criteria() {
//  override val type = CriteriaType.QUALITATIVE
//}
//
//class QualitativeCriteria(
//  override val id: UUID,
//  override val name: String,
//  override val weight: Int,
//  override val propertyType: PropertyType,
//  val options: List<QualitativeCriteriaOption>
//): Criteria() {
//  override val type = CriteriaType.QUANTITATIVE
//}
//
