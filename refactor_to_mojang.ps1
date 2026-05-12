# Mojang Mapping Refactor Script
$mappings = @{
    "import net.minecraft.text.Text"            = "import net.minecraft.network.chat.Component"
    "import net.minecraft.util.Identifier"      = "import net.minecraft.resources.ResourceLocation"
    "import net.minecraft.util.math.BlockPos"   = "import net.minecraft.core.BlockPos"
    "import net.minecraft.client.gui.widget."  = "import net.minecraft.client.gui.components."
    "import net.minecraft.block."               = "import net.minecraft.world.level.block."
    "import net.minecraft.entity."              = "import net.minecraft.world.entity."
    "import net.minecraft.item."                = "import net.minecraft.world.item."
    "import net.minecraft.nbt.NbtCompound"      = "import net.minecraft.nbt.CompoundTag"
    "import net.minecraft.client.texture.Sprite" = "import net.minecraft.client.renderer.texture.TextureAtlasSprite"
    "import net.minecraft.client.util.SpriteResourceLocation" = "import net.minecraft.resources.ResourceLocation"
    "TextFieldWidget"                           = "EditBox"
    "ButtonWidget"                              = "Button"
    "ClickableWidget"                           = "AbstractWidget"
    "Text.literal("                             = "Component.literal("
    "Text.translatable("                        = "Component.translatable("
}

$files = Get-ChildItem -Path "src" -Recurse -File | Where-Object { 
    $_.Extension -in ".kt", ".java", ".accesswidener", ".json" 
}

foreach ($file in $files) {
    $content = [System.IO.File]::ReadAllText($file.FullName)
    $isUpdated = $false

    foreach ($yarn in $mappings.Keys) {
        if ($content.Contains($yarn)) {
            $content = $content.Replace($yarn, $mappings[$yarn])
            $isUpdated = $true
        }
    }

    if ($isUpdated) {
        Write-Host "Refactored: $($file.Name)"
        [System.IO.File]::WriteAllText($file.FullName, $content)
    }
}
