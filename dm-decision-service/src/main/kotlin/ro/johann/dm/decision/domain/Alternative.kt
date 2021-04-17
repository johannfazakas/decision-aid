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
@Table(name = "alternative")
data class Alternative(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: UUID = UUID.randomUUID(),
  @Column(nullable = false)
  val name: String,
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "decision_id")
  val decision: Decision
)
