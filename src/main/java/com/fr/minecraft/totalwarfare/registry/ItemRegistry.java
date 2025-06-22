package com.fr.minecraft.totalwarfare.registry;

import com.fr.minecraft.totalwarfare.TotalWarfare;
import com.fr.minecraft.totalwarfare.item.M9GunItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

//Ici on enregistre tous les items du mod.
public class ItemRegistry {
    public static void init() {}

    public static final Supplier<M9GunItem> M9_GUN = registerItem("m_nine",() -> new M9GunItem(new Item.Properties()));

    private static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return TotalWarfare.PLATFORM.registerItem(id,item);
    }

    public static final Supplier<CreativeModeTab> TOTALWARFARE_TAB = TotalWarfare.PLATFORM.registerCreativeModeTab("total_warfare_items", () -> TotalWarfare.PLATFORM.newCreativeTabBuilder()
            .title(Component.translatable("itemGroup." + TotalWarfare.MODID + ".total_warfare_items"))
            .icon(() -> new ItemStack(ItemRegistry.M9_GUN.get()))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(ItemRegistry.M9_GUN.get());
            })
            .build());
}
