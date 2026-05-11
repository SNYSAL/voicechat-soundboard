# Soundboard (alice-magic fork)

Fork of [kikugie/voicechat-soundboard](https://github.com/kikugie/voicechat-soundboard)
ported to **Minecraft 1.21.8, 1.21.11 & 26.1.x** (Fabric).

> Have you ever played with a proximity voice chat mod and thought "Damn, I miss dropping a metal pipe sound effect in my Discord calls"?
> **Well, look no further! Since now you can do it in Minecraft!**

This fork ships a **single jar** that loads both the Simple Voice Chat
and Plasmo Voice entrypoints when either backend is present at runtime
(no separate svc/plasmo split — see `MIGRATION.md`).

| MC | Loader | Status |
|----|--------|--------|
| 1.21.8 | Fabric | ✅ **builds + runs** |
| 1.21.11 | Fabric | ✅ **builds + runs** |
| 26.1.x  | Fabric | ✅ **builds + runs** |

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
## Development

### Multi-Version Support (May 2026)

✅ **All versions now working!** The mod has been successfully ported to support Minecraft 1.21.8, 1.21.11, and 26.1.x using owo-lib 0.13.

#### What was done:
1. ✅ **Build Infrastructure**: Added `1.21.11` and `26.1` version configs with complete dependency matrices
2. ✅ **Dependency Resolution**: 
   - 1.21.8: owo-lib 0.12.23 + Fabric API 0.136.1 (Yarn mappings)
   - 1.21.11: owo-lib 0.13.0 + Fabric API 0.141.3 (Mojang mappings)
   - 26.1.x: owo-lib 0.13.0 + Fabric API 0.148.0 (Mojang mappings)
3. ✅ **Base Class Aliasing**: Stonecutter conditionals for `Component → UIComponent`, `ParentComponent → ParentUIComponent`
4. ✅ **Property Access Patterns**: Updated coordinate accessors to use method calls (x(), y(), width(), height())
5. ✅ **Import Fixes**: Fixed imports for `AnimatableProperty`, `Insets`, and moved classes
6. ✅ **GitHub Actions CI/CD**: Multi-version build pipeline configured
7. ✅ **ButtonComponent Handling**: Used factory pattern with `Components.button()` to handle ButtonWidget.Text conversions

#### Build Approach:
- Uses **Stonecutter preprocessor** for version-specific code
- Conditional compilation blocks with `//? if =VERSION { }` syntax
- Shared source code with version-specific overrides where needed

#### GitHub Actions:
- All three versions build automatically on push to `main`
- Artifacts uploaded for each version
- Compilation checks run for all targets
- Build matrix runs in parallel for fast feedback

### Building from Source

```bash
# Build all versions
./gradlew build

# Build specific version
./gradlew :1.21.8:build
./gradlew :1.21.11:build
./gradlew :26.1:build

# Output: versions/<version>/build/libs/soundboard-<version>.jar
```

For more details, see [README_EN.md](README_EN.md) and [README_TH.md](README_TH.md)