package com.ruskei.nohurtcam.mixin;

import com.ruskei.nohurtcam.NoHurtCamClient;
import com.ruskei.nohurtcam.NoHurtCamConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow protected abstract void tiltViewWhenHurt(MatrixStack matrices, float tickDelta);

    @Redirect(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;tiltViewWhenHurt(Lnet/minecraft/client/util/math/MatrixStack;F)V"))
    private void renderWorldInjected(GameRenderer instance, MatrixStack matrices, float tickDelta) {
        NoHurtCamConfig config = AutoConfig.getConfigHolder(NoHurtCamConfig.class).getConfig();
        if (config.shakeWorld) tiltViewWhenHurt(matrices, tickDelta);
    }

    @Redirect(method = "renderHand", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;tiltViewWhenHurt(Lnet/minecraft/client/util/math/MatrixStack;F)V"))
    private void renderHandInjected(GameRenderer instance, MatrixStack matrices, float tickDelta) {
        NoHurtCamConfig config = AutoConfig.getConfigHolder(NoHurtCamConfig.class).getConfig();
        if (config.shakeHand) tiltViewWhenHurt(matrices, tickDelta);
    }
}
