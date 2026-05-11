# Soundboard (alice-magic fork)

Fork of [kikugie/voicechat-soundboard](https://github.com/kikugie/voicechat-soundboard)
ported to **Minecraft 1.21.8** (Fabric).

> Have you ever played with a proximity voice chat mod and thought "Damn, I miss dropping a metal pipe sound effect in my Discord calls"?
> **Well, look no further! Since now you can do it in Minecraft!**

This fork ships a **single jar** that loads both the Simple Voice Chat
and Plasmo Voice entrypoints when either backend is present at runtime
(no separate svc/plasmo split — see `MIGRATION.md`).

| MC | Loader | Build status |
|----|--------|--------------|
| 1.21.8 | Fabric | ✅ builds + runs |
| 1.21.11 | Fabric | 🟡 compiling - 21 type compatibility errors remaining (owo-lib 0.13) |
| 26.1.x  | Fabric | 🟡 config ready, not yet tested (mojmap + owo-lib 0.13) |

## Addons
This mod is split for Simple Voice Chat and Plasmo Voice. Use links below to navigate to the correct one:

| Simple Voice Chat | [Modrinth](https://modrinth.com/mod/voicechat-soundboard) | [Curseforge](https://www.curseforge.com/minecraft/mc-mods/voicechat-soundboard)    |
|-------------------|-----------------------------------------------------------|------------------------------------------------------------------------------------|
| Plasmo Voice      | [Modrinth](https://modrinth.com/mod/plasmo-soundboard)    | [Curseforge](https://www.curseforge.com/minecraft/mc-mods/plasmo-voice-soundboard) |

## Dependencies
This mod requires:
- [Fabric API](https://modrinth.com/mod/fabric-api)
- [Fabric Language Kotlin](https://modrinth.com/mod/fabric-language-kotlin)
- [OwO Lib](https://modrinth.com/mod/owo-lib)

## How to use
Sounds are stored in `.minecraft/config/soundboard`, where you can put files and create directories to organize the files.
**Files must be in `.wav` format. Use an online audio converter if needed.**

The soundboard screen can be opened with **J**:  
![Soundboard screen](https://cdn.modrinth.com/data/N8s60DWW/images/3a0dc4edbe8dedd4ece49b635e2f38d097470b37.png)

Refer to the [changelog](https://modrinth.com/mod/voicechat-soundboard/changelog) for more information.

## Development

### Version Support Status (May 2026)

### 1.21.11 & 26.1.x Porting Work (May 2026)

**What was done:**
1. ✅ **Build Infrastructure**: Added `1.21.11` and `26.1` version configs to `settings.gradle.kts` and `stonecutter.properties.toml` with complete dependency matrices
2. ✅ **Dependency Resolution**: Configured all required Fabric/owo-lib versions:
	 - 1.21.8: owo-lib 0.12.23 + Fabric API 0.136.1 (baseline, working)
	 - 1.21.11: owo-lib 0.13.0 + Fabric API 0.141.3 (target)
	 - 26.1.x: owo-lib 0.13.0 + Fabric API 0.148.0 + Mojang Mappings (target)
3. ✅ **Base Class Aliasing**: Added Stonecutter conditionals for `Component → UIComponent`, `ParentComponent → ParentUIComponent`, `BaseComponent → BaseUIComponent`
4. ✅ **Property Access Patterns**: Updated coordinate accessors (x, y, width, height) to use method calls in 0.13
5. ✅ **Dependency Imports**: Fixed imports for `AnimatableProperty`, `Insets`, and other moved/renamed classes
6. ✅ **GitHub Actions**: Set up multi-version CI/CD pipeline to build all three versions automatically
7. ✅ **Error Reduction**: Reduced compilation errors from 48+ down to 21 (65% improvement)

**Current blockers (21 compile errors - Type Compatibility):**
- **ButtonWidget.Text incompatibility**: In 1.21.11, ButtonComponent expects `ButtonWidget.Text` parameter, but owo-lib still uses plain `Text` in some places
  - Affects: `DynamicButtonComponent.kt`, `Builders.kt` button functions, `ScrollingButtonComponent.kt`, `SoundSettingsWidget.kt`, `SoundBrowser.kt`
  - Impact: Cannot directly convert between Text and ButtonWidget.Text; requires wrapper components or return type overrides
- **Tooltip API Changes**: `owo$getTooltip` accessor no longer exists in 0.13; tooltip system redesigned
- **Widget Override Restrictions**: `renderWidget` method is now final in `PressableWidget` (can't be overridden)
- **Event Handler Signatures** (Not yet addressed): `onMouseDrag`, `onMouseUp` parameters changed from `(int, int, int, int)` to `(Click)` object

**Remaining Work (~2-3 hours estimated):**
1. Create wrapper components or conditional implementations for ButtonWidget.Text mismatch (~30 min)
2. Implement tooltip compatibility layer (~20 min)
3. Replace `renderWidget` override with alternative rendering approach (~15 min)
4. Update event handler signatures to accept Click record (~30 min)
5. Recompile and validate all three versions (~20 min)
6. Test runtime functionality in 1.21.11 client (~30 min)

**Build Status:**
- ✅ 1.21.8: Full builds + runs successfully
- 🟡 1.21.11: Compilation blocked by 21 type compatibility errors (compiles with warnings in GitHub Actions)
- 🟡 26.1.x: Ready to test after 1.21.11 fixes (likely same issues due to shared owo-lib 0.13)

**GitHub Actions CI/CD:**
- All three versions build automatically on push to `main` branch
- Artifacts uploaded to GitHub Actions for each version
- Compilation check runs for Kotlin syntax validation
- Build summary reports status of all versions

### Development

### 1.21.11 & 26.1.x Porting Work** — Partial migration in progress:

#### ✅ Completed:
- Added version blocks to `settings.gradle.kts` and `stonecutter.properties.toml`
- Full dependency matrix resolved and configured for 1.21.11 (owo-lib 0.13) and 26.1.2 (mojmap)
- Applied Stonecutter conditionals to ~20 source files for `Component → UIComponent` aliasing
- Fixed keyboard input APIs (`Screen.hasShiftDown()` → GLFW checks)
- Updated build configuration; builds **DO NOT** produce errors on 1.21.8

#### 🟡 Remaining (~50 compile errors):
- **Surface property API changes** — owo-lib 0.13 changed accessor method signatures (~20 errors)
- **Type inference in builders** — Lambda chains need explicit type annotations (~30 errors)
- **ButtonWidget.Text mismatch** — Minecraft 1.21.11+ changed return type (~5 errors)
- **GridLayout signatures** — Container insertion API diverged (~3 errors)

**Estimate to complete:** 4–6 hours work + testing  
**See** [`MIGRATION.md`](MIGRATION.md) **for detailed API change notes.**

---

| MC | Loader | Build status |
|----|--------|--------------|
| 1.21.8 | Fabric | ✅ builds + runs |
| 1.21.11 | Fabric | 🟡 WIP - Dependency config ready, source code needs API updates |
| 26.1.2  | Fabric | 🟡 WIP - Dependency config ready, source code needs API updates |