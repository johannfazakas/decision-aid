package ro.johann.da.decision.domain

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "alternative")
data class Alternative(

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: UUID = UUID.randomUUID(),

  @Column(nullable = false)
  var name: String,

  @Column
  var createdAt: LocalDateTime,

  @Column
  var updatedAt: LocalDateTime,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "decision_id")
  val decision: Decision,

  @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
  @JoinColumn(name = "alternative_id")
  private val propertyList: MutableList<Property> = mutableListOf()
) {
  val properties: List<Property>
    get() = propertyList.sortedBy { it.createdAt }
}
