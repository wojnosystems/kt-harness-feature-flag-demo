import io.harness.cf.client.api.CfClient
import io.harness.cf.client.connector.HarnessConnector
import io.harness.cf.client.connector.LocalConnector
import java.io.PrintWriter

fun main() {
    val connector = HarnessConnector(System.getenv("HARNESS_API_KEY"))
    val ffClient = CfClient(connector)
    ffClient.waitForInitialization()
    ffClient.use {
        val writer = PrintWriter(System.out)
        writer.use {
            Service(ffClient, writer).process("from main")
        }
    }
    println("donezo")
}