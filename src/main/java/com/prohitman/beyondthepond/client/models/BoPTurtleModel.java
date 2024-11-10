package com.prohitman.beyondthepond.client.models;

import com.prohitman.beyondthepond.entities.BoPDolphin;
import com.prohitman.beyondthepond.entities.BoPTurtle;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class BoPTurtleModel extends DefaultedEntityGeoModel<BoPTurtle> {
    public BoPTurtleModel(ResourceLocation assetSubpath) {
        super(assetSubpath, true);
    }
}
