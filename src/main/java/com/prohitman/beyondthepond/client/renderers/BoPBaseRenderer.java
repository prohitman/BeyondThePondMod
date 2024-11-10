package com.prohitman.beyondthepond.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.datagen.server.ModEntityTagsGen;
import com.prohitman.beyondthepond.entities.BoPTurtle;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.TurtleModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TurtleRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BoPBaseRenderer<T extends Entity & GeoEntity> extends GeoEntityRenderer<T> {
    public BoPBaseRenderer(EntityRendererProvider.Context renderManager, String name, boolean turnsHead, boolean hasScale, float withScale, float shadow) {
        super(renderManager, new DefaultedEntityGeoModel<>(ResourceLocation.fromNamespaceAndPath(BeyondThePond.MODID, name), turnsHead));
        if(hasScale){
            this.withScale(withScale);
        }
        this.shadowRadius = shadow;
    }
    protected float getShadowRadius(T pEntity) {
        if(pEntity.getType().is(ModEntityTagsGen.TURTLES)){
            float f = super.getShadowRadius(pEntity);
            return ((Animal)pEntity).isBaby() ? f * 0.27F : f;
        }
        return super.getShadowRadius(pEntity);
    }

    @Override
    public float getMotionAnimThreshold(T animatable) {
        if(animatable instanceof BoPTurtle){
            return 0.000001f;
        }
        return super.getMotionAnimThreshold(animatable);
    }

    @Override
    public void preRender(PoseStack poseStack, T animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        if(animatable != null && animatable.getType().is(ModEntityTagsGen.TURTLES) && ((Animal)animatable).isBaby()){
            float f1 = 1.0F / 6;
            poseStack.scale(f1, f1, f1);
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}