package com.prohitman.beyondthepond.entities;

import com.prohitman.beyondthepond.entities.goals.BoPBottomWaterWanderGoal;
import com.prohitman.beyondthepond.entities.goals.BoPFindWaterGoal;
import com.prohitman.beyondthepond.entities.goals.BoPLeaveWaterGoal;
import com.prohitman.beyondthepond.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.UUID;

public class BoPCrab extends WaterAnimal implements GeoEntity, NeutralMob {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;
    public double maxHealth;
    public double maxSpeed;
    public double attackDamage;
    public int maxHeadRotation;
    public boolean fightsBack;
    public boolean driesOut;
    public boolean isFullyAquatic;
    public int maxAirSupply;

    public BoPCrab(EntityType<? extends WaterAnimal> pEntityType, Level pLevel, double maxHealth, double maxSpeed, double attackDamage, int maxHeadRotation, int maxAirSupply, boolean fightsBack, boolean driesOut, boolean isFullyAquatic/*, boolean flippedMovement*/) {
        super(pEntityType, pLevel);
        this.maxHealth = maxHealth;
        this.maxSpeed = maxSpeed;
        this.attackDamage = attackDamage;
        this.maxHeadRotation = maxHeadRotation;
        this.fightsBack = fightsBack;
        this.driesOut = driesOut;
        this.isFullyAquatic = isFullyAquatic;
        this.maxAirSupply = maxAirSupply;

        this.setPathfindingMalus(PathType.WATER, pEntityType == ModEntities.COCONUT_CRAB.get() ? 3 : 0);
    }

    @Override
    public float maxUpStep() {
        return 1;
    }

    @Override
    public int getMaxHeadYRot() {
        return maxHeadRotation;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
        this.setHealth(this.getMaxHealth());
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(maxSpeed);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(attackDamage);
        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5f, true){
            @Override
            public boolean canUse() {
                if(!BoPCrab.this.fightsBack){
                    return false;
                }
                return super.canUse();
            }
        });
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new BoPFindWaterGoal(this, isFullyAquatic ? 1 :40){
            @Override
            public boolean canUse() {
                if(BoPCrab.this.getType() == ModEntities.COCONUT_CRAB.get()){
                    return false;
                }
                return super.canUse();
            }
        });
        this.goalSelector.addGoal(2, new BoPLeaveWaterGoal(this){
            @Override
            public void start() {
                if(BoPCrab.this.getType() == ModEntities.COCONUT_CRAB.get()){
                    this.executionChance = 1;
                }
                super.start();
            }
        });
        this.goalSelector.addGoal(3, new BoPBottomWaterWanderGoal(this, 1.0D, 10, 10));
        this.goalSelector.addGoal(3, new PanicGoal(this, 0.65){
            @Override
            public boolean canUse() {
                if(BoPCrab.this.fightsBack){
                    return false;
                }
                return super.canUse();
            }
        });
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this){
            @Override
            public boolean canUse() {
                if(!BoPCrab.this.fightsBack){
                    return false;
                }
                return super.canUse();
            }
        });
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt){
            @Override
            public boolean canUse() {
                if(!BoPCrab.this.fightsBack){
                    return false;
                }
                return super.canUse();
            }
        });
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false){
            @Override
            public boolean canUse() {
                if(!BoPCrab.this.fightsBack){
                    return false;
                }
                return super.canUse();
            }
        });
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.level().isClientSide){
            //System.out.println("Speed: " + this.getSpeed() + " Speed Modifier: " + this.moveControl.getSpeedModifier());
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if(!this.level().isClientSide && this.fightsBack){
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5F)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.FOLLOW_RANGE, 16);
    }

    @Override
    protected void handleAirSupply(int pAirSupply) {
        if(driesOut){
            if (this.isAlive() && !this.isInWaterOrBubble()) {
                this.setAirSupply(pAirSupply - 1);
                if (this.getAirSupply() == -maxAirSupply) {
                    this.setAirSupply(0);
                    this.hurt(this.damageSources().drown(), 2.0F);
                }
            } else {
                this.setAirSupply(300);
            }
        }
    }

    @Override
    protected float getWaterSlowDown() {
        return 0.875f;
    }

    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
/*           if(this.jumping && (this.getType() == ModEntities.EUROPEAN_LOBSTER.get() || this.getType() == ModEntities.GIANT_ISOPOD.get() || this.getType() == ModEntities.SALLY_LIGHTFOOT_CRAB.get())){
                this.setDeltaMovement(this.getDeltaMovement().scale(1.4D));
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.5D, 0.0D));
            }else{*/
                this.setDeltaMovement(this.getDeltaMovement().scale(0.4D));
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.08D, 0.0D));
            //}

        } else {
            super.travel(travelVector);
        }

    }

    public float getWalkTargetValue(BlockPos pos, LevelReader worldIn) {
        return worldIn.getFluidState(pos.below()).isEmpty() && worldIn.getFluidState(pos).is(FluidTags.WATER) && this.getType() != ModEntities.COCONUT_CRAB.get() ? 10.0F : super.getWalkTargetValue(pos, worldIn);
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return this.getType() != ModEntities.COCONUT_CRAB.get() ? new CrabPathNavigator(this, pLevel) : super.createNavigation(pLevel);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.TROPICAL_FISH_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.TROPICAL_FISH_DEATH;
    }

    public RawAnimation getWalkingAnimation(){
        return RawAnimation.begin().thenLoop("walking");
    }

    public RawAnimation getIdleAnimation(){
        return RawAnimation.begin().thenLoop("idle");
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Crab", 5, this::moveAnimController));
    }

    protected <E extends BoPCrab> PlayState moveAnimController(final AnimationState<E> event) {
        if (event.isMoving()){
            event.setAndContinue(getWalkingAnimation());
        } else {
            event.setAndContinue(getIdleAnimation());
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if(this.fightsBack){
            this.addPersistentAngerSaveData(pCompound);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if(this.fightsBack){
            this.readPersistentAngerSaveData(this.level(), pCompound);
        }
    }

    @Override
    public void startPersistentAngerTimer() {
        if(this.fightsBack){
            this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
        }
    }

    @Override
    public void setRemainingPersistentAngerTime(int pTime) {
        if(this.fightsBack){
            this.remainingPersistentAngerTime = pTime;
        }
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        if(this.fightsBack){
            return this.remainingPersistentAngerTime;
        }

        return 0;
    }

    @Override
    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID pTarget) {
        if(this.fightsBack){
            this.persistentAngerTarget = pTarget;
        }
    }

    @javax.annotation.Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        if(this.fightsBack){
            return this.persistentAngerTarget;
        }

        return UUID.randomUUID();
    }
}
