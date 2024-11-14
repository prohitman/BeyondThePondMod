package com.prohitman.beyondthepond.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SunFish extends BoPFish{
    //Investigate body rotations from NW
    public SunFish(EntityType<? extends AbstractBoPFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, 18, 0.7, 0, 1, false, false, false, 5, true, true, 0.25f, 1.75f);
    }
}
