package com.fr.minecraft.totalwarfare.entity;

import com.fr.minecraft.totalwarfare.TotalWarfare;
import com.fr.minecraft.totalwarfare.entity.BulletEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

//Cette classe permet l'utilisation d'entités custom
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, TotalWarfare.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<BulletEntity>> BULLET =
            ENTITY_TYPES.register("bullet", () ->
                    EntityType.Builder.<BulletEntity>of(BulletEntity::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F) // Taille de l'entité
                            .clientTrackingRange(64) // Distance de suivi côté client
                            .updateInterval(1) // Fréquence de mise à jour
                            .build(ResourceLocation.fromNamespaceAndPath(TotalWarfare.MODID, "bullet").getPath()));}