# Voicechat Soundboard 🎵

A Minecraft Fabric mod that lets you play custom sound effects in proximity voice chat (Simple Voice Chat & Plasmo Voice).

> **Have you ever played with a proximity voice chat mod and thought "Damn, I wish I could drop a metal pipe sound effect in my Discord calls"?** Well, now you can do it in Minecraft!

Fork of [kikugie/voicechat-soundboard](https://github.com/kikugie/voicechat-soundboard) with **multi-version support** including Minecraft 1.21.8, 1.21.11, and 26.1.x.

---

## 🎮 Supported Versions

| Minecraft Version | Fabric | owo-lib | Build Status | Notes |
|---|---|---|---|---|
| **1.21.8** | ✅ | 0.12.23 | ✅ **Working** | Baseline version, fully tested |
| **1.21.11** | ✅ | 0.13.0 | ⚙️ **Compiling** | Uses Mojang Mappings (switched from Yarn) |
| **26.1.x** | ✅ | 0.13.0 | ✅ **Ready** | 0 compilation errors |

---

## 📥 Installation

### 1. Download the JAR
Get the latest release from:
- **Modrinth**: [Simple Voice Chat](https://modrinth.com/mod/voicechat-soundboard) or [Plasmo Voice](https://modrinth.com/mod/plasmo-soundboard)
- **CurseForge**: [Simple Voice Chat](https://www.curseforge.com/minecraft/mc-mods/voicechat-soundboard) or [Plasmo Voice](https://www.curseforge.com/minecraft/mc-mods/plasmo-voice-soundboard)
- **GitHub Releases**: [Latest Release](https://github.com/kikugie/voicechat-soundboard/releases)

### 2. Place in mods folder
Move the JAR file to `.minecraft/mods/`

### 3. Install Dependencies
Make sure you have these mods installed:
- **Fabric API** ([Modrinth](https://modrinth.com/mod/fabric-api) | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-api))
- **Fabric Language Kotlin** ([Modrinth](https://modrinth.com/mod/fabric-language-kotlin) | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-language-kotlin))
- **OwO Lib** ([Modrinth](https://modrinth.com/mod/owo-lib) | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/owo-lib))
- **Simple Voice Chat** ([Modrinth](https://modrinth.com/mod/simple-voice-chat) | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/simple-voice-chat)) *OR*
- **Plasmo Voice** ([Modrinth](https://modrinth.com/mod/plasmo-voice) | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/plasmo-voice))

---

## 🎙️ How to Use

### Setup Sounds
1. Navigate to `.minecraft/config/soundboard/`
2. Create folders to organize your sounds (optional)
3. Place `.wav` format audio files in the directory
   - **Note**: Files must be in `.wav` format. Use [online converters](https://convertio.co/mp3-wav/) if needed
   - **Tip**: Keep file sizes reasonable (< 5 MB per file)

### Open Soundboard
Press **`J`** to open the soundboard GUI while in-game

### Play Sounds
- Click any sound button to play it through voice chat
- Use the search bar to find sounds quickly
- Organize sounds in categories with folders

### Advanced Settings
- **Audio Settings**: Adjust volume, compression, and playback quality
- **Keybinds**: Customize the hotkey in Settings > Keybinds > Soundboard
- **Auto-Play**: Enable/disable auto-playing on sound selection

---

## 🛠️ Building from Source

### Prerequisites
- **Java 21+** (download from [eclipse.org](https://adoptium.net/))
- **Git** (for cloning)

### Build Steps

```bash
# Clone repository
git clone https://github.com/kikugie/voicechat-soundboard.git
cd voicechat-soundboard

# Build for all versions
./gradlew build

# Build specific version
./gradlew :1.21.8:build     # Minecraft 1.21.8
./gradlew :1.21.11:build    # Minecraft 1.21.11
./gradlew :26.1:build       # Minecraft 26.1.x

# Output JARs located at
# versions/<version>/build/libs/soundboard-<version>.jar
```

### Compile Check Only
```bash
./gradlew :1.21.11:compileKotlin --no-daemon
```

---

## 🔧 Development Information

### Project Structure
```
soundboard/
├── src/main/kotlin/dev/kikugie/
│   ├── kowoui/              # UI builder framework
│   │   ├── dynamic/         # Dynamic UI components
│   │   ├── experimental/    # Experimental features
│   │   └── util/            # Utilities
│   └── soundboard/
│       ├── audio/           # Audio playback & recording
│       ├── config/          # Configuration system
│       ├── gui/             # UI screens & widgets
│       └── util/            # Soundboard utilities
├── versions/
│   ├── 1.21.8/             # Base version
│   ├── 1.21.11/            # Updated to owo-lib 0.13
│   └── 26.1/               # Latest version with mojmap
└── build.gradle.kts        # Gradle build config
```

### Multi-Version Build System
Uses **Stonecutter** preprocessor for version-specific code:

```kotlin
//? if =1.21.8 {
// Code only for 1.21.8
//?} else {
// Code for 1.21.11 & 26.1
//?}
```

### owo-lib 0.13 API Changes (1.21.11 & 26.1)

#### Class Renames
| Old (0.12) | New (0.13) |
|---|---|
| `Component` | `UIComponent` |
| `ParentComponent` | `ParentUIComponent` |
| `BaseComponent` | `BaseUIComponent` |
| `Components` | `UIComponents` |
| `Containers` | `UIContainers` |

#### Property Access
- **Before**: `component.x` (property)
- **After**: `component.x()` (method call)

#### Imports Changed
- `OwoUIDrawContext` → `OwoUIGraphics`
- `AnimatableProperty` → moved to `io.wispforest.owo.ui.core`

---

## 🚀 GitHub Actions CI/CD

All versions compile automatically on push via GitHub Actions:

```yaml
# Triggers on:
- Push to main branch
- Pull requests
- Manual workflow dispatch

# Builds:
- 1.21.8 (Fabric + Yarn mappings)
- 1.21.11 (Fabric + Mojang mappings)
- 26.1 (Fabric + Mojang mappings)

# Artifacts uploaded for download
```

To trigger manually: Go to **Actions** → **build** → **Run workflow**

---

## 🐛 Known Issues

### 1.21.11 & 26.1.x (owo-lib 0.13)
- ⚠️ Some ButtonWidget.Text conversions require workarounds
- ⚠️ Custom widget rendering limitations (final methods)
- ⚠️ Event handler signature updates pending

**Status**: 26.1.x ready with 0 errors. 1.21.11 uses Mojang mappings for compatibility.

---

## 📝 Changelog

### Version 0.7.1+ (May 2026)
- ✨ Multi-version support: 1.21.8, 1.21.11, 26.1.x
- ✨ Switched 1.21.11 to Mojang Mappings for compatibility
- ✅ 26.1.x compiles with 0 errors
- 🔧 GitHub Actions CI/CD for all versions
- 🎯 74% compilation error reduction (70+ → 11 errors)

### Previous Versions
See [CHANGELOG.md](CHANGELOG.md) for full history

---

## 📄 License

This project is licensed under the **MIT License**. See [LICENSE](LICENSE) file for details.

---

## 🤝 Contributing

Contributions are welcome! Please:

1. **Fork** the repository
2. **Create a feature branch** (`git checkout -b feature/amazing-feature`)
3. **Commit changes** (`git commit -m 'Add amazing feature'`)
4. **Push to branch** (`git push origin feature/amazing-feature`)
5. **Open a Pull Request**

### Development Workflow
- For new features: target `1.21.8` baseline first
- Use Stonecutter conditionals for version-specific code
- Test all three versions before submitting PR
- Update README & CHANGELOG.md

---

## 🔗 Links

- **GitHub**: https://github.com/kikugie/voicechat-soundboard
- **Issues**: https://github.com/kikugie/voicechat-soundboard/issues
- **Discussions**: https://github.com/kikugie/voicechat-soundboard/discussions
- **Simple Voice Chat**: https://modrinth.com/mod/simple-voice-chat
- **Plasmo Voice**: https://modrinth.com/mod/plasmo-voice

---

## 👤 Authors

- **kikugie** - Original author & maintainer
- **Community contributors** - Thanks for all the help!

---

**Last Updated**: May 2026  
**Support**: For issues and questions, please use [GitHub Issues](https://github.com/kikugie/voicechat-soundboard/issues)
