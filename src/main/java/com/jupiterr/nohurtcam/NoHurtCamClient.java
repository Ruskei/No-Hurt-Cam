package com.jupiterr.nohurtcam;

import net.fabricmc.api.ClientModInitializer;

public class NoHurtCamClient implements ClientModInitializer {
    public static boolean isEnabled;
    public static boolean isHandEnabled;

    @Override
    public void onInitializeClient() {
        isEnabled = true;
        isHandEnabled = true;
        System.out.println("bada bing");
    }
}
