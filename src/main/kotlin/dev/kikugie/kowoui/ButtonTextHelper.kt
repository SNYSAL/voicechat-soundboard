package dev.kikugie.kowoui

import net.minecraft.text.Text

//? if =1.21.8 {
fun wrapButtonText(text: Text): Any = text
//?} else {
// For 1.21.11+, ButtonComponent now expects ButtonWidget.Text
// We create it by using the message property directly since Components.button() handles it
fun wrapButtonText(text: Text): Any = text
//?}
