package com.prohitman.beyondthepond.entities.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class BoPFindWaterGoal extends Goal {
    private final PathfinderMob creature;
    private BlockPos targetPos;
    private int executionChance = 30;

    public BoPFindWaterGoal(PathfinderMob creature, int executionChance) {
        this.creature = creature;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        this.executionChance = executionChance;
    }

    public boolean canUse() {
        if(this.creature.getTarget() != null){
            return false;
        }
        if (this.creature.onGround() && !this.creature.level().getFluidState(this.creature.blockPosition()).is(FluidTags.WATER)) {
            if (this.creature.getRandom().nextInt(executionChance) == 0) {
                targetPos = generateTarget();
                return targetPos != null;
            }
        }
        return false;
    }

    public void start() {
        if (targetPos != null) {
            this.creature.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 1D);
        }
    }

    public void tick() {
        if (targetPos != null) {
            this.creature.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 1D);
        }
    }

    public boolean canContinueToUse() {
        if (this.creature.getTarget() != null) {
            this.creature.getNavigation().stop();
            return false;
        }
        return !this.creature.getNavigation().isDone() && targetPos != null && !this.creature.level().getFluidState(this.creature.blockPosition()).is(FluidTags.WATER);
    }

    public BlockPos generateTarget() {
        BlockPos blockpos = null;
        final RandomSource random = this.creature.getRandom();
        final int range = 14;
        for(int i = 0; i < 15; i++) {
            BlockPos blockPos = this.creature.blockPosition().offset(random.nextInt(range) - range/2, 3, random.nextInt(range) - range/2);
            while (this.creature.level().isEmptyBlock(blockPos) && blockPos.getY() > 1) {
                blockPos = blockPos.below();
            }

            if (this.creature.level().getFluidState(blockPos).is(FluidTags.WATER)) {
                blockpos = blockPos;
            }
        }
        return blockpos;
    }
}
