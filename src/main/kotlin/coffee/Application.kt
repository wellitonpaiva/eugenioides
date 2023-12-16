package coffee

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        routes()
    }.start(wait = true)
}

fun Application.routes() {
    routing {
        get("/") {
            call.respondText("Hello there!")
        }
    }
}
