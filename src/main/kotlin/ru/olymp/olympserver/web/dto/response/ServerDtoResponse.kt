package ru.olymp.olympserver.web.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import ru.olymp.olympserver.model.entity.Server

class ServerDtoResponse {

    @JsonProperty(value = "id")
    var serverId: Long? = null

    @JsonProperty(value = "server_ram")
    var serverRam: Long? = null

    @JsonProperty(value = "used_ram")
    var usedRam: Long? = null

    constructor(server: Server){
        this.serverId = server.id
        this.serverRam = server.totalRAM
        var userRam = 0L
        server.listOfTask.forEach { userRam += it.size }


    }

}