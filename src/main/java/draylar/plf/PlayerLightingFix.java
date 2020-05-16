package draylar.plf;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.render.DiffuseLighting;

public class PlayerLightingFix {
	public static void setupPlayerLighting() {
		RenderSystem.assertThread(RenderSystem::isOnRenderThread);
		RenderSystem.disableLighting();
		DiffuseLighting.disableGuiDepthLighting();
		DiffuseLighting.disable();
		//		final Matrix4f matrix4f = new Matrix4f();
		//		matrix4f.loadIdentity();
		//		matrix4f.multiply(Matrix4f.scale(1.0F, -1.0F, 1.0F));
		//		RenderSystem.setupLevelDiffuseLighting(matrix4f);
	}
}
