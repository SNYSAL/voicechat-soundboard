package dev.kikugie.soundboard

import dev.kikugie.soundboard.audio.BASE_DIR
import dev.kikugie.soundboard.audio.data.SoundEntry
import dev.kikugie.soundboard.audio.Registry.ResourceAudioHolder
import dev.kikugie.soundboard.audio.Registry.SoundRegistry
import dev.kikugie.soundboard.config.AudioConfig
import dev.kikugie.soundboard.config.SoundboardConfig
import dev.kikugie.soundboard.entrypoint.SoundboardAccess
import dev.kikugie.soundboard.gui.Screen.SoundBrowser
import dev.kikugie.soundboard.util.client
import dev.kikugie.soundboard.util.idOf
import dev.kikugie.soundboard.util.shiftDown
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import com.mojang.blaze3d.platform.InputConstants
import net.minecraft.resource.ResourceType
import org.lwjgl.glfw.GLFW
import kotlin.io.path.createDirectories

object Soundboard {
    private var ready = false
    val config = SoundboardConfig.load()

    fun initialize() {
        if (ready) return
        ready = true

        AudioConfig // Inits the object
        BASE_DIR.createDirectories()
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(ResourceAudioHolder)

        ModKeyBinds.keybind(GLFW.GLFW_KEY_J, "browser") {
            inGame { SoundBrowser.open() }
            inGui {
                if (this is SoundBrowser) {
                    if (settings != null) closeSettings()
                    else close()
                }
            }
        }
        ModKeyBinds.keybind(GLFW.GLFW_KEY_U, "cancel") {
            val reset = { SoundboardAccess.forEach { scheduler.reset() } }
            inGame { reset() }
            inGui { reset() }
        }
        ModKeyBinds.keybind(GLFW.GLFW_KEY_O, "play") {
            val favourites = config.favourites
            val numrow = GLFW.GLFW_KEY_1..GLFW.GLFW_KEY_9
            val numpad = GLFW.GLFW_KEY_KP_1..GLFW.GLFW_KEY_KP_9
            fun get(): SoundEntry? {
                //? if <26.0 {
                val window = client.window.handle
                //?} else {
                val window = client.window
                //?}
                // 0 to 9
                val index = when {
                    // 0 converts to 9 because it's the last on the number row
                    InputConstants.isKeyDown(window, GLFW.GLFW_KEY_0) ||
                    InputConstants.isKeyDown(window, GLFW.GLFW_KEY_KP_0) -> 9

                    else -> numrow.firstNotNullOfOrNull {
                        if (InputConstants.isKeyDown(window, it)) it - GLFW.GLFW_KEY_1 else null
                    } ?: numpad.firstNotNullOfOrNull {
                        if (InputConstants.isKeyDown(window, it)) it - GLFW.GLFW_KEY_KP_1 else null
                    } ?: return null
                }
                if (index >= favourites.size) return null
                SoundRegistry.update()
                return SoundRegistry.favourites[favourites[index]]
            }
            ClientTickEvents.END_CLIENT_TICK.register {
                if (keybind.isDown && favourites.isNotEmpty()) get()?.run {
                    println("Playing ${id.str}")
                    SoundboardAccess.active?.schedule(this, shiftDown)
                    keybind.isDown = false
                }
            }
        }

        ResourceManagerHelper.registerBuiltinResourcePack(
            idOf("default"),
            FabricLoader.getInstance().getModContainer("soundboard-core").get(),
            ResourcePackActivationType.NORMAL
        )
    }
}



