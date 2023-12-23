package coffee

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

class ApplicationTest {

    @Test
    fun `hello there!`() = testApplication {
        application { routes() }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello there!", response.bodyAsText())
    }

    @Test
    fun `get preferred drinking place options`() = testApplication {
        application {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            routes()
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
}
