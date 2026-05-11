package dev.kikugie.soundboard.util

import dev.kikugie.soundboard.MOD_ID
import kotlinx.coroutines.*
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Util
import net.minecraft.util.math.Vec3d
//? if !=1.21.8 {
import org.lwjgl.glfw.GLFW
//?}
import java.nio.file.Path
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.*

typealias Property<T> = KMutableProperty0<T>

fun idOf(path: String): ResourceLocation = ResourceLocation.of(MOD_ID, path)
fun idOf(namespace: String, path: String) = ResourceLocation.of(namespace, path)

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
    Util.getOperatingSystem().open(this)
}

val client = MinecraftClient.getInstance()

//? if =1.21.8 {
val shiftDown: Boolean get() = Screen.hasShiftDown()
val ctrlDown: Boolean get() = Screen.hasControlDown()
val altDown: Boolean get() = Screen.hasAltDown()
//?} else {
val shiftDown: Boolean get() = GLFW.glfwGetKey(client.window.handle, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS || GLFW.glfwGetKey(client.window.handle, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS
val ctrlDown: Boolean get() = GLFW.glfwGetKey(client.window.handle, GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS || GLFW.glfwGetKey(client.window.handle, GLFW.GLFW_KEY_RIGHT_CONTROL) == GLFW.GLFW_PRESS
val altDown: Boolean get() = GLFW.glfwGetKey(client.window.handle, GLFW.GLFW_KEY_LEFT_ALT) == GLFW.GLFW_PRESS || GLFW.glfwGetKey(client.window.handle, GLFW.GLFW_KEY_RIGHT_ALT) == GLFW.GLFW_PRESS
//?}

var currentScreen
    get() = client.currentScreen
    set(value) {
        client.setScreen(value)
    }

operator fun Vec3d.plus(other: Vec3d) = add(other)
