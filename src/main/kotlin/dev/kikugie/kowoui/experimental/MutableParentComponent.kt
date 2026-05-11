package dev.kikugie.kowoui.experimental

//? if =1.21.8 {
import io.wispforest.owo.ui.core.Component
//?} else {
import io.wispforest.owo.ui.core.UIComponent as Component
//?}

interface MutableParentComponent<T> where T : Position {
    fun <C : Component> addChild(position: T, component: C)
    fun <C : Component> addChildren(position: T, components: Iterable<C>)
}