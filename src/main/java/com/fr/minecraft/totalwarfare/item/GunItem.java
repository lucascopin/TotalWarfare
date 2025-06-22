package com.fr.minecraft.totalwarfare.item;

import com.fr.minecraft.totalwarfare.entity.BulletEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.AnimationController;

//Cette classe défini ce qu'est une arme.
public abstract class GunItem extends Item implements GeoItem {

    protected final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    protected static final RawAnimation SHOOT_ANIM = RawAnimation.begin().thenPlay("shoot");

    public GunItem(Properties properties) {
        super(properties);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "shoot_controller", state -> PlayState.STOP)
                .triggerableAnim("shoot", SHOOT_ANIM));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    /**
     * Méthode de tir appelée dans le GunEventHandler
     */
    public void fire(Level world, Player player) {
        System.out.println(!world.isClientSide);
        if (!world.isClientSide) {
            System.out.println("Tir dans GunItem");
            BulletEntity bullet = new BulletEntity(world, player);
            bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, getBulletSpeed(), 1.0F);
            world.addFreshEntity(bullet);
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    getShootSound(), SoundSource.PLAYERS, 1.0F, 1.0F);

            triggerAnim(player, GeoItem.getOrAssignId(player.getMainHandItem(), (ServerLevel) world),
                    "shoot_controller", "shoot");
        }

        player.getCooldowns().addCooldown(this, getCooldownTicks());
    }

    // Ces méthodes sont faites pour être overridées par les armes spécifiques
    protected int getCooldownTicks() {
        return 10;
    }

    protected float getBulletSpeed() {
        return 3.0f;
    }

    protected SoundEvent getShootSound() {
        return SoundEvents.GENERIC_EXPLODE.value();
    }
}