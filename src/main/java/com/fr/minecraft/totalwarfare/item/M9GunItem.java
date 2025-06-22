package com.fr.minecraft.totalwarfare.item;

import com.fr.minecraft.totalwarfare.client.renderer.M9GunRenderer;
import com.fr.minecraft.totalwarfare.entity.BulletEntity;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

import java.util.function.Consumer;

//Cette classe permet l'affichage et l'utilisation spécifique au M9.
public class M9GunItem extends GunItem{
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private static final RawAnimation SHOOT_ANIM = RawAnimation.begin().thenPlay("animation.m_nine.shoot");

    public M9GunItem(Properties properties) {
        super(properties);

        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createGeoRenderer (Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private M9GunRenderer renderer;

            @Override
            @Nullable
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new M9GunRenderer();
                return this.renderer;
            }
        });
    }
}