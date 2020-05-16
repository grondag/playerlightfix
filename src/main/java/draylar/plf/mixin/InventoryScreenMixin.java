package draylar.plf.mixin;

import draylar.plf.PlayerLightingFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.LivingEntity;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderDispatcher;setRenderShadows(Z)V", shift = At.Shift.AFTER, ordinal = 0), method = "drawEntity")
	private static void fixDrawEntityLighting(int x, int y, int size, float mouseX, float mouseY, LivingEntity entity, CallbackInfo ci) {
		PlayerLightingFix.setupPlayerLighting();
	}
}
