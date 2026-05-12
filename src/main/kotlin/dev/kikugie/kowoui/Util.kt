@file:Suppress("unused")

package dev.kikugie.kowoui

//? if <26.0 {
import io.wispforest.owo.ui.core.Component
import io.wispforest.owo.ui.base.BaseComponent
import io.wispforest.owo.ui.inject.GreedyInputComponent
import net.minecraft.network.chat.Component as ButtonText
//?} else {
import io.wispforest.owo.ui.core.UIComponent as Component
import io.wispforest.owo.ui.core.ParentUIComponent as ParentComponent
import io.wispforest.owo.ui.base.BaseUIComponent as BaseComponent
import io.wispforest.owo.ui.inject.GreedyInputUIComponent as GreedyInputComponent
import net.minecraft.client.gui.components.Button.Component as ButtonText
//?}
import io.wispforest.owo.ui.core.Insets
import io.wispforest.owo.ui.parsing.UIModel
import net.minecraft.network.chat.Component
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal inline fun unsupported(reason: () -> String = { "" }): Nothing =
    throw UnsupportedOperationException(reason())

fun String.Component(): Component = Component.of(this)
fun String.translation(vararg args: String): Component = Component.translatable(this, *args)
fun String.fallbackTranslation(fallback: String, vararg args: Any): Component = Component.translatableWithFallback(this, fallback, *args)

inline fun <reified T : Component> ParentComponent.childById(id: String): T? =
    childById(T::class.java, id)

inline fun <reified T : Component> UIModel.template(
    name: String,
    params: Map<String, String> = emptyMap(),
): T = expandTemplate(T::class.java, name, params)

operator fun Insets.plus(other: Insets): Insets = add(other.top, other.bottom, other.left, other.right)

fun <T> cached(value: T, consumer: (T) -> Unit) = Cached(value, consumer)

class Cached<T>(private var value: T, private val consumer: (T) -> Unit) : ReadWriteProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, new: T) {
        if (value == new) return
        value = new
        consumer(value)
    }
}



