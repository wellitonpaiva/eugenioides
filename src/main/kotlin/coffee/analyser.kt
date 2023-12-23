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
}


