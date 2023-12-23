package coffee

import coffee.data.PreferredDrinkingPlace
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun main() {
    val research = Research("/GACTT_RESULTS_ANONYMIZED_v2.csv")
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        routes(research)
    }.start(wait = true)
}

fun Application.routes(research: Research) {
    routing {
        get("/") {
            call.respondText("Hello there!")
        }
        get("/research") {
            call.respond(research.researchLines)
        }
        get("/research/drinking-place/") {
            call.respond(PreferredDrinkingPlace.entries.toTypedArray())
        }
        get("/research/drinking-place/{place}") {
            try {
                call.respond(
                    research.filterByPreferredPlace(
                        PreferredDrinkingPlace.valueOf(call.parameters["place"].toString().uppercase())
                    )
                )
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.NotAcceptable, "Option not found, please use the following options: ${PreferredDrinkingPlace.entries}")
            }
        }
    }
}
