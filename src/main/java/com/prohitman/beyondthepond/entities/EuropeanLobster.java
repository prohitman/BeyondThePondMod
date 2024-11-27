package com.prohitman.beyondthepond.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public class EuropeanLobster extends BoPCrab{
    public EuropeanLobster(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel,
                6,
                0.2f,
                0,
                10,
                2800,
                false,
                false,
                false);
    }

    public RawAnimation getSwimAnimation(){
        return RawAnimation.begin().thenLoop("swim");
    }

    @Override
    protected <E extends BoPCrab> PlayState moveAnimController(AnimationState<E> event) {
        if(this.isInWaterOrBubble() && event.isMoving() && !this.onGround()){
            event.setAndContinue(getSwimAnimation());
        }
        return super.moveAnimController(event);
    }
}
