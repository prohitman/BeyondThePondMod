package com.prohitman.beyondthepond.client.models;

import com.prohitman.beyondthepond.datagen.server.ModEntityTagsGen;
import com.prohitman.beyondthepond.entities.BoPDolphin;
import com.prohitman.beyondthepond.init.ModEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import javax.swing.text.html.parser.Entity;

public class BoPBaseModel<T extends LivingEntity & GeoEntity> extends DefaultedEntityGeoModel<T> {
    public BoPBaseModel(ResourceLocation assetSubpath, boolean turnsHead) {
        super(assetSubpath, turnsHead);
    }

    @Override
    public void setCustomAnimations(T animatable, long instanceId, AnimationState<T> animationState) {
        if(animatable != null && animatable.getType().is(ModEntityTagsGen.DOLPHINS)){
            GeoBone body = getAnimationProcessor().getBone("body");
            GeoBone tail = getAnimationProcessor().getBone("tail");
            GeoBone tailFin = getAnimationProcessor().getBone("tailFin");

            if (body != null) {
                EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

                body.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
                body.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);

                if (animatable.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7) {
                    body.setRotX(body.getRotX()+(-0.05F - 0.05F * Mth.cos(animationState.getPartialTick() * 0.3F)));
                    tail.setRotX(-0.1F * Mth.cos(animationState.getPartialTick() * 0.3F));
                    tailFin.setRotX(-0.2F * Mth.cos(animationState.getPartialTick() * 0.3F));
                }
            }
        }
    }
}
