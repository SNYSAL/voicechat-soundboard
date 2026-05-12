package dev.kikugie.kowoui.dynamic

import dev.kikugie.kowoui.cached
import io.wispforest.owo.ui.component.LabelComponent
//? if <26.0 {
import io.wispforest.owo.ui.core.OwoUIDrawContext
//?} else {
import io.wispforest.owo.ui.core.OwoUIGraphics as OwoUIDrawContext
//?}
import net.minecraft.network.chat.Component

class DynamicLabelComponent(initial: Component) : LabelComponent(initial) {
    private var provider: (() -> Component)? = null
    private var cache: Component by cached(initial) {
        Component = it
        wrappedText = listOf(it.asOrderedText())
    }

    fun Component(provider: () -> Component) = apply {
        this.provider = provider
    }

    override fun draw(context: OwoUIDrawContext?, mouseX: Int, mouseY: Int, partialTicks: Float, delta: Float) {
        provider?.invoke()?.let { cache = it }
        super.draw(context, mouseX, mouseY, partialTicks, delta)
    }
}



