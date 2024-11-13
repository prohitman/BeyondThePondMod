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
        super(pEntityType, pLevel, 18, 0.7, 0, 1, false, false, false, 5, true, true, 0.5f, 1.5f);
        this.moveControl = new SmoothSwimmingMoveControl(this, 0, 35, 0.01F, 0, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 15);
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(pTravelVector);
        }
    }
}
