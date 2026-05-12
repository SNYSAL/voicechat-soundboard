package dev.kikugie.kowoui.dynamic

import dev.kikugie.kowoui.cached
import io.wispforest.owo.ui.component.ButtonComponent
//? if <26.0 {
import io.wispforest.owo.ui.core.OwoUIDrawContext
//?} else {
import io.wispforest.owo.ui.core.OwoUIGraphics as OwoUIDrawContext
//?}
import net.minecraft.network.chat.Component

class DynamicButtonComponent(initial: Component) : ButtonComponent(initial, {}) {
    private var provider: (() -> Component)? = null
    private var cache: Component by cached(initial) {
        message = it
    }

    fun Component(provider: () -> Component) = apply {
        this.provider = provider
    }

    override fun getMessage(): Component {
        provider?.invoke()?.let { cache = it }
        return super.getMessage()
    }
}



