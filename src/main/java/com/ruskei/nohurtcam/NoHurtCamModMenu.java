package com.ruskei.nohurtcam;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class NoHurtCamModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return NoHurtCamModMenu::getConfigScreen;
    }

    public static Screen getConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("title.no-hurt-cam.config"));

        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.no-hurt-cam.general"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.no-hurt-cam.worldHurtShake"), NoHurtCamConfig.worldHurtShake, 0, 120)
                .setDefaultValue(0)
                .setTooltip(Text.of("0-120%"))
                .setSaveConsumer(newValue -> NoHurtCamConfig.worldHurtShake = newValue)
                .build());

        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.no-hurt-cam.handHurtShake"), NoHurtCamConfig.handHurtShake, 0, 120)
                .setDefaultValue(100)
                .setTooltip(Text.of("0-120%"))
                .setSaveConsumer(newValue -> NoHurtCamConfig.handHurtShake = newValue)
                .build());

        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.no-hurt-cam.worldBobbing"), NoHurtCamConfig.worldBobbing, 0, 120)
                .setDefaultValue(100)
                .setTooltip(Text.of("0-120%"))
                .setSaveConsumer(newValue -> NoHurtCamConfig.worldBobbing = newValue)
                .build());

        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.no-hurt-cam.handBobbing"), NoHurtCamConfig.handBobbing, 0, 120)
                .setDefaultValue(100)
                .setTooltip(Text.of("0-120%"))
                .setSaveConsumer(newValue -> NoHurtCamConfig.handBobbing = newValue)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.no-hurt-cam.directionalTilt"), NoHurtCamConfig.directionalTilt)
                .setTooltip(Text.translatable("tooltip.no-hurt-cam.tiltToggle"))
                .setSaveConsumer(newValue -> NoHurtCamConfig.directionalTilt = newValue)
                .build());

        general.addEntry(entryBuilder.startIntField(Text.translatable("option.no-hurt-cam.x"), NoHurtCamConfig.x)
                .setDefaultValue(8)
                .setTooltip(Text.translatable("tooltip.no-hurt-cam.x"))
                .setSaveConsumer(newValue -> NoHurtCamConfig.x = newValue)
                .build());

        general.addEntry(entryBuilder.startIntField(Text.translatable("option.no-hurt-cam.y"), NoHurtCamConfig.y)
                .setDefaultValue(8)
                .setTooltip(Text.translatable("tooltip.no-hurt-cam.y"))
                .setSaveConsumer(newValue -> NoHurtCamConfig.y = newValue)
                .build());

        builder.setSavingRunnable(NoHurtCamConfig::save);

        parent = builder.build();
        return parent;
    }
}
