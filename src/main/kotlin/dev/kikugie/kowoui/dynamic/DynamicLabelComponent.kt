package dev.kikugie.kowoui.dynamic

import dev.kikugie.kowoui.cached
import io.wispforest.owo.ui.component.LabelComponent
//? if =1.21.8 {
import io.wispforest.owo.ui.core.OwoUIDrawContext
//?} else {
import io.wispforest.owo.ui.core.OwoUIGraphics as OwoUIDrawContext
//?}
import net.minecraft.network.chat.Component

class DynamicLabelComponent(initial: Text) : LabelComponent(initial) {
    private var provider: (() -> Text)? = null
    private var cache: Text by cached(initial) {
        text = it
        wrappedText = listOf(it.asOrderedText())
    }

    fun text(provider: () -> Text) = apply {
        this.provider = provider
    }

    override fun draw(context: OwoUIDrawContext?, mouseX: Int, mouseY: Int, partialTicks: Float, delta: Float) {
        provider?.invoke()?.let { cache = it }
        super.draw(context, mouseX, mouseY, partialTicks, delta)
    }
}
