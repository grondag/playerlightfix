package draylar.plf.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.util.math.Matrix4f;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderDispatcher;setRenderShadows(Z)V", shift = At.Shift.AFTER, ordinal = 0), method = "drawEntity")
	private static void fixDrawEntityLighting(int x, int y, int size, float mouseX, float mouseY, LivingEntity entity, CallbackInfo ci) {
		setupPlayerLighting();
	}

	@Unique
	private static void setupPlayerLighting() {
		RenderSystem.assertThread(RenderSystem::isOnRenderThread);
		Matrix4f matrix4f = new Matrix4f();
		matrix4f.loadIdentity();
		matrix4f.multiply(Matrix4f.scale(1.0F, -1.0F, 1.0F));
		RenderSystem.setupLevelDiffuseLighting(matrix4f);
	}
}
