package ru.olymp.olympserver.web.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import ru.olymp.olympserver.model.entity.Server

class ServerPayloadDtoResponse {

    var payload: Float? = null
    @JsonProperty(value = "server_id")
    var serverId: Long? = null

    constructor(server: Server){

        this.payload = server.totalRAM.toFloat() / server.usedRAM!!.toFloat()
        this.serverId = server.id
    }
}