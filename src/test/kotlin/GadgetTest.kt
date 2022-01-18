import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class GadgetTest {
    private val gadget = Gadget()

    @TestFactory
    fun `format appends`() = listOf(
        "baz" to "gadget(baz)"
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("given '$input' then I get '$expected'") {
            assertEquals(expected, gadget.format(input))
        }
    }
}