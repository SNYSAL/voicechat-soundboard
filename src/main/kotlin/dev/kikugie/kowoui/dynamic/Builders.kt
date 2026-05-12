package dev.kikugie.kowoui.dynamic

//? if <26.0 {
import io.wispforest.owo.ui.core.Component
import net.minecraft.network.chat.Component as ButtonText
//?} else {
import io.wispforest.owo.ui.core.UIComponent as Component
import net.minecraft.client.gui.components.Button.Component as ButtonText
//?}
import net.minecraft.network.chat.Component

inline infix fun <T : Component> T.wrap(build: WrapperContainer<T>.() -> Unit) =
    WrapperContainer(this).apply(build)

@JvmOverloads inline fun fixedSpacer(pixels: Int, build: FixedSpacerComponent.() -> Unit = {}) =
    FixedSpacerComponent(pixels).apply(build)

//? if <26.0 {
@JvmOverloads inline fun dynamicButton(Component: Component = Component.empty(), build: DynamicButtonComponent.() -> Unit = {}) =
    DynamicButtonComponent(Component).apply(build)
//?} else {
@JvmOverloads inline fun dynamicButton(Component: Component = Component.empty(), build: DynamicButtonComponent.() -> Unit = {}) =
    DynamicButtonComponent(Component).apply(build)
//?}

@JvmOverloads inline fun dynamicLabel(Component: Component = Component.empty(), build: DynamicLabelComponent.() -> Unit = {}) =
    DynamicLabelComponent(Component).apply(build)

@JvmOverloads inline fun coloredTextBox(build: ColoredTextComponent.() -> Unit = {}) =
    ColoredTextComponent().apply(build)



