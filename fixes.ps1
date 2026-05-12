# รายการ Mapping สำหรับเปลี่ยนกลับ (Mojang -> Yarn/Intermediary)
$mappings = @{
    # --- Packages & Imports ---
    "net.minecraft.network.chat.Component"      = "net.minecraft.text.Text"
    "net.minecraft.resources.ResourceLocation"  = "net.minecraft.util.Identifier"
    "net.minecraft.core.BlockPos"               = "net.minecraft.util.math.BlockPos"
    "net.minecraft.client.gui.components"       = "net.minecraft.client.gui.widget"
    "net.minecraft.world.level.block"           = "net.minecraft.block"
    "net.minecraft.client.Minecraft"            = "net.minecraft.class_310"
    
    # --- UI Components & Widgets ---
    "EditBox"                                   = "TextFieldWidget"
    "Button"                                    = "ButtonWidget"
    "AbstractWidget"                            = "ClickableWidget"
    
    # --- Methods ---
    ".setValue("                                = ".setText("
    ".getValue()"                               = ".getText()"
    "Component.literal("                        = "Text.literal("
    "Component.translatable("                   = "Text.translatable("
    
    # --- AccessWidener (กลับมาเป็นภาษา Yarn) ---
    "accessible field net/minecraft/client/gui/components/EditBox value Ljava/lang/String;" = "accessible field net/minecraft/client/gui/widget/TextFieldWidget text Ljava/lang/String;"
}

Write-Host "🔄 กำลังพากัปตันกลับบ้าน... (ย้อนคืนสู่ Yarn Mapping)" -ForegroundColor Yellow

# ค้นหาไฟล์ในโปรเจกต์
$files = Get-ChildItem -Path "." -Recurse -File | Where-Object { 
    $_.Extension -in ".kt", ".java", ".accesswidener", ".json" 
}

foreach ($file in $files) {
    $content = [System.IO.File]::ReadAllText($file.FullName)
    $isUpdated = $false

    foreach ($mojang in $mappings.Keys) {
        if ($content.Contains($mojang)) {
            $content = $content.Replace($mojang, $mappings[$mojang])
            $isUpdated = $true
        }
    }

    if ($isUpdated) {
        Write-Host "↩️ ย้อนคืนแล้ว: $($file.Name)" -ForegroundColor Cyan
        [System.IO.File]::WriteAllText($file.FullName, $content)
    }
}

Write-Host "`n🏁 เสร็จสิ้น! โค้ดกลับเป็นภาษา Yarn แล้วครับกัปตัน!" -ForegroundColor Green