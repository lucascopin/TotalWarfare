package com.fr.minecraft.totalwarfare.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

//Création des touches personalisés, à savoir qu'elles sont utilisables uniquement côté client (pas pratique bordel (la source de tous nos soucis)).
public class ModKeyMapping {
    public static final KeyMapping SHOOT = new KeyMapping(
            "key.totalwarfare.shoot",
            InputConstants.Type.MOUSE.getOrCreate(GLFW.GLFW_MOUSE_BUTTON_LEFT).getValue(),
            "key.categories.totalwarfare"
    );
}