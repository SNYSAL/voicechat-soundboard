package dev.kikugie.kowoui.mixin;

import dev.kikugie.kowoui.mixinstuff.TextFieldAccessor;
import io.wispforest.owo.ui.core.Color;
import net.minecraft.client.gui.components.EditBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.function.Predicate;

@Mixin(EditBox.class)
public class EditBoxMixin implements TextFieldAccessor {
	@Unique
	private String cachedText;

	@Unique
	private int cachedColor;

	@Shadow
	private String Component;

	@Shadow
	private Predicate<String> textPredicate;

	@Override
	public Predicate<String> soundboard$predicate() {
		return textPredicate;
	}

	@ModifyVariable(method = "renderWidget", at = @At("STORE"), ordinal = 2)
	private int applyColor(int value) {
		if (Component.equals(cachedText)) return cachedColor;
		Color color = soundboard$color(Component);
		cachedColor = color == null ? value : color.argb();
		cachedText = Component;
		return cachedColor;
	}
}



