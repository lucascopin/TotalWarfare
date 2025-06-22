package com.fr.minecraft.totalwarfare.platform;

import com.fr.minecraft.totalwarfare.TotalWarfare;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

//PAS TOUCHE (pour l'instant)
public class TotalWarfarePlatform implements ITotalWarfarePlatform {

    @Override
    public <T extends Item> Supplier<T> registerItem(String id, Supplier<T> supplier) {
        return TotalWarfare.ITEMS.register(id, supplier);
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab) {
        return TotalWarfare.CREATIVE_TABS.register(id, tab);
    }

    @Override
    public CreativeModeTab.Builder newCreativeTabBuilder() {
        return CreativeModeTab.builder();
    }
}
