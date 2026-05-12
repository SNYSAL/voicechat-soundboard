package dev.kikugie.kowoui.experimental

//? if <26.0 {
import io.wispforest.owo.ui.core.Component
//?} else {
import io.wispforest.owo.ui.core.UIComponent as Component
//?}

interface AppendableParentComponent {
    fun <C : Component> addChild(component: C)
    fun <C : Component> addChildren(components: Iterable<C>)
}



