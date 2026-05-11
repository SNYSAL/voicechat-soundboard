# Migration notes (alice-magic fork)

This fork ports [kikugie/voicechat-soundboard](https://github.com/kikugie/voicechat-soundboard)
from upstream's 1.21.4 multi-module layout to a single-jar
[Stonecutter](https://stonecutter.kikugie.dev/) build for **Minecraft 1.21.8 (Primary)** with
**partial support for 1.21.11 and 26.1.x** (requires additional testing and API fixes).

## What ships

- ✅ `1.21.8` — Fabric, Yarn mappings, Java 21. Builds and runs.
  Single jar with both SVC + Plasmo Voice entrypoints baked in.
- 🟡 `1.21.11` — Fabric, Yarn mappings, Java 21. **Config added, compilation in progress**
  Port to owo-lib 0.13's UIComponent rebrand (Component → UIComponent) partially complete.
  ~50 compile errors remain related to API signature changes in Surface, accessors, and type inference.
- 🟡 `26.1.x` — Fabric, Mojang Mappings (no yarn), Java 25. **Config added, not yet tested**
  Requires additional mojmap name translation beyond the owo-lib 0.13 changes.

## Work completed for 1.21.11 / 26.1 support

### Configuration (✅ Done)
- Added version blocks to `settings.gradle.kts`: `versions("1.21.8", "1.21.11")` and `version("26.1", "26.1.2")`
- Added dependency matrix to `stonecutter.properties.toml` with all resolved versions
- Updated `README.md` build status table

### Source patching (🟡 Partial)
Applied Stonecutter `//? if =1.21.8 {` conditional blocks to ~20 files:
- **Core imports**: `kowoui/Builders.kt`, `kowoui/Actions.kt`, `kowoui/Util.kt`, `kowoui/Setters.kt`
- **Experimental**: `kowoui/experimental/{AppendableParentComponent,MutableParentComponent,Setters}.kt`
- **Dynamic**: `kowoui/dynamic/{Builders,WrapperContainer}.kt`
- **Soundboard**: `soundboard/gui/component/FlexibleGridLayout.kt`, `soundboard/gui/screen/{ConfigScreen,ModScreen}.kt`, `soundboard/util/KOwoUi.kt`, `soundboard/util/Util.kt`

Pattern used: aliasing `UIComponent as Component` to minimize invasive changes:
```kotlin
//? if =1.21.8 {
import io.wispforest.owo.ui.core.Component
//? } else {
import io.wispforest.owo.ui.core.UIComponent as Component
//? }
```

### Keyboard API changes (✅ Done)
Handled `Screen.hasShiftDown() / hasControlDown() / hasAltDown()` removal in 1.21.11+:
```kotlin
//? if =1.21.8 {
val shiftDown: Boolean get() = Screen.hasShiftDown()
//? } else {
val shiftDown: Boolean get() = GLFW.glfwGetKey(...) == GLFW.GLFW_PRESS
//? }
```

## Remaining issues for 1.21.11 / 26.1

| Issue | Scope | Files affected | Estimate |
|-------|-------|-----------------|----------|
| Surface API changes (accessor methods) | owo-lib 0.13 | `kowoui/access/Parents.kt` and callers | ~20 errors |
| Type inference failures in `let` chains | Type system | Multiple GUI builders | ~30 errors |
| ButtonWidget.Text vs Text mismatch | Minecraft API | `soundboard/gui/screen/*.kt` | ~5 errors |
| GridLayout signature changes | owo-lib 0.13 | `soundboard/gui/component/FlexibleGridLayout.kt` | ~3 errors |
| **Total remaining** | | **~50 compile errors** | 4-6 hours |

## Dependency version matrix

| | 1.21.8 | 1.21.11 | 26.1.2 |
|---|---|---|---|
| yarn | 1.21.8+build.1 | 1.21.11+build.5 | (mojmap, no yarn) |
| fabric_api | 0.136.1+1.21.8 | 0.141.3+1.21.11 | 0.148.0+26.1.2 |
| fabric_loader | 0.19.2 | 0.19.2 | 0.19.2 |
| FLK | 1.13.11+kotlin.2.3.21 | (same) | (same) |
| owo-lib | 0.12.23+1.21.8 | 0.13.0+1.21.11 | 0.13.0+26.1 |
| SVC mod | fabric-1.21.8-2.6.17 | fabric-1.21.11-2.6.17 | fabric-2.6.17+26.1.2 |
| SVC API | 2.6.13 | 2.6.13 | 2.6.13 |
| Plasmo mod | fabric-1.21.6-2.1.9 | fabric-1.21.11-2.1.9 | fabric-26.1-2.1.9 |
| Plasmo API | 2.1.7-SNAPSHOT | (same) | (same) |
| modmenu | 15.0.2 | 17.0.0 | 18.0.0-beta.1 |

## Source patches applied for 1.21.8

| Old API (1.21.4) | New API (1.21.8) | File |
|------------------|------------------|------|
| `RenderSystem.recordRenderCall` | `RenderSystem.queueFencedTask` | `gui/screen/ScreenManager.kt`, `gui/widget/SidebarWidget.kt` |
| `RenderLayer::getGuiTextured` | `RenderPipelines.GUI_TEXTURED` | `gui/component/DurationCutterComponent.kt` |
| `Consumer<MatrixStack>` (owo) | `Consumer<Matrix4f>` | `kowoui/access/Components.kt` |
| `MatrixStack.push/pop` | `Matrix3x2fStack.pushMatrix/popMatrix` | `gui/component/ScrollingLabelComponent.kt` |
| `RenderEffectWrapper` (owo 0.12 — gone) | helper deleted (no callers) | `kowoui/Builders.kt` |
| `ClickEvent(Action, str)` | `ClickEvent.OpenUrl(URI)` | `audio/download/CobaltAPI.kt` |
| `drawTooltip(...)` | gained `focused: boolean` arg | `gui/component/ScrollingButtonComponent.kt` |
| custom `drawLinePrecise` using `vertexConsumers()` | delegates to owo's `drawLine(...)` | `util/KOwoUi.kt` |
| `me.fallenbreath.yamlang` 1.4.0 | 1.5.0 (Gradle 9 compat) | `build.gradle.kts` |
| modmenu 13.0.0-beta.1 (1.21.4) | 15.0.2 (1.21.8) | `stonecutter.properties.toml` |

## To complete the 1.21.11 / 26.1 port

1. **Surface accessor API** — Trace and conditionally gate Surface property accesses in `kowoui/access/Parents.kt`. owo-lib 0.13 may have changed how surface() works.

2. **Type inference in builders** — The `when { }.let { }` chains are failing type inference. May need explicit type annotations or lambda restructuring.

3. **GridLayout and container changes** — Determine if child insertion / getChild() APIs changed and gate accordingly.

4. **26.1 mojmap translation** — Once 1.21.11 compiles, add per-version mojmap gatekeeping for class names (e.g., `net.minecraft.entity.Entity` vs `net.minecraft.world.entity.Entity`).

5. **Test in real environment** — Build and run on actual Minecraft 1.21.11 and 26.1 servers with both SVC and Plasmo backends.

The original SVC 2.5 → 2.6 audio decoder rewrite kikugie called out
in upstream issues #32 / #36 has **not** been done — runtime listens
on the new 2.6 events but reuses the 2.5-shaped audio path. Test in
a real server before relying on it.

## Build / run commands

```bash
./gradlew :1.21.8:build       # produces versions/1.21.8/build/libs/soundboard-0.7.1+1.21.8.jar
./gradlew :1.21.8:runClient   # boots Minecraft 1.21.8 with the dev mod loaded

# When 1.21.11 / 26.1 are complete:
./gradlew :1.21.11:build
./gradlew :26.1:build
```
