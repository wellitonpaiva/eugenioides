package coffee

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import java.io.File

val file = File(object {}.javaClass.getResource("/GACTT_RESULTS_ANONYMIZED_v2.csv")?.file!!)
val researchLines: List<ResearchLine> = readFile(file)

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
    }
}
