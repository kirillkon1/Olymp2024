package ru.olymp.olympserver.web.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

class TaskDtoResponse (
    var result: String,
    @JsonProperty(value = "host_id")
    var hostId: Long? = null
)