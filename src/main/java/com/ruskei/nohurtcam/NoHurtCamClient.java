package com.ruskei.nohurtcam;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class NoHurtCamClient implements ClientModInitializer {
    public static boolean isEnabled;
    public static boolean isHandEnabled;

    @Override
    public void onInitializeClient() {
        isEnabled = true;
        isHandEnabled = true;

        AutoConfig.register(NoHurtCamConfig.class, GsonConfigSerializer::new);
    }
}
