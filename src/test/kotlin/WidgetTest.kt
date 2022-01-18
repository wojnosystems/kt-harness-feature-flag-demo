import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class WidgetTest {
    private val widget = Widget()

    @TestFactory
    fun `format appends`() = listOf(
        "foo" to "widget(foo)",
        "bar" to "widget(bar)"
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("given '$input' then I get '$expected'") {
            Assertions.assertEquals(expected, widget.format(input))
        }
    }
}