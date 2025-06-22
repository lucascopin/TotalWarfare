package com.fr.minecraft.totalwarfare.client.models;

import com.fr.minecraft.totalwarfare.TotalWarfare;
import com.fr.minecraft.totalwarfare.item.M9GunItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

//Pareil que le renderer mais l'un ne fonctionne pas sans l'autre. A savoir que dans les autres classes ou on appelle la texture c'est cette classe qu'on appelle, et cette classe appelle le renderer.
public class M9GunModel extends GeoModel<M9GunItem> {
    @Override
    public ResourceLocation getModelResource(M9GunItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(TotalWarfare.MODID, "geo/item/m_nine.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(M9GunItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(TotalWarfare.MODID, "textures/item/m_nine.png");
    }

    @Override
    public ResourceLocation getAnimationResource(M9GunItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(TotalWarfare.MODID, "animations/m_nine.animation.json");    }
}
