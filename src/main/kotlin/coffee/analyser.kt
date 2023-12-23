package coffee

import coffee.data.ResearchLine
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

val file = File(object {}.javaClass.getResource("/GACTT_RESULTS_ANONYMIZED_v2.csv")?.file!!)
val researchLines: List<ResearchLine> = readFile(file)

fun readFile(file: File): List<ResearchLine> =
    csvReader().readAllWithHeader(file).map { ResearchLine(it) }

