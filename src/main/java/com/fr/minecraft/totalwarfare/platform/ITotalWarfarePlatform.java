package com.fr.minecraft.totalwarfare.platform;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

//PAS TOUCHE (pour l'instant)
public interface ITotalWarfarePlatform {
    <T extends Item> Supplier<T> registerItem(String id, Supplier<T> supplier);
    <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab);
    CreativeModeTab.Builder newCreativeTabBuilder();
}
