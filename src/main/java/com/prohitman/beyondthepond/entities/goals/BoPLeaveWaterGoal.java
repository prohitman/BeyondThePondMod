package com.prohitman.beyondthepond.entities.goals;

import com.prohitman.beyondthepond.entities.BoPCrab;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class BoPLeaveWaterGoal  extends Goal {
    private final BoPCrab creature;
    private BlockPos targetPos;
    public int executionChance = 25;

    public BoPLeaveWaterGoal(BoPCrab creature) {
        this.creature = creature;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        if(this.creature.isFullyAquatic){
            return false;
        }
        if (this.creature.level().getFluidState(this.creature.blockPosition()).is(FluidTags.WATER) && (this.creature.getRandom().nextInt(executionChance) == 0)) {
            targetPos = generateTarget();
            return targetPos != null;
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
        if (this.creature.horizontalCollision && this.creature.isInWater()) {
            final float f1 = creature.getYRot() * Mth.DEG_TO_RAD;
            creature.setDeltaMovement(creature.getDeltaMovement().add(-Mth.sin(f1) * 0.2F, 0.1D, Mth.cos(f1) * 0.2F));

        }
    }

    public boolean canContinueToUse() {
        if (this.creature.getTarget() != null) {
            this.creature.getNavigation().stop();
            return false;
        }
        return !this.creature.getNavigation().isDone() && targetPos != null && !this.creature.level().getFluidState(targetPos).is(FluidTags.WATER);
    }

    public BlockPos generateTarget() {
        Vec3 vector3d = LandRandomPos.getPos(this.creature, 23, 7);
        int tries = 0;
        while(vector3d != null && tries < 8) {
            boolean waterDetected = false;
            for(BlockPos blockpos1 : BlockPos.betweenClosed(Mth.floor(vector3d.x - 2.0D), Mth.floor(vector3d.y - 1.0D), Mth.floor(vector3d.z - 2.0D), Mth.floor(vector3d.x + 2.0D), Mth.floor(vector3d.y), Mth.floor(vector3d.z + 2.0D))) {
                if (this.creature.level().getFluidState(blockpos1).is(FluidTags.WATER)) {
                    waterDetected = true;
                    break;
                }
            }
            if (waterDetected) {
                vector3d = LandRandomPos.getPos(this.creature, 23, 7);
            } else {
                return fromVec3(vector3d);
            }
            tries++;
        }
        return null;
    }

    public static final BlockPos fromCoords(double x, double y, double z){
        return new BlockPos((int) x, (int) y, (int) z);
    }

    public static final BlockPos fromVec3(Vec3 vec3){
        return fromCoords(vec3.x, vec3.y, vec3.z);
    }
}
