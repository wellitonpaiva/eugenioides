package coffee

import coffee.data.PreferredDrinkingPlace
import coffee.data.ResearchLine
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

class Research(filePath: String) {
    private val file = File(object {}.javaClass.getResource(filePath)?.file!!)
    val researchLines: List<ResearchLine> = readFile()
    fun filterByPreferredPlace(place: PreferredDrinkingPlace): List<ResearchLine> =
        researchLines.filter { it.coffeeDrinkingPlace.isIt(place) }

    fun readFile(): List<ResearchLine> =
        csvReader().readAllWithHeader(file).map { ResearchLine(it) }

    fun mapByPreferredPlace(): Map<String, Int> =
        mapOf(
            "Office" to researchLines.count { it.coffeeDrinkingPlace.isIt(PreferredDrinkingPlace.OFFICE) },
            "Home" to researchLines.count { it.coffeeDrinkingPlace.isIt(PreferredDrinkingPlace.HOME) },
            "Cafe" to researchLines.count { it.coffeeDrinkingPlace.isIt(PreferredDrinkingPlace.CAFE) },
            "On the go" to researchLines.count { it.coffeeDrinkingPlace.isIt(PreferredDrinkingPlace.ON_THE_GO) },
            "Other" to researchLines.count { it.coffeeDrinkingPlace.isIt(PreferredDrinkingPlace.OTHER) }
        )

    fun mapByPreferredBrewingMethod(): Map<String, Int> =
        mapOf(
            "Pour Over" to researchLines.count { it.brewingMethod.pourOver },
            "French Press" to researchLines.count { it.brewingMethod.frenchPress },
            "Espresso" to researchLines.count { it.brewingMethod.espresso },
            "Coffee brewing machine (e.g. Mr. Coffee)" to researchLines.count { it.brewingMethod.machine },
            "Pod/capsule machine (e.g. Keurig/Nespresso)" to researchLines.count { it.brewingMethod.capsule },
            "Instant Coffee" to researchLines.count { it.brewingMethod.instant },
            "Bean To Cup Machine" to researchLines.count { it.brewingMethod.beanToCup },
            "Cold Brew" to researchLines.count { it.brewingMethod.coldBrew },
            "Coffee extract (e.g. Cometeer))" to researchLines.count { it.brewingMethod.extract },
            "Others" to researchLines.count { it.brewingMethod.otherMethodName.isNotEmpty() }
        )

}


