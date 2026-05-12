package dev.kikugie.soundboard.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.kikugie.soundboard.audio.download.Downloader;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
	protected TitleScreenMixin(Component title) {
		super(title);
	}

	@WrapMethod(method = "init")
	private void downloaderWarning(Button button, Operation<Void> operation) {
		var downloads = Downloader.downloads();
		if (minecraft == null || downloads.isEmpty()) operation.call(button);
		else minecraft.setScreen(Downloader.confirmation(this, () -> operation.call(button)));
	}
}




