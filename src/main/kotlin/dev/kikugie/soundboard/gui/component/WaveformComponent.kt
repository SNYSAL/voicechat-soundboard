package dev.kikugie.soundboard.gui.component

import dev.kikugie.soundboard.util.drawLinePrecise
import dev.kikugie.soundboard.util.volumeScale
//? if =1.21.8 {
import io.wispforest.owo.ui.base.BaseComponent
import io.wispforest.owo.ui.core.OwoUIDrawContext
//?} else {
import io.wispforest.owo.ui.base.BaseUIComponent as BaseComponent
import io.wispforest.owo.ui.core.OwoUIGraphics as OwoUIDrawContext
//?}
import io.wispforest.owo.ui.core.Color
import io.wispforest.owo.ui.core.Sizing
import io.wispforest.owo.ui.core.Insets
import kotlin.math.absoluteValue
import kotlin.properties.Delegates.observable

class WaveformComponent(
    private val data: ShortArray,
    scalar: Double = 1.0
) : BaseComponent() {
    var thickness: Double = 1.0
    var color: Color = Color.ofRgb(0x1976D2)
    var mult: Double by observable(scalar) {_, _, _ ->
        update()
    }
    private var lastHeight: Int = 0
    private var lines: IntArray = intArrayOf()

    init {
        //? if =1.21.8 {
        sizing(Sizing.fill())
        //?} else {
        sizing(Sizing.fill(), Sizing.fill())
        //?}
    }

    override fun draw(context: OwoUIDrawContext, mouseX: Int, mouseY: Int, partialTicks: Float, delta: Float) {
        if (lines.isEmpty()) return
        val local = margins.get()
        var drawX = x + local.left + .5
        val drawY = y + local.top + .0
        for (i in 0..<lines.lastIndex) context.drawLinePrecise(
            drawX,
            drawY + lines[i],
            ++drawX,
            drawY + lines[i + 1],
            thickness,
            color
        )
    }

    private fun update() {
        val compressed = compress(data, width)
        val max = compressed.maxBy { it.absoluteValue }.absoluteValue
        lines = compressed.map {
            point((it * mult.volumeScale).coerceIn(-max, max), max)
        }.toIntArray()
        lastHeight = height
    }

    private fun compress(array: ShortArray, length: Int): DoubleArray {
        val window = array.size / length.toDouble()
        return DoubleArray(length) {
            val start = (it * window).toInt()
            val end = ((it + 1) * window).toInt().coerceAtMost(data.size)
            array.copyOfRange(start, end).average()
        }
    }

    private fun point(it: Double, max: Double): Int {
        val halfHeight = height / 2F
        val lineHeight = it / max * halfHeight
        return (halfHeight - lineHeight).toInt()
    }
}