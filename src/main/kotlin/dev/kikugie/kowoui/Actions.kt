@file:Suppress("unused")

package dev.kikugie.kowoui

import io.wispforest.owo.ui.component.*
import io.wispforest.owo.ui.container.CollapsibleContainer
import io.wispforest.owo.ui.core.Color
//? if <26.0 {
import io.wispforest.owo.ui.core.Component
import io.wispforest.owo.ui.core.Component.FocusSource
//?} else {
import io.wispforest.owo.ui.core.UIComponent as Component
import io.wispforest.owo.ui.core.UIComponent.FocusSource
//?}
import io.wispforest.owo.ui.event.*

fun <T : SlimSliderComponent> T.onChange(action: (Double) -> Unit) =
    this.also { onChanged().subscribe(SlimSliderComponent.OnChanged(action)) }

fun <T : ColorPickerComponent> T.onChange(action: (Color) -> Unit) =
    this.also { onChanged().subscribe(ColorPickerComponent.OnChanged(action)) }

fun <T : SmallCheckboxComponent> T.onChange(action: (Boolean) -> Unit) =
    this.also { onChanged().subscribe(SmallCheckboxComponent.OnChanged(action)) }

fun <T : SliderComponent> T.onChange(action: (Double) -> Unit) =
    this.also { onChanged().subscribe(SliderComponent.OnChanged(action)) }

fun <T : TextAreaComponent> T.onChange(action: (String) -> Unit) =
    this.also { onChanged().subscribe(TextAreaComponent.OnChanged(action)) }

fun <T : TextBoxComponent> T.onChange(action: (String) -> Unit) =
    this.also { onChanged().subscribe(TextBoxComponent.OnChanged(action)) }

//? if <26.0 {
fun <T : Component> T.onCharType(action: (Char, Int) -> Boolean) =
    this.also { charTyped().subscribe(CharTyped(action)) }

fun <T : Component> T.onFocusGain(action: (FocusSource) -> Unit) =
    this.also { focusGained().subscribe(FocusGained(action)) }

fun <T : Component> T.onFocusLose(action: () -> Unit) =
    this.also { focusLost().subscribe(FocusLost(action)) }

fun <T : Component> T.onKeyPress(action: (Int, Int, Int) -> Boolean) =
    this.also { keyPress().subscribe(KeyPress(action)) }

fun <T : Component> T.onMouseDown(action: (Double, Double, Int) -> Boolean) =
    this.also { mouseDown().subscribe(MouseDown(action)) }

fun <T : Component> T.onMouseDrag(action: (Double, Double, Double, Double, Int) -> Boolean) =
    this.also { mouseDrag().subscribe(MouseDrag(action)) }

fun <T : Component> T.onMouseEnter(action: () -> Unit) =
    this.also { mouseEnter().subscribe(MouseEnter(action)) }

fun <T : Component> T.onMouseLeave(action: () -> Unit) =
    this.also { mouseLeave().subscribe(MouseLeave(action)) }

fun <T : Component> T.onMouseScroll(action: (Double, Double, Double) -> Boolean) =
    this.also { mouseScroll().subscribe(MouseScroll(action)) }

fun <T : Component> T.onMouseUp(action: (Double, Double, Int) -> Boolean) =
    this.also { mouseUp().subscribe(MouseUp(action)) }
//?} else {
private fun Any.callInt(vararg names: String): Int {
    for (name in names) {
        val method = javaClass.methods.firstOrNull { it.name == name && it.parameterCount == 0 } ?: continue
        val value = method.invoke(this) ?: continue
        if (value is Number) return value.toInt()
    }
    error("Unable to extract int from ${javaClass.name}")
}

private fun Any.callChar(vararg names: String): Char {
    for (name in names) {
        val method = javaClass.methods.firstOrNull { it.name == name && it.parameterCount == 0 } ?: continue
        val value = method.invoke(this) ?: continue
        when (value) {
            is Char -> return value
            is Number -> return value.toInt().toChar()
            is String -> if (value.isNotEmpty()) return value[0]
        }
    }
    error("Unable to extract char from ${javaClass.name}")
}

fun <T : Component> T.onCharType(action: (Char, Int) -> Boolean) =
    this.also { charTyped().subscribe { input -> action(input.callChar("character", "char", "value"), input.callInt("modifiers", "mods", "modifiers") ) } }

fun <T : Component> T.onFocusGain(action: (FocusSource) -> Unit) =
    this.also { focusGained().subscribe { focusSource -> action(focusSource) } }

fun <T : Component> T.onFocusLose(action: () -> Unit) =
    this.also { focusLost().subscribe { action() } }

fun <T : Component> T.onKeyPress(action: (Int, Int, Int) -> Boolean) =
    this.also { keyPress().subscribe { input -> action(input.callInt("keyCode", "key", "code"), input.callInt("scanCode", "scan", "scanCode"), input.callInt("modifiers", "mods", "modifiers")) } }

fun <T : Component> T.onMouseDown(action: (Double, Double, Int) -> Boolean) =
    this.also { mouseDown().subscribe { click, doubled -> action(click.callInt("x", "mouseX").toDouble(), click.callInt("y", "mouseY").toDouble(), click.callInt("button", "buttonId")) } }

fun <T : Component> T.onMouseDrag(action: (Double, Double, Double, Double, Int) -> Boolean) =
    this.also { mouseDrag().subscribe { click, deltaX, deltaY -> action(click.callInt("x", "mouseX").toDouble(), click.callInt("y", "mouseY").toDouble(), click.callInt("x", "mouseX").toDouble() + deltaX, click.callInt("y", "mouseY").toDouble() + deltaY, click.callInt("button", "buttonId")) } }

fun <T : Component> T.onMouseEnter(action: () -> Unit) =
    this.also { mouseEnter().subscribe { action() } }

fun <T : Component> T.onMouseLeave(action: () -> Unit) =
    this.also { mouseLeave().subscribe { action() } }

fun <T : Component> T.onMouseScroll(action: (Double, Double, Double) -> Boolean) =
    this.also { mouseScroll().subscribe { mouseX, mouseY, amount -> action(mouseX, mouseY, amount) } }

fun <T : Component> T.onMouseUp(action: (Double, Double, Int) -> Boolean) =
    this.also { mouseUp().subscribe { click -> action(click.callInt("x", "mouseX").toDouble(), click.callInt("y", "mouseY").toDouble(), click.callInt("button", "buttonId")) } }
//?}

fun <T : SliderComponent> T.onSlideEnd(action: () -> Unit) =
    this.also { slideEnd().subscribe(SliderComponent.OnSlideEnd(action)) }

fun <T : SlimSliderComponent> T.onSlideEnd(action: () -> Unit) =
    this.also { onSlideEnd().subscribe(SlimSliderComponent.OnSlideEnd(action)) }



