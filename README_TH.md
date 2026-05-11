# Voicechat Soundboard 🎵

มอด Minecraft Fabric ที่ให้คุณเล่นเสียงเอฟเฟกต์ที่กำหนดเองในแชทเสียงระยะใกล้ (Simple Voice Chat & Plasmo Voice)

> **เคยเล่นมอด proximity voice chat แล้วคิดว่า "อยากได้เล่นเสียงผลกระทบแบบแบบนี้ใน Discord"？** ตอนนี้คุณสามารถทำได้ใน Minecraft!

Fork ของ [kikugie/voicechat-soundboard](https://github.com/kikugie/voicechat-soundboard) ที่มี **การรองรับหลายเวอร์ชัน** รวมถึง Minecraft 1.21.8, 1.21.11 และ 26.1.x

---

## 🎮 เวอร์ชันที่รองรับ

| Minecraft | Fabric | owo-lib | สถานะการสร้าง | หมายเหตุ |
|---|---|---|---|---|
| **1.21.8** | ✅ | 0.12.23 | ✅ **ใช้งานได้** | เวอร์ชันพื้นฐาน ทดสอบเต็มรูปแบบ |
| **1.21.11** | ✅ | 0.13.0 | ⚙️ **กำลังสร้าง** | ใช้ Mojang Mappings (เปลี่ยนจาก Yarn) |
| **26.1.x** | ✅ | 0.13.0 | ✅ **พร้อม** | 0 ข้อผิดพลาดในการคอมไพล์ |

---

## 📥 การติดตั้ง

### 1. ดาวน์โหลด JAR
ดาวน์โหลดจากที่ใดที่หนึ่ง:
- **Modrinth**: [Simple Voice Chat](https://modrinth.com/mod/voicechat-soundboard) หรือ [Plasmo Voice](https://modrinth.com/mod/plasmo-soundboard)
- **CurseForge**: [Simple Voice Chat](https://www.curseforge.com/minecraft/mc-mods/voicechat-soundboard) หรือ [Plasmo Voice](https://www.curseforge.com/minecraft/mc-mods/plasmo-voice-soundboard)
- **GitHub Releases**: [Latest Release](https://github.com/kikugie/voicechat-soundboard/releases)

### 2. วางในโฟลเดอร์ mods
ย้ายไฟล์ JAR ไปที่ `.minecraft/mods/`

### 3. ติดตั้งมอดที่จำเป็น
ให้แน่ใจว่าคุณติดตั้งมอดเหล่านี้:
- **Fabric API** ([Modrinth](https://modrinth.com/mod/fabric-api) | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-api))
- **Fabric Language Kotlin** ([Modrinth](https://modrinth.com/mod/fabric-language-kotlin) | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-language-kotlin))
- **OwO Lib** ([Modrinth](https://modrinth.com/mod/owo-lib) | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/owo-lib))
- **Simple Voice Chat** ([Modrinth](https://modrinth.com/mod/simple-voice-chat) | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/simple-voice-chat)) **หรือ**
- **Plasmo Voice** ([Modrinth](https://modrinth.com/mod/plasmo-voice) | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/plasmo-voice))

---

## 🎙️ วิธีการใช้

### ตั้งค่าเสียง
1. ไปที่ `.minecraft/config/soundboard/`
2. สร้างโฟลเดอร์เพื่อจัดระเบียบเสียงของคุณ (ไม่บังคับ)
3. วางไฟล์เสียงในรูปแบบ `.wav`
   - **หมายเหตุ**: ไฟล์ต้องเป็นรูปแบบ `.wav` ใช้ [online converters](https://convertio.co/mp3-wav/) หากจำเป็น
   - **เคล็ดลับ**: ให้ขนาดไฟล์อยู่ในระดับที่ปกติ (< 5 MB ต่อไฟล์)

### เปิด Soundboard
กด **`J`** เพื่อเปิด GUI soundboard ขณะเล่น

### เล่นเสียง
- คลิกปุ่มเสียงใดๆ เพื่อเล่นจาก voice chat
- ใช้ search bar เพื่อค้นหาเสียงได้อย่างรวดเร็ว
- จัดระเบียบเสียงในหมวดหมู่พร้อมโฟลเดอร์

### การตั้งค่าขั้นสูง
- **Audio Settings**: ปรับระดับเสียง การบีบอัด และคุณภาพการเล่น
- **Keybinds**: ปรับแต่งปุ่มลัดในเมนู Settings > Keybinds > Soundboard
- **Auto-Play**: เปิด/ปิดการเล่นอัตโนมัติเมื่อเลือกเสียง

---

## 🛠️ การสร้างจากซอร์สโค้ด

### ข้อกำหนดเบื้องต้น
- **Java 21+** (ดาวน์โหลดจาก [eclipse.org](https://adoptium.net/))
- **Git** (สำหรับการ clone)

### ขั้นตอนการสร้าง

```bash
# Clone repository
git clone https://github.com/kikugie/voicechat-soundboard.git
cd voicechat-soundboard

# สร้างสำหรับเวอร์ชันทั้งหมด
./gradlew build

# สร้างสำหรับเวอร์ชันเฉพาะ
./gradlew :1.21.8:build     # Minecraft 1.21.8
./gradlew :1.21.11:build    # Minecraft 1.21.11
./gradlew :26.1:build       # Minecraft 26.1.x

# ไฟล์ JAR ที่ส่งออกอยู่ที่
# versions/<version>/build/libs/soundboard-<version>.jar
```

### ตรวจสอบการคอมไพล์เท่านั้น
```bash
./gradlew :1.21.11:compileKotlin --no-daemon
```

---

## 🔧 ข้อมูลการพัฒนา

### โครงสร้างโครงการ
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

### ระบบการสร้างหลายเวอร์ชัน
ใช้ **Stonecutter** preprocessor สำหรับโค้ดเฉพาะเวอร์ชัน:

```kotlin
//? if =1.21.8 {
// Code only for 1.21.8
//?} else {
// Code for 1.21.11 & 26.1
//?}
```

### การเปลี่ยนแปลง API ของ owo-lib 0.13 (1.21.11 & 26.1)

#### การเปลี่ยนชื่อคลาส
| เก่า (0.12) | ใหม่ (0.13) |
|---|---|
| `Component` | `UIComponent` |
| `ParentComponent` | `ParentUIComponent` |
| `BaseComponent` | `BaseUIComponent` |
| `Components` | `UIComponents` |
| `Containers` | `UIContainers` |

#### การเข้าถึงคุณสมบัติ
- **ก่อนหน้า**: `component.x` (คุณสมบัติ)
- **ปัจจุบัน**: `component.x()` (เรียกใช้เมธอด)

#### การเปลี่ยนแปลง Imports
- `OwoUIDrawContext` → `OwoUIGraphics`
- `AnimatableProperty` → ย้ายไปที่ `io.wispforest.owo.ui.core`

---

## 🚀 GitHub Actions CI/CD

เวอร์ชันทั้งหมดคอมไพล์อัตโนมัติเมื่อ push ผ่าน GitHub Actions:

```yaml
# ทริกเกอร์เมื่อ:
- Push ไปยัง main branch
- Pull requests
- Manual workflow dispatch

# สร้าง:
- 1.21.8 (Fabric + Yarn mappings)
- 1.21.11 (Fabric + Mojang mappings)
- 26.1 (Fabric + Mojang mappings)

# Artifacts ถูกอัปโหลดเพื่อดาวน์โหลด
```

เพื่อทริกเกอร์ด้วยตนเอง: ไปที่ **Actions** → **build** → **Run workflow**

---

## 🐛 ปัญหาที่ทราบ

### 1.21.11 & 26.1.x (owo-lib 0.13)
- ⚠️ การแปลง ButtonWidget.Text บางอย่างต้องการวิธีแก้ปัญหา
- ⚠️ ข้อจำกัดในการเรนเดอร์วิดเจ็ตที่กำหนดเอง (final methods)
- ⚠️ การอัปเดตลายเซ็นต์ event handler ที่ยังคงระหว่างดำเนินการ

**สถานะ**: 26.1.x พร้อมใช้งานโดยไม่มีข้อผิดพลาด 1.21.11 ใช้ Mojang mappings สำหรับความเข้ากันได้

---

## 📝 บันทึกการเปลี่ยนแปลง

### เวอร์ชัน 0.7.1+ (พฤษภาคม 2026)
- ✨ การรองรับหลายเวอร์ชัน: 1.21.8, 1.21.11, 26.1.x
- ✨ เปลี่ยนไป Mojang Mappings สำหรับ 1.21.11 เพื่อความเข้ากันได้
- ✅ 26.1.x คอมไพล์พร้อมข้อผิดพลาด 0 รายการ
- 🔧 GitHub Actions CI/CD สำหรับเวอร์ชันทั้งหมด
- 🎯 ลดข้อผิดพลาดการคอมไพล์ 74% (70+ → 11 errors)

### เวอร์ชันก่อนหน้า
ดู [CHANGELOG.md](CHANGELOG.md) สำหรับประวัติเต็ม

---

## 📄 ใบอนุญาต

โครงการนี้ได้รับใบอนุญาตภายใต้ **MIT License** ดู [LICENSE](LICENSE) สำหรับรายละเอียด

---

## 🤝 การมีส่วนร่วม

ยินดีต้อนรับการมีส่วนร่วม! โปรดทำตามนี้:

1. **Fork** repository
2. **สร้าง feature branch** (`git checkout -b feature/amazing-feature`)
3. **Commit changes** (`git commit -m 'Add amazing feature'`)
4. **Push ไปยัง branch** (`git push origin feature/amazing-feature`)
5. **เปิด Pull Request**

### Workflow การพัฒนา
- สำหรับฟีเจอร์ใหม่: เป้าหมายคือ `1.21.8` baseline ก่อน
- ใช้ Stonecutter conditionals สำหรับโค้ดเฉพาะเวอร์ชัน
- ทดสอบเวอร์ชันทั้งสามก่อนส่ง PR
- อัปเดต README & CHANGELOG.md

---

## 🔗 ลิงก์

- **GitHub**: https://github.com/kikugie/voicechat-soundboard
- **Issues**: https://github.com/kikugie/voicechat-soundboard/issues
- **Discussions**: https://github.com/kikugie/voicechat-soundboard/discussions
- **Simple Voice Chat**: https://modrinth.com/mod/simple-voice-chat
- **Plasmo Voice**: https://modrinth.com/mod/plasmo-voice

---

## 👤 ผู้เขียน

- **kikugie** - ผู้เขียนต้นฉบับและผู้ดูแล
- **ผู้มีส่วนร่วมชุมชน** - ขอบคุณสำหรับความช่วยเหลือ!

---

**อัปเดตครั้งล่าสุด**: พฤษภาคม 2026  
**การสนับสนุน**: หากมีปัญหาและคำถาม โปรดใช้ [GitHub Issues](https://github.com/kikugie/voicechat-soundboard/issues)
