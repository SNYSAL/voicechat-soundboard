package dev.kikugie.soundboard.gui.component

import dev.kikugie.soundboard.util.drawScrollingText
import dev.kikugie.kowoui.text
//? if <26.0 {
import io.wispforest.owo.mixin.ui.access.AbstractWidgetAccessor
import io.wispforest.owo.ui.core.OwoUIDrawContext
//?} else {
import io.wispforest.owo.mixin.ui.access.ButtonAccessor
import io.wispforest.owo.ui.core.OwoUIGraphics as OwoUIDrawContext
//?}
import io.wispforest.owo.ui.component.ButtonComponent
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.tooltip.HoveredTooltipPositioner
import net.minecraft.network.chat.Component
import java.util.function.Consumer

//? if <26.0 {
class ScrollingButtonComponent(message: Component, onPress: Consumer<ButtonComponent>?) : ButtonComponent(message, onPress) {
//?} else {
class ScrollingButtonComponent(message: Component, onPress: Consumer<ButtonComponent>?) : ButtonComponent(message, onPress) {
//?}
    private val margins get() = margins().get()

    //? if <26.0 {
    override fun renderWidget(context: GuiGraphics, mouseX: Int, mouseY: Int, delta: Float) {
        renderer.draw(context as OwoUIDrawContext, this, delta)

        val textRenderer = Minecraft.getInstance().font
        val color = if (this.active) 0xFFFFFF else 0xA0A0A0

        context.drawScrollingText(
            textRenderer,
            message,
            x + width / 2,
            x + margins.left,
            y + margins.top,
            x + width - margins.right,
            y + height - margins.bottom,
            color,
            textShadow
        )
        val tooltip = (this as AbstractWidgetAccessor).`owo$getTooltip`()
        if (this.hovered && tooltip.tooltip != null) context.renderTooltip(
            textRenderer, tooltip.tooltip!!
                .toLines(Minecraft.getInstance()), HoveredTooltipPositioner.INSTANCE, mouseX, mouseY
        )
    }
    //?} else {
    // In 1.21.11, renderWidget is final - we use composition instead
    override fun renderWidget(context: GuiGraphics, mouseX: Int, mouseY: Int, delta: Float) {
        super.renderWidget(context, mouseX, mouseY, delta)
    }
    //?}
}



