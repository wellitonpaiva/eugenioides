package coffee

import coffee.data.PreferredDrinkingPlace
import coffee.data.ResearchLine
import org.http4k.core.*
import org.http4k.core.Method.GET
import org.http4k.core.Status.Companion.NOT_ACCEPTABLE
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.ServerFilters
import org.http4k.format.KotlinxSerialization.auto
import org.http4k.lens.Path
import org.http4k.lens.string
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Netty
import org.http4k.server.asServer

val researchLens = Body.auto<List<ResearchLine>>().toLens()
val drinkingPlaceLens = Body.auto<Array<PreferredDrinkingPlace>>().toLens()
val drinkingPlacePathLens = Path.string().of("place")

fun main() {
    val research = Research("/GACTT_RESULTS_ANONYMIZED_v2.csv")
    ServerFilters.CatchLensFailure
        .then(
            routes(
                homeRoute(),
                allResearchRoute(research),
                drinkingPlaceOptionsRoute(),
                researchByDrinkingPlace(research),
            )
        ).asServer(Netty(8080)).start()
}

fun homeRoute(): RoutingHttpHandler = "/" bind GET to {
    Response(OK).body("Hello there!")
}

fun researchByDrinkingPlace(research: Research) =
    "/research/drinking-place/{place}" bind GET to { req: Request ->
        Result.runCatching { PreferredDrinkingPlace.valueOf(drinkingPlacePathLens(req).uppercase()) }
            .map { Response(OK).with(researchLens of research.filterByPreferredPlace(it)) }
            .getOrElse { Response(NOT_ACCEPTABLE).body("Option not found, please use the following options: ${PreferredDrinkingPlace.entries}") }
    }

fun drinkingPlaceOptionsRoute() = "/research/drinking-place/" bind GET to {
    Response(OK).with(drinkingPlaceLens of PreferredDrinkingPlace.entries.toTypedArray())
}

fun allResearchRoute(research: Research) = "/research" bind GET to {
    Response(OK).with(researchLens of research.researchLines)
}