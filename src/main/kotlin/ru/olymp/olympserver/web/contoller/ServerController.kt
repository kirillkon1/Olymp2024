package ru.olymp.olympserver.web.contoller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.olymp.olympserver.model.service.ServerService
import ru.olymp.olympserver.web.dto.response.ServerDtoResponse
import ru.olymp.olympserver.web.dto.request.TaskDtoRequest
import ru.olymp.olympserver.web.dto.response.ServerPayloadDtoResponse
import ru.olymp.olympserver.web.dto.response.TaskDtoResponse

@RestController
@RequestMapping("api/server")
class ServerController (private val serverService: ServerService) {

    @GetMapping("/{id}")
    fun getServerById(@PathVariable id: Long): ResponseEntity<ServerDtoResponse>{
        return serverService.getServerById(id)
    }

    @PostMapping
    fun createTask(@RequestBody dto: TaskDtoRequest): ResponseEntity<TaskDtoResponse>{
        return serverService.insertTask(dto)
    }

    @GetMapping("/payload")
    fun getAllServersPayload(): ResponseEntity<List<ServerPayloadDtoResponse>> {
        return serverService.getAllServersPayload()
    }

    @GetMapping("/{id}/payload")
    fun getServerPayloadById(@PathVariable id: Long): ResponseEntity<ServerPayloadDtoResponse> {
        return serverService.getServerPayloadById(id)
    }

}