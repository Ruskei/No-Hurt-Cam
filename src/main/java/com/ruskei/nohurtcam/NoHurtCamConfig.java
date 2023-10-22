package com.ruskei.nohurtcam;

import net.fabricmc.loader.api.FabricLoader;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class NoHurtCamConfig {
    public static int worldHurtShake;
    public static int handHurtShake;
    public static boolean directionalTilt;

    public static int worldBobbing;
    public static int handBobbing;

    public static int x;
    public static int y;

    public static void load() {
        Path filePath = FabricLoader.getInstance().getConfigDir().resolve("no-hurt-cam.json");
        //set the defaults
        worldHurtShake = 0;
        handHurtShake = 100;
        directionalTilt = false;
        worldBobbing = 0;
        handBobbing = 100;
        x = 8;
        y = 8;

        try {
            if (Files.exists(filePath)) {
                byte[] jsonData = Files.readAllBytes(filePath);
                JSONObject config = new JSONObject(new String(jsonData));

                boolean old = false;

                //compatibility for older versions
                if (config.has("shakeWorld")) {
                    System.out.println("found shakeWorld");
                    int i = (Boolean) config.get("shakeWorld") ? 1 : 0;
                    worldHurtShake = i * 100;
                    old = true;
                }

                if (config.has("shakeHand")) {
                    System.out.println("found shakeHand");
                    int i = (Boolean) config.get("shakeHand") ? 1 : 0;
                    handHurtShake = i * 100;
                    old = true;
                }

                if (old) {
                    System.out.println("old old");
                    directionalTilt = false;
                    x = 8;
                    y = 8;

                    save();
                    return;
                }

                //some more compatibility
                if (config.has("worldShake")) {
                    worldHurtShake = config.getInt("worldShake");
                    old = true;
                }

                if (config.has("handShake")) {
                    handHurtShake = config.getInt("handShake");
                    old = true;
                }

                if (old) {
                    System.out.println("old");
                    directionalTilt = config.getBoolean("directionalTilt");
                    x = config.getInt("x");
                    y = config.getInt("y");

                    save();
                    return;
                }

                worldHurtShake = config.getInt("worldHurtShake");
                handHurtShake = config.getInt("handHurtShake");
                directionalTilt = config.getBoolean("directionalTilt");
                worldBobbing = config.getInt("worldBobbing");
                handBobbing = config.getInt("handBobbing");
                x = config.getInt("x");
                y = config.getInt("y");
            } else {
                System.out.println("file doesn't exist");
                worldHurtShake = 0;
                handHurtShake = 100;
                directionalTilt = false;
                worldBobbing = 0;
                handBobbing = 100;
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
            config.put("worldHurtShake", worldHurtShake);
            config.put("handHurtShake", handHurtShake);
            config.put("worldBobbing", worldBobbing);
            config.put("handBobbing", handBobbing);
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
