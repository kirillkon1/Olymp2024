package ru.olymp.olympserver.model.entity

import jakarta.persistence.*

@Entity
class ServerPayload(

    var size: Long,
    var task: String

) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    open var id: Long? = null


}