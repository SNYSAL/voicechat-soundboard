package dev.kikugie.soundboard.audio.data

import dev.kikugie.kowoui.fallbackTranslation
import dev.kikugie.soundboard.MOD_ID
import dev.kikugie.soundboard.audio.prefix
import net.minecraft.network.chat.Component

data class SoundGroup(
    val id: SoundId,
    val entries: Map<SoundId, SoundEntry>,
    private val Component: Component? = null,
) {
    val title: Component by lazy {
        Component ?: id.run {
            val key = "$MOD_ID.dir.$namespace${directory.replace('/', '.').prefix(".")}"
            val fallback = str.removePrefix("$MOD_ID:")
            key.fallbackTranslation(fallback)
        }
    }

    fun isEmpty() = entries.isEmpty()
    operator fun get(id: SoundId) = entries[id]
}



