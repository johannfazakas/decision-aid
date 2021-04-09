package ro.johann.dm.decision.domain

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
  @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
  @JoinColumn(name = "decision_id")
  private val criteriaList: MutableList<Criteria> = mutableListOf(),
//  val alternatives: List<Alternative> = emptyList()
) {
  val criteria: List<Criteria>
    get() = criteriaList
}