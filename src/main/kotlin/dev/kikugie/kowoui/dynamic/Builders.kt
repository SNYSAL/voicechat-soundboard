package dev.kikugie.kowoui.dynamic

//? if <26.0 {
import io.wispforest.owo.ui.core.Component as UIComponent
import net.minecraft.network.chat.Component as ButtonText
//?} else {
import io.wispforest.owo.ui.core.UIComponent as UIComponent
import net.minecraft.client.gui.components.Button.Component as ButtonText
//?}
import net.minecraft.network.chat.Component as TextComponent

inline infix fun <T : UIComponent> T.wrap(build: WrapperContainer<T>.() -> Unit) =
    WrapperContainer(this).apply(build)

@JvmOverloads inline fun fixedSpacer(pixels: Int, build: FixedSpacerComponent.() -> Unit = {}) =
    FixedSpacerComponent(pixels).apply(build)

//? if <26.0 {
@JvmOverloads inline fun dynamicButton(Component: UIComponent = UIComponent.empty(), build: DynamicButtonComponent.() -> Unit = {}) =
    DynamicButtonComponent(Component).apply(build)
//?} else {
@JvmOverloads inline fun dynamicButton(Component: UIComponent = UIComponent.empty(), build: DynamicButtonComponent.() -> Unit = {}) =
    DynamicButtonComponent(Component).apply(build)
//?}

@JvmOverloads inline fun dynamicLabel(Component: UIComponent = UIComponent.empty(), build: DynamicLabelComponent.() -> Unit = {}) =
    DynamicLabelComponent(Component).apply(build)

@JvmOverloads inline fun coloredTextBox(build: ColoredTextComponent.() -> Unit = {}) =
    ColoredTextComponent().apply(build)



