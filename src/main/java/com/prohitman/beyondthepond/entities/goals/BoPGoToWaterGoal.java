package com.prohitman.beyondthepond.entities.goals;

import com.prohitman.beyondthepond.entities.BoPCrab;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;

public class BoPGoToWaterGoal extends MoveToBlockGoal {
    private static final int GIVE_UP_TICKS = 1200;
    private final BoPCrab crab;

    public BoPGoToWaterGoal(BoPCrab crab, double pSpeedModifier) {
        super(crab, crab.isBaby() ? 1.5 : pSpeedModifier, 24);
        this.crab = crab;
        this.verticalSearchStart = -1;
    }

    @Override
    public boolean canContinueToUse() {
        return !this.crab.isInWater() && this.tryTicks <= 1200 && this.isValidTarget(this.crab.level(), this.blockPos);
    }

    @Override
    public boolean canUse() {
        if(!crab.prefersWater){
            return false;
        }
        if (!this.crab.isInWater()) {
            return super.canUse();
        }
        return false;
    }

    @Override
    public boolean shouldRecalculatePath() {
        return this.tryTicks % 160 == 0;
    }

    /**
     * Return {@code true} to set given position as destination
     */
    @Override
    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos).is(Blocks.WATER);
    }

    @Override
    public boolean isInterruptable() {
        return false;
    }
}
