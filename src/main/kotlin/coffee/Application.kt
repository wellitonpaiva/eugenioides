package coffee

import coffee.data.PreferredDrinkingPlace
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        routes()
    }.start(wait = true)
}

fun Application.routes() {
    routing {
        get("/") {
            call.respondText("Hello there!")
        }
        get("/research") {
            call.respond(researchLines)
        }
        get("/research/drinking-place/") {
            call.respond(PreferredDrinkingPlace.entries.toTypedArray())
        }
    }
}
