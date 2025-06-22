package com.fr.minecraft.totalwarfare.network;

import com.fr.minecraft.totalwarfare.TotalWarfare;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.ChannelBuilder;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

//Visiblement cette classe créé les packets à envoyer au serveur via le client. il y a des problèmes d'imports
public class ModPackets {

    public static final Channel INSTANCE = ChannelBuilder.named(new ResourceLocation(TotalWarfare.MODID, "main"))
            .networkProtocolVersion(1)
            .simpleChannel();

    public static void register(RegisterPayloadHandlersEvent event) {
        INSTANCE.registerPayload(FireGunPacket.TYPE, new FireGunPacket.Handler());
    }
}

