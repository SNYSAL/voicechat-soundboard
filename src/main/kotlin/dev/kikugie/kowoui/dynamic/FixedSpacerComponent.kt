package dev.kikugie.kowoui.dynamic

import dev.kikugie.kowoui.access.sizing
//? if =1.21.8 {
import io.wispforest.owo.ui.base.BaseComponent
import io.wispforest.owo.ui.core.OwoUIDrawContext
//?} else {
import io.wispforest.owo.ui.base.BaseUIComponent as BaseComponent
import io.wispforest.owo.ui.core.OwoUIGraphics as OwoUIDrawContext
//?}
import io.wispforest.owo.ui.core.Sizing.fixed

class FixedSpacerComponent(pixels: Int) : BaseComponent() {
    init {
        sizing = fixed(pixels)
    }

    override fun draw(context: OwoUIDrawContext?, mouseX: Int, mouseY: Int, partialTicks: Float, delta: Float) {
    }
}