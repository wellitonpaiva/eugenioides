package coffee

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.then
import org.http4k.filter.ServerFilters
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Netty
import org.http4k.server.asServer


fun main() {
    val research = Research("/GACTT_RESULTS_ANONYMIZED_v2.csv")

    ServerFilters.CatchLensFailure
        .then(
            routes(
                "/" bind Method.GET to {
                    Response(Status.OK).body(createHTML().html {
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
                    })
                },
                allResearchRoute(research),
                drinkingPlaceOptionsRoute(),
                researchByDrinkingPlaceRoute(research),
            )
        )
        .asServer(Netty(8080)).start()
}

private fun DIV.chart(title: String, data: Map<String, Int>) {
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
                      labels: ${Json.encodeToString(data.keys)},
                      datasets: [{
                        label: '# of Votes',
                        data: ${Json.encodeToString(data.values)},
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