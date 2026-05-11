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
| 1.21.11 | Fabric | 🟡 config added, ~50 compile errors remain (owo-lib 0.13 API changes) |
| 26.1.x  | Fabric | 🟡 config added, not yet tested (requires mojmap translation + owo-lib 0.13) |

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

**1.21.11 & 26.1.x Porting Work** — Partial migration in progress:

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

I'm planning to expand its features with a config, favorite sounds and other cool stuff.  
If you have suggestion or issues tell me on the [Discord server](https://discord.gg/TBgNUCfryS) or [GitHub issues](https://github.com/kikugie/voicechat-soundboard).