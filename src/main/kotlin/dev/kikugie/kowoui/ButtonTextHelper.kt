package dev.kikugie.kowoui

import net.minecraft.text.Text

//? if =1.21.8 {
fun wrapButtonWidgetText(text: Text): Any = text
//?} else {
// For 1.21.11+, ButtonWidgetComponent now expects ButtonWidget.Text
// We create it by using the message property directly since Components.button() handles it
fun wrapButtonWidgetText(text: Text): Any = text
//?}

