package ru.olymp.olympserver.model.service

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.olymp.olympserver.model.entity.Server
import ru.olymp.olympserver.model.entity.ServerPayload
import ru.olymp.olympserver.model.repository.ServerRepository
import ru.olymp.olympserver.web.dto.response.ServerDtoResponse
import ru.olymp.olympserver.web.dto.request.TaskDtoRequest
import ru.olymp.olympserver.web.dto.response.ServerPayloadDtoResponse
import ru.olymp.olympserver.web.dto.response.TaskDtoResponse

@Service
class ServerService(private val serverRepository: ServerRepository) {

    fun getServerById(id: Long): ResponseEntity<ServerDtoResponse>{
        val serverOpt = serverRepository.findById(id)

        if (serverOpt.isEmpty){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }

        return ResponseEntity.ok(ServerDtoResponse(serverOpt.get()))

    }


    @Transactional
    fun insertTask(taskDto: TaskDtoRequest): ResponseEntity<TaskDtoResponse>{

        if(taskDto.size!! > 128L){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(TaskDtoResponse(result = "NOT_OK"))
        }

        val serverList = serverRepository.getServersByDescendingPayload()

        var tmp: Server? = null

        for(server in serverList){
            if (server.totalRAM - server.usedRAM!! >= taskDto.size){
                tmp = server
                tmp.listOfTask.add(ServerPayload(taskDto.size, taskDto.task!!))
                tmp.usedRAM = tmp.usedRAM?.plus(taskDto.size)
            }
        }

        if (tmp == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(TaskDtoResponse(result = "NOT_OK"))
        }

        val currentServer = serverRepository.save(tmp)
        return ResponseEntity.status(HttpStatus.CREATED).body(TaskDtoResponse(result = "OK", hostId = currentServer.id))


    }

    fun getAllServersPayload(): ResponseEntity<List<ServerPayloadDtoResponse>>{
        val serverList = serverRepository.findAll()

        val answer = serverList.map { ServerPayloadDtoResponse(it)}.toList()

        return ResponseEntity.ok(answer)
    }

    fun getServerPayloadById(id: Long): ResponseEntity<ServerPayloadDtoResponse>{
        val serverOpt = serverRepository.findById(id)

        if (serverOpt.isEmpty){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }

        return ResponseEntity.ok(ServerPayloadDtoResponse(serverOpt.get()))
    }



}