package com.prohitman.beyondthepond.entities;

import com.prohitman.beyondthepond.entities.goals.BoPGoToWaterGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.UUID;
import java.util.function.Predicate;

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
    public boolean prefersWater;
    public int maxAirSupply;

    public BoPCrab(EntityType<? extends WaterAnimal> pEntityType, Level pLevel, double maxHealth, double maxSpeed, double attackDamage, int maxHeadRotation, int maxAirSupply, boolean fightsBack, boolean driesOut, boolean prefersWater/*, boolean flippedMovement*/) {
        super(pEntityType, pLevel);
        this.maxHealth = maxHealth;
        this.maxSpeed = maxSpeed;
        this.attackDamage = attackDamage;
        this.maxHeadRotation = maxHeadRotation;
        this.fightsBack = fightsBack;
        this.driesOut = driesOut;
        this.prefersWater = prefersWater;
        this.maxAirSupply = maxAirSupply;

        this.setPathfindingMalus(PathType.WATER, prefersWater ? 0 : 1);
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
        //Fix random stroll goal for drying out mobs
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new BoPGoToWaterGoal(this, 1.2f));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.25){
            @Override
            public boolean canUse() {
                if(this.mob instanceof BoPCrab crab){
                    if(crab.fightsBack){
                        return false;
                    }
                }
                return super.canUse();
            }
        });
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this){
            @Override
            public boolean canUse() {
                if(this.mob instanceof BoPFish fish){
                    if(!fish.fightsBack){
                        return false;
                    }
                }
                return super.canUse();
            }
        });
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt){
            @Override
            public boolean canUse() {
                if(this.mob instanceof BoPFish fish){
                    if(!fish.fightsBack){
                        return false;
                    }
                }
                return super.canUse();
            }
        });
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
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
                .add(Attributes.MOVEMENT_SPEED, 1.2F)
                .add(Attributes.ATTACK_DAMAGE, 3.0);
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
