$mappings = @{
    "net.minecraft.util.Identifier" = "net.minecraft.resources.ResourceLocation"
    "net.minecraft.text.Text" = "net.minecraft.network.chat.Component"
    "net.minecraft.nbt.NbtCompound" = "net.minecraft.nbt.CompoundTag"
    "net.minecraft.client.gui.DrawContext" = "net.minecraft.client.gui.GuiGraphics"
    "net.minecraft.client.MinecraftClient" = "net.minecraft.client.Minecraft"
    "net.minecraft.class_310Client" = "net.minecraft.client.Minecraft"
    "net.minecraft.util.math.BlockPos" = "net.minecraft.core.BlockPos"
    "net.minecraft.util.math.Vec3d" = "net.minecraft.world.phys.Vec3"
    "net.minecraft.client.util.math.MatrixStack" = "com.mojang.blaze3d.vertex.PoseStack"
    "net.minecraft.client.gui.screen.Screen" = "net.minecraft.client.gui.screens.Screen"
    "net.minecraft.util.registry.Registry" = "net.minecraft.core.Registry"
    "net.minecraft.util.registry.SimpleRegistry" = "net.minecraft.core.MappedRegistry"
    "net.minecraft.util.math.Direction" = "net.minecraft.core.Direction"
    "net.minecraft.client.gui.widget.TextFieldWidget" = "net.minecraft.client.gui.components.EditBox"
    "net.minecraft.client.gui.widget.ButtonWidget" = "net.minecraft.client.gui.components.Button"
    "net.minecraft.client.gui.widget.ClickableWidget" = "net.minecraft.client.gui.components.AbstractWidget"
    "net.minecraft.util.math.MathHelper" = "net.minecraft.util.Mth"
    "net.minecraft.util.ActionResult" = "net.minecraft.world.InteractionResult"
    "net.minecraft.util.TypedActionResult" = "net.minecraft.world.InteractionResultHolder"
    "net.minecraft.util.Hand" = "net.minecraft.world.InteractionHand"
    "net.minecraft.item.ItemUsageContext" = "net.minecraft.world.item.context.UseOnContext"
    "net.minecraft.client.Keyboard" = "net.minecraft.client.KeyboardHandler"
    "net.minecraft.client.option.KeyBinding" = "net.minecraft.client.KeyMapping"
    "net.minecraft.client.util.InputUtil" = "com.mojang.blaze3d.platform.InputConstants"
}

Get-ChildItem -Path "src\main" -Recurse -File -Include *.kt,*.java | ForEach-Object {
    $content = Get-Content $_.FullName -Raw
    $changed = $false
    foreach ($yarn in $mappings.Keys) {
        $mojang = $mappings[$yarn]
        if ($content.Contains($yarn)) {
            $content = $content.Replace($yarn, $mojang)
            $changed = $true
        }
    }
    if ($changed) {
        Set-Content $_.FullName $content -NoNewline
        Write-Host "Updated $($_.FullName)"
    }
}
