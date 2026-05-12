package dev.kikugie.soundboard

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.KeyMapping.v1.KeyBindingHelper
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.KeyMapping
import com.mojang.blaze3d.platform.InputConstants

object ModKeyBinds {
    private val keybinds = mutableMapOf<String, KeyBuilder>()

    @JvmStatic
    operator fun get(name: String): KeyMapping? = keybinds[name]?.keybind
    fun keybind(key: Int, name: String, action: KeyBuilder.() -> Unit) {
        //? if <26.0 {
        val bind = KeyBindingHelper.registerKeyBinding(KeyMapping("soundboard.keybinds.$name", key, "soundboard.title"))
        //?} else {
        val bind = KeyBindingHelper.registerKeyBinding(KeyMapping("soundboard.keybinds.$name", InputConstants.Type.KEYSYM, key, "soundboard.title"))
        //?}
        val builder = KeyBuilder(bind).apply(action)
        keybinds[name] = builder
        ClientTickEvents.END_CLIENT_TICK.register {
            if (bind.consumeClick()) builder.inGame?.invoke()
        }
    }

    internal fun invoke(Screen: Screen) = keybinds.values.forEach {
        it.inGui?.invoke(Screen)
    }

    class KeyBuilder(
        val keybind: KeyMapping,
    ) {
        internal var inGame: (() -> Unit)? = null
        internal var inGui: ((Screen) -> Unit)? = null

        fun inGame(action: (KeyMapping) -> Unit) {
            inGame = { action(keybind) }
        }

        fun inGui(action: Screen.(KeyMapping) -> Unit) {
            inGui = { it.action(keybind) }
        }
    }
}



