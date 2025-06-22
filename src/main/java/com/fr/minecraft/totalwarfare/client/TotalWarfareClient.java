package com.fr.minecraft.totalwarfare.client;

import com.fr.minecraft.totalwarfare.TotalWarfare;
import com.fr.minecraft.totalwarfare.client.renderer.M9GunRenderer;
import com.fr.minecraft.totalwarfare.item.M9GunItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

//Je sais pas trop à quoi ça sert mais c'est utile pour la base d'un projet NéoForge
@EventBusSubscriber(modid = TotalWarfare.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class TotalWarfareClient {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        //TotalWarfareClient.registerRenderers(event::registerEntityRenderer, event::registerBlockEntityRenderer);
    }
}
