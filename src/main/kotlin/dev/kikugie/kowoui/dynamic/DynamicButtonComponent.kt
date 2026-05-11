package dev.kikugie.kowoui.dynamic

import dev.kikugie.kowoui.cached
import io.wispforest.owo.ui.component.ButtonComponent
//? if =1.21.8 {
import io.wispforest.owo.ui.core.OwoUIDrawContext
//?} else {
import io.wispforest.owo.ui.core.OwoUIGraphics as OwoUIDrawContext
//?}
import net.minecraft.network.chat.Component

class DynamicButtonComponent(initial: Text) : ButtonComponent(initial, {}) {
    private var provider: (() -> Text)? = null
    private var cache: Text by cached(initial) {
        message = it
    }

    fun text(provider: () -> Text) = apply {
        this.provider = provider
    }

    override fun getMessage(): Text {
        provider?.invoke()?.let { cache = it }
        return super.getMessage()
    }
}
