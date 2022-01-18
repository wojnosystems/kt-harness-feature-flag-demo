import io.harness.cf.client.api.CfClient
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.ByteArrayOutputStream
import java.io.PrintWriter
import kotlin.test.assertEquals

class ServiceITest {
    private lateinit var output : ByteArrayOutputStream
    private lateinit var mock : CfClient
    private lateinit var service : Service
    @BeforeEach
    fun init() {
        output = ByteArrayOutputStream()
        mock = mock()
        service = Service(mock, PrintWriter(output))
    }
    @Nested
    @DisplayName("for Gadget Feature Flag")
    inner class ForGadgetFeatureFlag {
        @Nested
        inner class WhenDisabled {
            @Test
            fun `it uses widget`() {
                /* Given */
                whenever(mock.boolVariation(eq(FlagName.GADGET_REPLACE_WIDGET.name), any(), any())).thenReturn(false)

                /* When */
                service.process("boom")

                /* Then */
                assertEquals("widget(boom)", output.toString())
            }
        }

        @Nested
        inner class WhenEnabled {
            @Test
            fun `it uses gadget`() {
                /* Given */
                whenever(mock.boolVariation(eq(FlagName.GADGET_REPLACE_WIDGET.name), any(), any())).thenReturn(true)

                /* When */
                service.process("boom")

                /* Then */
                assertEquals("gadget(boom)", output.toString())
            }
        }
    }
}