package dev.kikugie.kowoui

import net.minecraft.text.Text

//? if =1.21.8 {
fun wrapButtonText(text: Text): Text = text
//?} else {
fun wrapButtonText(text: Text): net.minecraft.client.gui.widget.ButtonWidget.Text = 
    net.minecraft.client.gui.widget.ButtonWidget.Text.of(text)
//?}
