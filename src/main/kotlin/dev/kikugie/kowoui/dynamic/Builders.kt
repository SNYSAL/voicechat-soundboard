package dev.kikugie.kowoui.dynamic

//? if =1.21.8 {
import io.wispforest.owo.ui.core.Component
import net.minecraft.network.chat.Component as ButtonText
//?} else {
import io.wispforest.owo.ui.core.UIComponent as Component
import net.minecraft.client.gui.components.Button.Text as ButtonText
//?}
import net.minecraft.network.chat.Component

inline infix fun <T : Component> T.wrap(build: WrapperContainer<T>.() -> Unit) =
    WrapperContainer(this).apply(build)

@JvmOverloads inline fun fixedSpacer(pixels: Int, build: FixedSpacerComponent.() -> Unit = {}) =
    FixedSpacerComponent(pixels).apply(build)

//? if =1.21.8 {
@JvmOverloads inline fun dynamicButton(text: Text = Text.empty(), build: DynamicButtonComponent.() -> Unit = {}) =
    DynamicButtonComponent(text).apply(build)
//?} else {
@JvmOverloads inline fun dynamicButton(text: Text = Text.empty(), build: DynamicButtonComponent.() -> Unit = {}) =
    DynamicButtonComponent(text).apply(build)
//?}

@JvmOverloads inline fun dynamicLabel(text: Text = Text.empty(), build: DynamicLabelComponent.() -> Unit = {}) =
    DynamicLabelComponent(text).apply(build)

@JvmOverloads inline fun coloredTextBox(build: ColoredTextComponent.() -> Unit = {}) =
    ColoredTextComponent().apply(build)
