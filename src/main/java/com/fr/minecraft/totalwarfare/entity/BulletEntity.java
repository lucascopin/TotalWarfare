package com.fr.minecraft.totalwarfare.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

//Cette classe est un moyen de créer une balle visible (à voir comment on fait) en tant qu'entité.
public class BulletEntity extends ThrowableProjectile {
    private static final EntityDataAccessor<Boolean> IS_ACTIVE =
            SynchedEntityData.defineId(BulletEntity.class, EntityDataSerializers.BOOLEAN);
    public BulletEntity(EntityType<? extends BulletEntity> type, Level world) {
        super(type, world);
    }

    public BulletEntity(Level world, LivingEntity shooter) {
        super(ModEntities.BULLET.get(), shooter, world);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity target = result.getEntity();
        target.hurt(
                target.level().damageSources().thrown(this, this.getOwner()),
                6.0F
        );        this.discard(); // Supprime la balle après impact
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.discard(); // Supprime la balle après impact
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(IS_ACTIVE, false);
        boolean isActive = this.getEntityData().get(IS_ACTIVE);
        this.getEntityData().set(IS_ACTIVE, true);
    }
}