package coffee

import org.junit.Test
import kotlin.test.assertEquals

class AnalyserKtTest {

    private val research = Research("/small_example.csv")

    @Test
    fun `can read file`() = assertEquals(2, research.readFile().size)

    @Test
    fun `can parse file`() {
        research.readFile().apply {
            assertEquals("pKL8aB", get(0).id)
            assertEquals("Zd694B", get(1).id)
        }

    }
}