package ru.olymp.olympserver.model.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.olymp.olympserver.model.entity.Server

@Repository
interface ServerRepository: JpaRepository<Server, Long> {

    @Query(
        value = "select s from Server s order by s.totalRAM - s.usedRAM asc"
    )
    fun getServersByDescendingPayload(): List<Server>

}