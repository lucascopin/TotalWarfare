package com.fr.minecraft.totalwarfare.client.renderer;

import com.fr.minecraft.totalwarfare.TotalWarfare;
import com.fr.minecraft.totalwarfare.client.models.M9GunModel;
import com.fr.minecraft.totalwarfare.item.M9GunItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoItemRenderer;

//C'est la classe appelée pour afficher le model et les animations de nos items 3D, plus spécifiquement celle du M9 ici.
public class M9GunRenderer extends GeoItemRenderer<M9GunItem> {

    // Noms des os dans le modèle Blockbench qui représentent les faux bras
    private static final String RIGHT_ARM_BONE = "right_arm";
    private static final String LEFT_ARM_BONE = "left_arm";

    // Variable pour déterminer quand afficher les bras
    private boolean shouldRenderArms = false;

    public M9GunRenderer() {
        super(new M9GunModel());
    }

    // Méthode pour déterminer si on doit afficher les bras
    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack,
                             MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        // Déterminer le contexte d'affichage
        boolean isFirstPerson = displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND ||
                displayContext == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND;

        // Masquer les bras si nécessaire avant le rendu
        BakedGeoModel model = getGeoModel().getBakedModel(getGeoModel().getModelResource(getAnimatable()));
        if (model != null) {
            // Accéder aux os individuellement par leur nom
            GeoBone rightArmBone = model.getBone(RIGHT_ARM_BONE).orElse(null);
            GeoBone leftArmBone = model.getBone(LEFT_ARM_BONE).orElse(null);

            // Masquer les bras si nécessaire
            if (rightArmBone != null && isFirstPerson) {
                rightArmBone.setHidden(shouldRenderArms);
            }else {
                rightArmBone.setHidden(!shouldRenderArms);
            }
            if (leftArmBone != null && isFirstPerson) {
                leftArmBone.setHidden(shouldRenderArms);
            }else {
                leftArmBone.setHidden(!shouldRenderArms);
            }
        }


        super.renderByItem(stack, displayContext, poseStack, bufferSource, packedLight, packedOverlay);
    }

    // Override la méthode qui détermine quelle texture utiliser pour chaque partie
    @Override
    public ResourceLocation getTextureLocation(M9GunItem animatable) {
        // Si nous sommes en train de rendre les bras en troisième personne, utiliser la texture du skin
        if (shouldRenderArms) {
            return getSkinTexture();
        }
        // Sinon, utiliser la texture normale du modèle
        return super.getTextureLocation(animatable);
    }

    /**
     * Récupère la texture du skin du joueur actuel
     */
    private ResourceLocation getSkinTexture() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player instanceof AbstractClientPlayer player) {
            return player.getSkin().texture();
        }
        // Fallback sur une texture par défaut si nécessaire
        try {
            return ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/steve.png");
        } catch (Exception e) {
            // Si le constructeur n'est pas disponible, utiliser une méthode alternative
            return Minecraft.getInstance().player.getSkin().texture();
        }
    }
}
