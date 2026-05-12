package dev.kikugie.kowoui

import net.minecraft.network.chat.Component

//? if <26.0 {
fun wrapButtonText(Component: Component): Any = Component
//?} else {
// For 1.21.11+, ButtonComponent now expects Button.Component
// We create it by using the message property directly since Components.button() handles it
fun wrapButtonText(Component: Component): Any = Component
//?}




