package coffee

import coffee.data.PreferredDrinkingPlace
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

val writer: ObjectWriter = ObjectMapper().writer()

fun main() {
    val research = Research("/GACTT_RESULTS_ANONYMIZED_v2.csv")

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }
        routing {
            home(research)
            allResearch(research)
            researchByDrinkingPlaceRoute(research)
            drinkingPlaceOptionsRoute()
        }
    })
        .start(wait = true)
}

fun Routing.allResearch(research: Research) =
    get("/research") {
        call.respond(research)
    }

fun Routing.researchByDrinkingPlaceRoute(research: Research) =
    get("/research/drinking-place/{place}") {
        kotlin.runCatching {
            call.pathParameters["place"]?.let {
                call.respond(research.filterByPreferredPlace(PreferredDrinkingPlace.valueOf(it)))
            }
        }.getOrElse {
            call.respond(
                HttpStatusCode.NotAcceptable,
                "Option not found, please use the following options: ${PreferredDrinkingPlace.entries}"
            )
        }
    }

fun Routing.drinkingPlaceOptionsRoute() = get("/research/drinking-place/") {
    call.respond(PreferredDrinkingPlace.entries.toTypedArray())
}


fun Routing.home(research: Research) =
    get("/") {
        call.respondHtml {
            head {
                script {
                    type = ScriptType.textJScript
                    src = "https://unpkg.com/htmx.org@2.0.1"
                }
                script {
                    type = ScriptType.textJScript
                    src = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.4.1/chart.min.js"
                }

                link(rel = "stylesheet", href = "https://cdn.simplecss.org/simple.min.css")
            }
            body {
                h1 { +"Eugenioides" }
                div {
                    chart("Preferred drinking places", research.mapByPreferredPlace())
                    chart("Preferred Brewing Method", research.mapByPreferredBrewingMethod())
                }
            }
        }
    }

fun DIV.chart(title: String, data: Map<String, Int>) {

    div {
        val titleId = title.replace(" ", "").lowercase()
        canvas { id = titleId }
        unsafe {
            raw(
                """
                <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

                <script>
                  const method$titleId = document.getElementById('$titleId');

                  new Chart(method$titleId, {
                    type: 'doughnut',
                    data: {
                      labels: ${writer.writeValueAsString(data.keys)},
                      datasets: [{
                        label: '# of Votes',
                        data: ${writer.writeValueAsString(data.values)},
                        borderWidth: 1
                      }]
                    },
                    options: {
                        plugins: {
                            title: {
                                display: true,
                                text: '$title'
                            },
                            legend: {
                                position: 'left'
                            }
                        }
                    }
                  });
                </script>""".trimIndent()
            )
        }
    }
}