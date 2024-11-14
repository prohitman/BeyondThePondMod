package com.prohitman.beyondthepond.client.renderers;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.client.models.BoPDolphinModel;
import com.prohitman.beyondthepond.entities.BoPDolphin;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoEntity;
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
}
