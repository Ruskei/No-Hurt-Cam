package com.ruskei.nohurtcam;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "no-hurt-cam")
public class NoHurtCamConfig implements ConfigData {
    public boolean shakeWorld = false;
    public boolean shakeHand = true;
}
