package com.prohitman.beyondthepond.entities.goals;

import com.prohitman.beyondthepond.entities.BoPCrab;
import com.prohitman.beyondthepond.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class BoPBottomWaterWanderGoal extends RandomStrollGoal {
    private int range = 5;

    public BoPBottomWaterWanderGoal(BoPCrab creature, double speed, int interval) {
        super(creature, speed, interval);
    }

    public BoPBottomWaterWanderGoal(BoPCrab creature, double speed, int interval, int range) {
        super(creature, speed, interval);
        this.range = range;
    }

    public boolean canUse(){
        return super.canUse();
    }

    public boolean canContinueToUse() {
        return super.canContinueToUse();
    }

    @Nullable
    protected Vec3 getPosition() {
        if(!((BoPCrab)this.mob).isFullyAquatic){
            if(this.mob.isInWater() && this.mob.getType() != ModEntities.COCONUT_CRAB.get()) {
                return generateWaterPos();
            } else {
                return super.getPosition();
            }
        }else{
            return generateWaterPos();
        }
    }

    protected Vec3 generateWaterPos(){
        BlockPos blockpos = null;
        final RandomSource random = this.mob.getRandom();
        for (int i = 0; i < 15; i++) {
            BlockPos blockPos = this.mob.blockPosition().offset(random.nextInt(range) - range / 2, 3, random.nextInt(range) - range / 2);
            while ((this.mob.level().isEmptyBlock(blockPos) || this.mob.level().getFluidState(blockPos).is(FluidTags.WATER)) && blockPos.getY() > 1) {
                blockPos = blockPos.below();
            }
            if (isBottomOfSeafloor(this.mob.level(), blockPos.above())) {
                blockpos = blockPos;
            }
        }

        return blockpos != null ? new Vec3(blockpos.getX() + 0.5F, blockpos.getY() + 0.5F, blockpos.getZ() + 0.5F) : null;

    }

    private boolean isBottomOfSeafloor(LevelAccessor world, BlockPos pos){
        return world.getFluidState(pos).is(FluidTags.WATER) && world.getFluidState(pos.below()).isEmpty() && world.getBlockState(pos.below()).canOcclude();
    }
}
