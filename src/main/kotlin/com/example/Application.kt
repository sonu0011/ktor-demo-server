package com.example

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.Serializable

//fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun main() {
    embeddedServer(Netty, port = 8080, watchPaths = listOf("classes", "resources")) {
        install(CallLogging)
        install(ContentNegotiation) {
            json()
        }
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(Routing) {

        static("assets") {
            resources("static") //specify path of files you want to expose present in resources directory
        }

        get("/") {
            call.respond("Hello Ktor!!")
        }

        get("/user/{username}") {
            val username = call.parameters["username"]
            val connection = call.request.headers["Connection"]
            if (username == "Admin") {
                call.response.header("CustomHeader", "Admin")
                call.respond("Hello admin")
            } else {
                call.respond(message = "Hi $username with header $connection", status = HttpStatusCode.OK)
            }
        }

        get("/user") {
            val name = call.request.queryParameters["name"]
            val age = call.request.queryParameters["age"]
            call.respond("Hi $name your age is $age")
        }

        get("/person") {
            val person = Person("Admin", 25)
            call.respond(person)
        }

        get("/redirect") {
            call.respondRedirect("/move")
        }
        get("/move") {
            call.respond("you have been moved to new url")
        }

        get("/html") {
            call.respond("html template")
        }
    }
}

@Serializable
data class Person(
    val name: String,
    val age: Int
)

fun Application.module2() {
    routing {
        get("/books") {
            call.respond("All Books !!")
        }
    }
}
