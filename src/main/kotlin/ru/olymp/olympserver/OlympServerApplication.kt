package ru.olymp.olympserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OlympServerApplication

fun main(args: Array<String>) {
    runApplication<OlympServerApplication>(*args)
}
