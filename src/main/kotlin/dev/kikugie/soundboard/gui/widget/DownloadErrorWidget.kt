package dev.kikugie.soundboard.gui.widget

import dev.kikugie.kowoui.access.*
import dev.kikugie.kowoui.dynamic.wrap
import dev.kikugie.kowoui.entity
import dev.kikugie.kowoui.experimental.plusAssign
import dev.kikugie.kowoui.label
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.Insets.of
import io.wispforest.owo.ui.core.Sizing.*
import io.wispforest.owo.ui.core.Surface
import net.minecraft.world.entity.EntityType
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component

class DownloadErrorWidget(message: Component) : FlowLayout(expand(), content(), Algorithm.HORIZONTAL) {
    init {
        surface(Surface.PANEL)
        padding(of(5))
        gap(5)
        this += cat()
        this += label(message) {
            horizontalSizing = expand()
        }
    }

    private fun cat() = entity(
        EntityType.CAT,
        CompoundTag().apply {
            putString("variant", "all_black")
            putBoolean("Sitting", true)
        }
    ) {
        sizing = fill()
        lookAtCursor = true
    } wrap {
        sizing = fixed(64)
        surface = Surface.PANEL_INSET
        padding = of(1)
    }
}



