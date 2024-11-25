package com.prohitman.beyondthepond.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.client.models.BoPDolphinModel;
import com.prohitman.beyondthepond.entities.BoPDolphin;
import com.prohitman.beyondthepond.entities.BoPOrca;
import com.prohitman.beyondthepond.init.ModEntities;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BoPDolphinRenderer <T extends BoPDolphin> extends GeoEntityRenderer<T> {
    public BoPDolphinRenderer(EntityRendererProvider.Context renderManager, String name, boolean turnsHead, boolean hasScale, float withScale, float shadow) {
        super(renderManager, new BoPDolphinModel<>(ResourceLocation.fromNamespaceAndPath(BeyondThePond.MODID, name), turnsHead));
        if(hasScale){
            this.withScale(withScale);
        }
        this.shadowRadius = shadow;
    }

    @Override
    public void preRender(PoseStack poseStack, T animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
        if(animatable.getType() == ModEntities.ORCA.get() && !animatable.isBeached()){
            poseStack.translate(0, -0.45f, 0);
        }

        if(animatable.getType() == ModEntities.HUMPBACK_WHALE.get() && !animatable.isBeached()){
            poseStack.translate(0, -1.25f, 0);
        }
    }
}
