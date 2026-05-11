package dev.kikugie.kowoui.dynamic

import dev.kikugie.kowoui.cached
import dev.kikugie.kowoui.wrapButtonText
import io.wispforest.owo.ui.component.ButtonComponent
//? if =1.21.8 {
import io.wispforest.owo.ui.core.OwoUIDrawContext
//?} else {
import io.wispforest.owo.ui.core.OwoUIGraphics as OwoUIDrawContext
//?}
import net.minecraft.text.Text

//? if =1.21.8 {
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
//?} else {
class DynamicButtonComponent(initial: Text) : ButtonComponent(wrapButtonText(initial) as net.minecraft.client.gui.widget.ButtonWidget.Text, {}) {
    private var provider: (() -> Text)? = null
    private var cache: Text by cached(initial) {
        message(wrapButtonText(it) as net.minecraft.client.gui.widget.ButtonWidget.Text)
    }

    fun text(provider: () -> Text) = apply {
        this.provider = provider
    }

    override fun getMessage(): net.minecraft.client.gui.widget.ButtonWidget.Text {
        provider?.invoke()?.let { cache = it }
        return super.getMessage()
    }
}
//?}