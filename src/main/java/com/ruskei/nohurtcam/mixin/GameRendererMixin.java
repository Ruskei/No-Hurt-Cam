package com.ruskei.nohurtcam.mixin;

import com.ruskei.nohurtcam.NoHurtCamConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    protected GameRendererMixin(MinecraftClient client) {
        this.client = client;
    }

    @Shadow final MinecraftClient client;

    @Redirect(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;tiltViewWhenHurt(Lnet/minecraft/client/util/math/MatrixStack;F)V"))
    private void renderWorldInjected(GameRenderer instance, MatrixStack matrices, float tickDelta) {
        if (NoHurtCamConfig.worldShake / 100f > 0f) {
            // run the tilt view function, but change damage tilt based on world %
            if (this.client.getCameraEntity() instanceof LivingEntity) {
                float g;
                LivingEntity livingEntity = (LivingEntity)this.client.getCameraEntity();
                float f = (float)livingEntity.hurtTime - tickDelta;
                if (livingEntity.isDead()) {
                    g = Math.min((float)livingEntity.deathTime + tickDelta, 20.0f);
                    matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(40.0f - 8000.0f / (g + 200.0f)));
                }
                if (f < 0.0f) {
                    return;
                }
                f /= (float)livingEntity.maxHurtTime;
                f = MathHelper.sin(f * f * f * f * (float)Math.PI);
                g = livingEntity.getDamageTiltYaw();
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-g));
                float h = (float)((double)(-f) * 14.0 * (NoHurtCamConfig.worldShake / 100f));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(h));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(g));
            }
        }
    }

    @Redirect(method = "renderHand", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;tiltViewWhenHurt(Lnet/minecraft/client/util/math/MatrixStack;F)V"))
    private void renderHandInjected(GameRenderer instance, MatrixStack matrices, float tickDelta) {
        if (NoHurtCamConfig.handShake / 100f > 0f) {
            // run the tilt view function, but change damage tilt based on hand %
            if (this.client.getCameraEntity() instanceof LivingEntity) {
                float g;
                LivingEntity livingEntity = (LivingEntity)this.client.getCameraEntity();
                float f = (float)livingEntity.hurtTime - tickDelta;
                if (livingEntity.isDead()) {
                    g = Math.min((float)livingEntity.deathTime + tickDelta, 20.0f);
                    matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(40.0f - 8000.0f / (g + 200.0f)));
                }
                if (f < 0.0f) {
                    return;
                }
                f /= (float)livingEntity.maxHurtTime;
                f = MathHelper.sin(f * f * f * f * (float)Math.PI);
                g = livingEntity.getDamageTiltYaw();
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-g));
                float h = (float)((double)(-f) * 14.0 * (NoHurtCamConfig.handShake / 100f));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(h));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(g));
            }
        }
    }

    @Redirect(method = "tiltViewWhenHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getDamageTiltYaw()F"))
    private float entityTilt(LivingEntity entity) {
        if (!NoHurtCamConfig.directionalTilt) return entity.getDamageTiltYaw();
        else return 0f;
    }
}
