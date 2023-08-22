package com.ruskei.nohurtcam;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NoHurtCamConfig {
    public static int worldShake;
    public static int handShake;
    public static boolean directionalTilt;

    public static void load() {
        Path filePath = FabricLoader.getInstance().getConfigDir().resolve("no-hurt-cam.json");

        try {
            if (Files.exists(filePath)) {
                byte[] jsonData = Files.readAllBytes(filePath);
                JSONObject config = new JSONObject(new String(jsonData));

                worldShake = config.getInt("worldShake");
                handShake = config.getInt("handShake");
                directionalTilt = config.getBoolean("directionalTilt");
            } else {
                worldShake = 0;
                handShake = 100;
                directionalTilt = false;

                save();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            JSONObject config = new JSONObject();
            config.put("worldShake", worldShake);
            config.put("handShake", handShake);
            config.put("directionalTilt", directionalTilt);

            Path filePath = FabricLoader.getInstance().getConfigDir().resolve("no-hurt-cam.json");

            byte[] jsonData = config.toString().getBytes();

            Files.write(filePath, jsonData, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
