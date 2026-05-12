@file:Suppress("unused")

package dev.kikugie.kowoui.access

import dev.kikugie.kowoui.unsupported
import io.wispforest.owo.ui.core.*
//? if <26.0 {
//?} else {
import io.wispforest.owo.ui.core.UIComponent as UIComponent
import io.wispforest.owo.ui.core.ParentUIComponent as ParentComponent
//?}
import io.wispforest.owo.ui.util.FocusHandler
import net.minecraft.client.gui.tooltip.TooltipComponent
import net.minecraft.network.chat.Component as TextComponent

//? if <26.0 {
var Component.cursorStyle: CursorStyle
    get() = cursorStyle()
    set(value) {
        cursorStyle(value)
    }

var Component.horizontalSizing: Sizing
    get() = horizontalSizing().get()
    set(value) {
        horizontalSizing(value)
    }

var Component.id: String?
    get() = id()
    set(value) {
        id(value)
    }

var Component.margins: Insets
    get() = margins().get()
    set(value) {
        margins(value)
    }

var Component.positioning: Positioning
    get() = positioning().get()
    set(value) {
        positioning(value)
    }

var Component.sizing: Sizing
    @Deprecated("Getter unavailable", level = DeprecationLevel.ERROR)
    get() = unsupported { "Getter unavailable" }
    set(value) {
        sizing(value)
    }

var Component.tooltip: List<TooltipComponent>?
    get() = tooltip()
    set(value) {
        tooltip(value)
    }

var Component.tooltipText: Component
    @Deprecated("Getter unavailable", level = DeprecationLevel.ERROR)
    get() = unsupported { "Getter unavailable" }
    set(value) {
        tooltip(value)
    }

var Component.tooltipTexts: Collection<Component>
    @Deprecated("Getter unavailable", level = DeprecationLevel.ERROR)
    get() = unsupported { "Getter unavailable" }
    set(value) {
        tooltip(value)
    }

var Component.verticalSizing: Sizing
    get() = verticalSizing().get()
    set(value) {
        verticalSizing(value)
    }

var Component.zIndex: Int
    get() = zIndex()
    set(value) {
        zIndex(value)
    }

val Component.animatableHorizontalSizing: AnimatableProperty<Sizing>
    get() = horizontalSizing()
val Component.animatableMargins: AnimatableProperty<Insets>
    get() = margins()
val Component.animatablePositioning: AnimatableProperty<Positioning>
    get() = positioning()
val Component.animatableVerticalSizing: AnimatableProperty<Sizing>
    get() = verticalSizing()
val Component.focusHandler: FocusHandler?
//?} else {
var UIComponent.cursorStyle: CursorStyle
    get() = cursorStyle()
    set(value) {
        cursorStyle(value)
    }

var UIComponent.horizontalSizing: Sizing
    get() = horizontalSizing().get()
    set(value) {
        horizontalSizing(value)
    }

var UIComponent.id: String?
    get() = id()
    set(value) {
        id(value)
    }

var UIComponent.margins: Insets
    get() = margins().get()
    set(value) {
        margins(value)
    }

var UIComponent.positioning: Positioning
    get() = positioning().get()
    set(value) {
        positioning(value)
    }

var UIComponent.sizing: Sizing
    @Deprecated("Getter unavailable", level = DeprecationLevel.ERROR)
    get() = unsupported { "Getter unavailable" }
    set(value) {
        sizing(value)
    }

var UIComponent.tooltip: List<TooltipComponent>?
    get() = tooltip()
    set(value) {
        tooltip(value)
    }

var UIComponent.tooltipText: TextComponent
    @Deprecated("Getter unavailable", level = DeprecationLevel.ERROR)
    get() = unsupported { "Getter unavailable" }
    set(value) {
        tooltip(value)
    }

var UIComponent.tooltipTexts: Collection<TextComponent>
    @Deprecated("Getter unavailable", level = DeprecationLevel.ERROR)
    get() = unsupported { "Getter unavailable" }
    set(value) {
        tooltip(value)
    }

var UIComponent.verticalSizing: Sizing
    get() = verticalSizing().get()
    set(value) {
        verticalSizing(value)
    }

var UIComponent.zIndex: Int
    get() = 0
    set(value) {
    }

val UIComponent.animatableHorizontalSizing: AnimatableProperty<Sizing>
    get() = horizontalSizing()
val UIComponent.animatableMargins: AnimatableProperty<Insets>
    get() = margins()
val UIComponent.animatablePositioning: AnimatableProperty<Positioning>
    get() = positioning()
val UIComponent.animatableVerticalSizing: AnimatableProperty<Sizing>
    get() = verticalSizing()
val UIComponent.focusHandler: FocusHandler?
//?}
    get() = focusHandler()
val Component.hasParent: Boolean
    get() = hasParent()
val Component.parent: ParentComponent?
    get() = parent()
val Component.root: ParentComponent?
    get() = root()

//? if <26.0 {
val Component.x: Int
    get() = x()
val Component.y: Int
    get() = y()
val Component.width: Int
    get() = width()
val Component.height: Int
    get() = height()
//?} else {
val Component.x: Int
    get() = x()
val Component.y: Int
    get() = y()
val Component.width: Int
    get() = width()
val Component.height: Int
    get() = height()
//?}




