package com.prohitman.beyondthepond.client.renderers;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.entities.BoPFish;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BoPFishRenderer extends GeoEntityRenderer<BoPFish> {
    public BoPFishRenderer(EntityRendererProvider.Context renderManager, String name) {
        super(renderManager, new DefaultedEntityGeoModel<>(ResourceLocation.fromNamespaceAndPath(BeyondThePond.MODID, name)));
    }
}
