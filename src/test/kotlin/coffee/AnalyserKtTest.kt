package coffee

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class AnalyserKtTest {

    @Test
    fun `can read file`() {
        val file = File(this.javaClass.getResource("/small_example.csv")!!.file)
        assertEquals(2, readFile(file).size)
    }

    @Test
    fun `can parse file`() {
        val file = File(this.javaClass.getResource("/small_example.csv")!!.file)
        val rows = readFile(file)
        assertEquals("gMR29l", rows[0].id)
        assertEquals("BkPN0e", rows[1].id)
    }
}