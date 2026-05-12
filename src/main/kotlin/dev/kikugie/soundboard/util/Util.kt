package dev.kikugie.soundboard.util

import dev.kikugie.soundboard.MOD_ID
import kotlinx.coroutines.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.Screen
//? if <26.0 {
import net.minecraft.resources.ResourceLocation
//?} else {
import net.minecraft.util.Identifier as ResourceLocation
//?}
import net.minecraft.util.Util
import net.minecraft.world.phys.Vec3
import java.nio.file.Path
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.*
//? if !=1.21.8 {
import org.lwjgl.glfw.GLFW
//?}

typealias Property<T> = KMutableProperty0<T>

//? if <26.0 {
fun idOf(path: String): ResourceLocation = ResourceLocation(MOD_ID, path)
fun idOf(namespace: String, path: String) = ResourceLocation(namespace, path)
//?} else {
// In 26.1, ResourceLocation is Identifier
fun idOf(path: String): ResourceLocation = ResourceLocation.of(MOD_ID, path)
fun idOf(namespace: String, path: String) = ResourceLocation.of(namespace, path)
//?}

@OptIn(DelicateCoroutinesApi::class)
fun runOn(context: CoroutineContext, action: suspend CoroutineScope.() -> Unit) {
    GlobalScope.launch(context, block = action)
}

inline fun ShortArray.reassign(transform: (Short) -> Short) {
    for (i in indices) this[i] = transform(this[i])
}

inline infix fun Boolean.then(action: () -> Unit): Boolean {
    if (this) action()
    return this
}

fun Class<*>.accurateName(): String = simpleName.ifEmpty {
    val parent = if (this == Any::class.java) "Object" else superclass.accurateName()
    "out $parent"
}

fun Path.resolveOrNull(string: String) = runCatching { resolve(string) }.getOrNull()

fun Path.navigate() {
    //? if <26.0 {
    Util.getOperatingSystem().open(this)
    //?} else {
    @Suppress("UNUSED_EXPRESSION") Unit // navigate not yet implemented for 26.1
    //?}
}

val client = Minecraft.getInstance()

//? if <26.0 {
val shiftDown: Boolean get() = Screen.hasShiftDown()
val ctrlDown: Boolean get() = Screen.hasControlDown()
val altDown: Boolean get() = Screen.hasAltDown()
//?} else {
val shiftDown: Boolean get() = false // TODO: Implement for 26.1
val ctrlDown: Boolean get() = false // TODO: Implement for 26.1
val altDown: Boolean get() = false // TODO: Implement for 26.1
//?}


//? if <26.0 {
var currentScreen
    get() = client.currentScreen
    set(value) {
        client.setScreen(value)
    }
//?} else {
var currentScreen: Screen?
    get() = null  // TODO: Implement for 26.1
    set(value) {
        // TODO: Implement for 26.1
    }
//?}

operator fun Vec3.plus(other: Vec3) = add(other)



