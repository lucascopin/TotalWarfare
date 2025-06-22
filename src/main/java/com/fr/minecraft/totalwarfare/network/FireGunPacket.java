
package com.fr.minecraft.totalwarfare.network;

import com.fr.minecraft.totalwarfare.item.GunItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ConfigurationTask;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.extensions.ICommonPacketListener;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

//Cette classe est le packet spécifique au tir via une arme.
public record FireGunPacket() implements CustomPacketPayload {

    public static final ResourceLocation ID = new ResourceLocation("totalwarfare", "fire_gun");
    public static final Type<FireGunPacket> TYPE = new Type<>(FireGunPacket.ID);
    public static final StreamCodec<FriendlyByteBuf, FireGunPacket> STREAM_CODEC = StreamCodec.unit(new FireGunPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class Handler implements IPayloadContext<FireGunPacket> {

        @Override
        public void handle(FireGunPacket payload, IPayloadContext context) {
            context.enqueueWork(() -> {
                ServerPlayer player = (ServerPlayer) context.player();
                ItemStack mainHand = player.getMainHandItem();
                if (mainHand.getItem() instanceof GunItem gun) {
                    gun.fire(player.level(), player);
                }
            });
        }

        @Override
        public ICommonPacketListener listener() {
            return null;
        }

        @Override
        public Player player() {
            return null;
        }

        @Override
        public CompletableFuture<Void> enqueueWork(Runnable runnable) {
            return null;
        }

        @Override
        public <T> CompletableFuture<T> enqueueWork(Supplier<T> supplier) {
            return null;
        }

        @Override
        public PacketFlow flow() {
            return null;
        }

        @Override
        public void handle(CustomPacketPayload customPacketPayload) {

        }

        @Override
        public void finishCurrentTask(ConfigurationTask.Type type) {

        }
    }
}


