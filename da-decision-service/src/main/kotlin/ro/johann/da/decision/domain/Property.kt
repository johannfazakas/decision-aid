package ro.johann.da.decision.domain

import java.time.LocalDateTime
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

  @Column
  var createdAt: LocalDateTime,

  @Column
  var updatedAt: LocalDateTime,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "alternative_id")
  val alternative: Alternative,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "criteria_id")
  val criteria: Criteria
)
