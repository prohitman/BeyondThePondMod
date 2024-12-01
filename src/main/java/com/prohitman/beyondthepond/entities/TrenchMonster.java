package com.prohitman.beyondthepond.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TrenchMonster extends PathfinderMob implements GeoEntity {
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(TrenchMonster.class, EntityDataSerializers.BOOLEAN);
    public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    public static final RawAnimation ATTACK = RawAnimation.begin().thenPlay("attack");
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public TrenchMonster(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 10;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
        this.setXRot(this.getRandom().nextInt(0, 360));
        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }

    public boolean isAttacking(){
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(boolean is_attacking){
        this.entityData.set(ATTACKING, is_attacking);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(ATTACKING, false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        //this.goalSelector.addGoal(1, Mel);
    }



    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100)
                .add(Attributes.MOVEMENT_SPEED, 0)
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .add(Attributes.FOLLOW_RANGE, 0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 3);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Trench", 5, this::moveAnimController));
    }

    protected <E extends TrenchMonster> PlayState moveAnimController(final AnimationState<E> event) {
        if(this.isAttacking()){
            event.setAndContinue(ATTACK);
        } else {
            event.setAndContinue(IDLE);
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    public static boolean checkSurfaceWaterAnimalSpawnRules(
            EntityType<? extends TrenchMonster> pWaterAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom
    ) {
        return pLevel.getFluidState(pPos.below()).is(FluidTags.WATER)
                && pLevel.getBlockState(pPos.above()).is(Blocks.WATER);
    }
}
