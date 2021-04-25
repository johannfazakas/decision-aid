package ro.johann.da.decision.domain

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "decision")
data class Decision(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: UUID = UUID.randomUUID(),

  @Column(nullable = false)
  var name: String,

  @Column
  var description: String? = null,

  @Column
  var status: DecisionStatus,

  @Column
  var createdAt: LocalDateTime,

  @Column
  var updatedAt: LocalDateTime,

  @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
  @JoinColumn(name = "decision_id")
  private val criteriaList: MutableList<Criteria> = mutableListOf(),

  @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
  @JoinColumn(name = "decision_id")
  private val alternativeList: MutableList<Alternative> = mutableListOf(),
) {
  val criteria: List<Criteria>
    get() = criteriaList.sortedBy { it.createdAt }

  val alternatives: List<Alternative>
    get() = alternativeList.sortedBy { it.createdAt }

  fun propertiesAreMissing(): Boolean =
    alternativeList.any { alternative -> alternative.properties.size < criteria.size }
}