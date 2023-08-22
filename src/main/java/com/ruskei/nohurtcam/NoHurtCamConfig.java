package com.ruskei.nohurtcam;

import net.fabricmc.loader.api.FabricLoader;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class NoHurtCamConfig {
    public static int worldShake;
    public static int handShake;
    public static boolean directionalTilt;

    public static int x;
    public static int y;

    public static void load() {
        Path filePath = FabricLoader.getInstance().getConfigDir().resolve("no-hurt-cam.json");

        try {
            if (Files.exists(filePath)) {
                byte[] jsonData = Files.readAllBytes(filePath);
                JSONObject config = new JSONObject(new String(jsonData));

                boolean old = false;

                if (config.has("shakeWorld")) {
                    int i = (Boolean) config.get("shakeWorld") ? 1 : 0;
                    worldShake = i * 100;
                    old = true;
                }

                if (config.has("shakeHand")) {
                    int i = (Boolean) config.get("shakeHand") ? 1 : 0;
                    handShake = i * 100;
                    old = true;
                }

                if (old) {
                    directionalTilt = false;
                    x = 8;
                    y = 8;

                    save();
                    return;
                }

                worldShake = config.getInt("worldShake");
                handShake = config.getInt("handShake");
                directionalTilt = config.getBoolean("directionalTilt");
                x = config.getInt("x");
                y = config.getInt("y");
            } else {
                worldShake = 0;
                handShake = 100;
                directionalTilt = false;
                x = 8;
                y = 8;

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
            config.put("x", x);
            config.put("y", y);

            Path filePath = FabricLoader.getInstance().getConfigDir().resolve("no-hurt-cam.json");

            byte[] jsonData = config.toString().getBytes();

            Files.write(filePath, jsonData, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
