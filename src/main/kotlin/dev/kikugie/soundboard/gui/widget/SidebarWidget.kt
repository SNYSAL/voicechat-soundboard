package dev.kikugie.soundboard.gui.widget

import com.mojang.blaze3d.systems.RenderSystem
import dev.kikugie.kowoui.access.renderer
import dev.kikugie.kowoui.access.sizing
import dev.kikugie.kowoui.access.tooltipText
import dev.kikugie.kowoui.button
import dev.kikugie.kowoui.experimental.plusAssign
import dev.kikugie.kowoui.translation
import dev.kikugie.soundboard.gui.Screen.DownloadScreen
import dev.kikugie.soundboard.gui.Screen.SoundBrowser
import dev.kikugie.soundboard.util.currentScreen
import dev.kikugie.soundboard.util.idOf
import io.wispforest.owo.ui.component.ButtonComponent.Renderer.texture
import io.wispforest.owo.ui.container.FlowLayout
//? if >=26.0 {
import net.minecraft.util.Identifier
//?}
import io.wispforest.owo.ui.core.Sizing.content
import io.wispforest.owo.ui.core.Sizing.fixed
import net.minecraft.client.gui.screens.Screen
import kotlin.reflect.full.createInstance

class SidebarWidget(private val current: Screen) : FlowLayout(fixed(16), content(), Algorithm.VERTICAL) {
    companion object {
        val WIDGETS = idOf("textures/gui/widgets.png")
    }

    init {
        gap(2)
        this += tab<SoundBrowser>("browser", 0)
        this += tab<DownloadScreen>("downloader", 1)
    }

    private inline fun <reified T : Screen> tab(name: String, index: Int) = button {
        id = name
        sizing = fixed(16)
        //? if <26.0 {
        renderer = texture(WIDGETS, index * 16, 0, 256, 256)
        //?} else {
        renderer = texture(WIDGETS, index * 16, 0, 256, 256)
        //?}
        active = current !is T
        tooltipText = "soundboard.$name".translation()
        onPress { RenderSystem.queueFencedTask { currentScreen = T::class.createInstance() } }
    }
}



