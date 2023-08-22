package com.ruskei.nohurtcam;

import net.fabricmc.api.ClientModInitializer;

public class NoHurtCamClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NoHurtCamConfig.load();
    }
}
