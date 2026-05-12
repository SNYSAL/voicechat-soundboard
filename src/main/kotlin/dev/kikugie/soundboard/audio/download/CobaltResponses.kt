package dev.kikugie.soundboard.audio.download

import net.minecraft.network.chat.Component

sealed interface CobaltResponse

class StreamResponse(val url: String) : CobaltResponse
class ErrorResponse(val cause: Component) : CobaltResponse



