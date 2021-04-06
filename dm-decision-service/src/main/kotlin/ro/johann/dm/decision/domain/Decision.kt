package ro.johann.dm.decision.domain

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "decision")
data class Decision(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: UUID = UUID.randomUUID(),
  @Column(nullable = false)
  val name: String,
  @Column
  val description: String? = null
//  val criteria: List<Criteria> = emptyList(),
//  val alternatives: List<Alternative> = emptyList()
)