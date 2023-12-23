package coffee

import coffee.data.ResearchLine
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation as serverContentNegotiation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation as clientContentNegotiation

class ApplicationTest {

    private val research = Research("/small_example.csv")

    @Test
    fun `hello there!`() = testApplication {
        application { routes(research) }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello there!", response.bodyAsText())
    }

    @Test
    fun `get preferred drinking place options`() = testApplication {
        application {
            install(serverContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            routes(research)
        }
        val expected = """[
                          |    "HOME",
                          |    "OFFICE",
                          |    "ON_THE_GO",
                          |    "CAFE",
                          |    "OTHER"
                          |]""".trimMargin()
        val response = client.get("/research/drinking-place/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(expected, response.bodyAsText())
    }

    @Test
    fun `get only home drinking data`() = testApplication {
        application {
            install(serverContentNegotiation) {
                json()
            }
            routes(research)
        }
        val client = createClient {
            install(clientContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }

        val response = client.get("/research/drinking-place/HOME")
        assertEquals(1, response.body<List<ResearchLine>>().size)
    }
}
