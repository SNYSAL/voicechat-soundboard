$mappings = @{
    # Classes
    "Identifier" = "ResourceLocation"
    "Text" = "Component"
    "NbtCompound" = "CompoundTag"
    "DrawContext" = "GuiGraphics"
    "MinecraftClient" = "Minecraft"
    "BlockPos" = "BlockPos" # Stays same, but package might change
    "Vec3d" = "Vec3"
    "MatrixStack" = "PoseStack"
    "Screen" = "Screen"
    "Registry" = "Registry"
    "TextFieldWidget" = "EditBox"
    "ButtonWidget" = "Button"
    "ClickableWidget" = "AbstractWidget"
    "MathHelper" = "Mth"
    "ActionResult" = "InteractionResult"
    "TypedActionResult" = "InteractionResultHolder"
    "Hand" = "InteractionHand"
    "ItemUsageContext" = "UseOnContext"
    "Keyboard" = "KeyboardHandler"
    "KeyBinding" = "KeyMapping"
    "InputUtil" = "InputConstants"
    "EntityAttribute" = "Attribute"
    "EntityAttributeModifier" = "AttributeModifier"
    "Formatting" = "ChatFormatting"
    
    # Packages (handled separately by full names in mappings if needed)
}

# Full package mappings
$packageMappings = @{
    "net.minecraft.util.Identifier" = "net.minecraft.resources.ResourceLocation"
    "net.minecraft.text.Text" = "net.minecraft.network.chat.Component"
    "net.minecraft.nbt.NbtCompound" = "net.minecraft.nbt.CompoundTag"
    "net.minecraft.client.gui.DrawContext" = "net.minecraft.client.gui.GuiGraphics"
    "net.minecraft.client.MinecraftClient" = "net.minecraft.client.Minecraft"
    "net.minecraft.util.math.BlockPos" = "net.minecraft.core.BlockPos"
    "net.minecraft.util.math.Vec3d" = "net.minecraft.world.phys.Vec3"
    "net.minecraft.client.util.math.MatrixStack" = "com.mojang.blaze3d.vertex.PoseStack"
    "net.minecraft.client.gui.screen.Screen" = "net.minecraft.client.gui.screens.Screen"
    "net.minecraft.util.registry.Registry" = "net.minecraft.core.Registry"
    "net.minecraft.util.math.Direction" = "net.minecraft.core.Direction"
    "net.minecraft.client.gui.widget.TextFieldWidget" = "net.minecraft.client.gui.components.EditBox"
    "net.minecraft.client.gui.widget.ButtonWidget" = "net.minecraft.client.gui.components.Button"
    "net.minecraft.client.gui.widget.ClickableWidget" = "net.minecraft.client.gui.components.AbstractWidget"
    "net.minecraft.util.math.MathHelper" = "net.minecraft.util.Mth"
    "net.minecraft.client.Keyboard" = "net.minecraft.client.KeyboardHandler"
    "net.minecraft.client.option.KeyBinding" = "net.minecraft.client.KeyMapping"
    "net.minecraft.client.util.InputUtil" = "com.mojang.blaze3d.platform.InputConstants"
    "net.minecraft.util.Formatting" = "net.minecraft.ChatFormatting"
}

# Common Method Mappings
$methodMappings = @{
    "\.isPressed" = ".isDown"
    "\.wasPressed\(\)" = ".consumeClick()"
}

Get-ChildItem -Path "src\main" -Recurse -File -Include *.kt,*.java | ForEach-Object {
    $content = Get-Content $_.FullName -Raw
    $changed = $false
    
    # Replace full packages first
    foreach ($yarn in $packageMappings.Keys) {
        $mojang = $packageMappings[$yarn]
        if ($content.Contains($yarn)) {
            $content = $content.Replace($yarn, $mojang)
            $changed = $true
        }
    }
    
    # Replace simple class names (with word boundaries)
    foreach ($yarn in $mappings.Keys) {
        $mojang = $mappings[$yarn]
        $pattern = "\b$yarn\b"
        if ($content -match $pattern) {
            $content = $content -replace $pattern, $mojang
            $changed = $true
        }
    }
    
    # Replace methods
    foreach ($yarn in $methodMappings.Keys) {
        $mojang = $methodMappings[$yarn]
        if ($content -match $yarn) {
            $content = $content -replace $yarn, $mojang
            $changed = $true
        }
    }

    if ($changed) {
        Set-Content $_.FullName $content -NoNewline
        Write-Host "Updated $($_.FullName)"
    }
}
