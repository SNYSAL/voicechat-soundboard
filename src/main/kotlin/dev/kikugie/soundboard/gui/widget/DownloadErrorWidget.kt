package dev.kikugie.soundboard.gui.widget

import dev.kikugie.kowoui.dynamic.wrap
import dev.kikugie.kowoui.entity
import dev.kikugie.kowoui.experimental.plusAssign
import dev.kikugie.kowoui.label
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.Insets.of
import io.wispforest.owo.ui.core.Sizing.*
import io.wispforest.owo.ui.core.Surface
import net.minecraft.world.entity.EntityType
//? if <26.0 {
import net.minecraft.nbt.CompoundTag
//?} else {
import net.minecraft.nbt.NbtCompound
//?}
import net.minecraft.network.chat.Component as Text

class DownloadErrorWidget(message: Text) : FlowLayout(expand(), content(), Algorithm.HORIZONTAL) {
    init {
        surface(Surface.PANEL)
        //? if <26.0 {
        padding(of(5))
        //?}
        gap(5)
        //? if <26.0 {
        this += cat()
        this += label(message) {
            horizontalSizing = expand()
        }
        //?} else {
        child(label(message) {
            horizontalSizing().set(expand())
        })
        //?}
    }

    //? if <26.0 {
    private fun cat() = entity(
        EntityType.CAT,
        CompoundTag().apply {
            putString("variant", "all_black")
            putBoolean("Sitting", true)
        }
    ) {
        sizing(fill())
    }
    //?}
}



