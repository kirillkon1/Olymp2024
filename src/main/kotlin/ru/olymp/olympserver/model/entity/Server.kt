package ru.olymp.olympserver.model.entity

import jakarta.persistence.*

@Entity
class Server(

    @Column(name = "total_ram")
    var totalRAM: Long,

    @OneToMany
    var listOfTask: MutableList<ServerPayload>

) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "used_ram")
    var usedRAM: Long? = 128
}