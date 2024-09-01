package coffee

import coffee.data.PreferredDrinkingPlace
import coffee.data.ResearchLine
import org.http4k.core.*
import org.http4k.format.KotlinxSerialization.auto
import org.http4k.lens.Path
import org.http4k.lens.string
import org.http4k.routing.bind

val researchLens = Body.auto<List<ResearchLine>>().toLens()
val drinkingPlaceLens = Body.auto<Array<PreferredDrinkingPlace>>().toLens()
val drinkingPlacePathLens = Path.string().of("place")

fun researchByDrinkingPlaceRoute(research: Research) =
    "/research/drinking-place/{place}" bind Method.GET to { req: Request ->
        Result.runCatching { PreferredDrinkingPlace.valueOf(drinkingPlacePathLens(req).uppercase()) }
            .map { Response(Status.OK).with(researchLens of research.filterByPreferredPlace(it)) }
            .getOrElse { Response(Status.NOT_ACCEPTABLE).body("Option not found, please use the following options: ${PreferredDrinkingPlace.entries}") }
    }

fun drinkingPlaceOptionsRoute() = "/research/drinking-place/" bind Method.GET to {
    Response(Status.OK).with(drinkingPlaceLens of PreferredDrinkingPlace.entries.toTypedArray())
}

fun allResearchRoute(research: Research) = "/research" bind Method.GET to {
    Response(Status.OK).with(researchLens of research.researchLines)
}

