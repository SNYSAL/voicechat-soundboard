package dev.kikugie.kowoui.dynamic

//? if =1.21.8 {
import io.wispforest.owo.ui.container.WrappingParentComponent
import io.wispforest.owo.ui.core.Component
import io.wispforest.owo.ui.core.OwoUIDrawContext
//?} else {
import io.wispforest.owo.ui.container.WrappingParentUIComponent as WrappingParentComponent
import io.wispforest.owo.ui.core.UIComponent as Component
import io.wispforest.owo.ui.core.OwoUIGraphics as OwoUIDrawContext
//?}
import io.wispforest.owo.ui.core.Sizing.content

class WrapperContainer<T : Component>(child: T) : WrappingParentComponent<T>(content(), content(), child) {
    override fun draw(context: OwoUIDrawContext?, mouseX: Int, mouseY: Int, partialTicks: Float, delta: Float) {
        super.draw(context, mouseX, mouseY, partialTicks, delta)
        drawChildren(context, mouseX, mouseY, partialTicks, delta, children())
    }
}