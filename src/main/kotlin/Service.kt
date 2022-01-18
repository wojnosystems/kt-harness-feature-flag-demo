import io.harness.cf.client.api.CfClient
import io.harness.cf.client.dto.Target
import java.io.PrintWriter

enum class FlagName {
    GADGET_REPLACE_WIDGET
}

class Service(private val featureFlags : CfClient, private val output : PrintWriter) {
    fun process(input : String) {
        val customerName = "Coala Cocoa"
        val all = Target.builder()
            .name("customer")
            .identifier(customerName)
            .build()
        val widget = if( featureFlags.boolVariation(FlagName.GADGET_REPLACE_WIDGET.name, all, false) ) {
            Gadget()
        } else {
            Widget()
        }
        output.print(widget.format(input))
        output.flush()
    }
}