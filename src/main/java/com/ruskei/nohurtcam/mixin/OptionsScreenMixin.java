package com.ruskei.nohurtcam.mixin;

import com.ruskei.nohurtcam.NoHurtCamConfig;
import com.ruskei.nohurtcam.NoHurtCamModMenu;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {
    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        this.addDrawableChild(new TexturedButtonWidget(NoHurtCamConfig.x, NoHurtCamConfig.y, 19, 19, 0, 0, 19, new Identifier("no-hurt-cam", "textures/button.png"), 19, 38, button -> MinecraftClient.getInstance().setScreen(NoHurtCamModMenu.getConfigScreen(MinecraftClient.getInstance().currentScreen))));
    }
}
