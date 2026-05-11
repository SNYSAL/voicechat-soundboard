@file:Suppress("unused")

package dev.kikugie.kowoui.access

import dev.kikugie.kowoui.util.CombinedAlignment
//? if =1.21.8 {
import io.wispforest.owo.ui.core.*
//?} else {
import io.wispforest.owo.ui.core.ParentUIComponent as ParentComponent
import io.wispforest.owo.ui.core.UIComponent as Component
import io.wispforest.owo.ui.core.HorizontalAlignment
import io.wispforest.owo.ui.core.Insets
import io.wispforest.owo.ui.core.Surface
import io.wispforest.owo.ui.core.Sizing
import io.wispforest.owo.ui.core.VerticalAlignment
import io.wispforest.owo.ui.core.AnimatableProperty
//?}

var ParentComponent.allowOverflow: Boolean
    get() = allowOverflow()
    set(value) {
        allowOverflow(value)
    }

var ParentComponent.padding: Insets
    get() = padding().get()
    set(value) {
        padding(value)
    }

var ParentComponent.surface: Surface
    get() = surface()
    set(value) {
        surface(value)
    }

var ParentComponent.alignment: CombinedAlignment
    get() = CombinedAlignment.of(horizontalAlignment, verticalAlignment)
    set(value) {
        horizontalAlignment = value.horizontal
        verticalAlignment = value.vertical
    }

var ParentComponent.horizontalAlignment: HorizontalAlignment
    get() = horizontalAlignment()
    set(value) {
        horizontalAlignment(value)
    }

var ParentComponent.verticalAlignment: VerticalAlignment
    get() = verticalAlignment()
    set(value) {
        verticalAlignment(value)
    }

val ParentComponent.animatablePadding: AnimatableProperty<Insets>
    get() = padding()
val ParentComponent.children: List<Component>
    get() = children()