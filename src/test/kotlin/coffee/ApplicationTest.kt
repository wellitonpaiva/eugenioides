package coffee

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.Status.Companion.OK
import org.junit.Test
import kotlin.test.assertEquals

class ApplicationTest {

    private val research = Research("/small_example.csv")

    @Test
    fun `hello there!`() {
        val response: Response = homeRoute().invoke(Request(Method.GET, "/"))
        assertEquals("Hello there!", response.bodyString())
    }

    @Test
    fun `get preferred drinking place options`() {
        val expected = """["HOME","OFFICE","ON_THE_GO","CAFE","OTHER"]"""
        val response: Response = drinkingPlaceOptionsRoute().invoke(Request(Method.GET, "/research/drinking-place/"))
        assertEquals(OK, response.status)
        assertEquals(expected, response.bodyString())
    }

    @Test
    fun `get only home drinking data`() {
        val response = researchByDrinkingPlace(research).invoke(
            Request(
                Method.GET,
                "/research/drinking-place/HOME"
            )
        )

        assertEquals(1, researchLens(response).size)
    }

    @Test
    fun `wrong drinking option`() {
        val response = researchByDrinkingPlace(research).invoke(
            Request(
                Method.GET,
                "/research/drinking-place/WRONG"
            )
        )
        assertEquals(Status.NOT_ACCEPTABLE, response.status)
        assertEquals(
            "Option not found, please use the following options: [HOME, OFFICE, ON_THE_GO, CAFE, OTHER]",
            response.bodyString()
        )
    }
}
