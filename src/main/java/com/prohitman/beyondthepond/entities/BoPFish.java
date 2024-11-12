package com.prohitman.beyondthepond.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.UUID;
import java.util.function.Predicate;

public class BoPFish extends AbstractFish implements GeoEntity, NeutralMob {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private static final Predicate<LivingEntity> SCARY_MOB = p_348288_ -> {
        if (p_348288_ instanceof Player player && player.isCreative()) {
            return false;
        }

        return !p_348288_.getType().is(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH);
    };
    static final TargetingConditions targetingConditions = TargetingConditions.forNonCombat()
            .ignoreInvisibilityTesting()
            .ignoreLineOfSight()
            .selector(SCARY_MOB);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;
    public boolean canFlop;
    public boolean fightsBack;
    public int maxHeadRotation;
    public boolean isBucketable;
    public double maxHealth;
    public double maxSpeed;
    public double attackDamage;
    public boolean isPoisonous;
    public boolean isDamaging;

    public BoPFish(EntityType<? extends AbstractFish> pEntityType, Level pLevel, double maxHealth, double maxSpeed, double attackDamage, int maxHeadRotation, boolean isBucketable, boolean fightsBack, boolean canFlop, int xp, boolean isPoisonous, boolean isDamaging) {
        super(pEntityType, pLevel);
        this.canFlop = canFlop;
        this.fightsBack = fightsBack;
        this.maxHeadRotation = maxHeadRotation;
        this.isBucketable = isBucketable;
        this.xpReward = xp;
        this.maxHealth = maxHealth;
        this.maxSpeed = maxSpeed;
        this.attackDamage = attackDamage;
        this.isPoisonous = isPoisonous;
        this.isDamaging = isDamaging;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, true){
            @Override
            public boolean canUse() {
                if(!BoPFish.this.fightsBack){
                    return false;
                }
                return super.canUse();
            }
        });
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6, 1.4, EntitySelector.NO_SPECTATORS::test){
            @Override
            public boolean canUse() {
                if(BoPFish.this.fightsBack){
                    return false;
                }
                return super.canUse();
            }
        });
        this.goalSelector.addGoal(4, new BoPFish.FishSwimGoal(this));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.25){
            @Override
            public boolean canUse() {
                if(BoPFish.this.fightsBack){
                    return false;
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
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false){
            @Override
            public boolean canUse() {
                if(!BoPFish.this.fightsBack){
                    return false;
                }
                return super.canUse();
            }
        });
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if(!this.level().isClientSide && this.fightsBack){
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }
        if (this.isAlive() && (isPoisonous || isDamaging)) {
            for (LivingEntity mob : this.level()
                    .getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.3), p_149013_ -> targetingConditions.test(this, p_149013_))) {
                if (mob.isAlive()) {
                    this.touch(mob);
                }

            }
        }
    }

    private void touch(LivingEntity pMob) {
        if (pMob.hurt(this.damageSources().mobAttack(this), 1)) {
            if(isPoisonous){
                pMob.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
            }
            this.playSound(SoundEvents.PUFFER_FISH_STING, 1.0F, 1.0F);
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
        this.setHealth(this.getMaxHealth());
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(attackDamage);
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(maxSpeed);

        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 1)
                .add(Attributes.ATTACK_DAMAGE, 1);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getTarget() != null){
            //System.out.println("Has target");
        }
    }

    @Override
    public int getMaxHeadYRot() {
        return maxHeadRotation;
    }

    public RawAnimation getSwimAnimation(){
        return RawAnimation.begin().thenLoop("swim");
    }

    public RawAnimation getFlopAnimation(){
        return RawAnimation.begin().thenLoop("flop");
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Fish", 5, this::moveAnimController));
    }

    protected <E extends BoPFish> PlayState moveAnimController(final AnimationState<E> event) {
        if (this.isInWaterOrBubble() || !canFlop){
            event.setAndContinue(getSwimAnimation());
        } else {
            event.setAndContinue(getFlopAnimation());
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.COD_HURT;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return null;
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if(this.isBucketable){
            return super.mobInteract(pPlayer, pHand);
        } else {
            return InteractionResult.PASS;
        }
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

    static class FishSwimGoal extends RandomSwimmingGoal {
        private final BoPFish fish;

        public FishSwimGoal(BoPFish pFish) {
            super(pFish, 1.0, 40);
            this.fish = pFish;
        }

        @Override
        public boolean canUse() {
            return this.fish.canRandomSwim() && super.canUse();
        }
    }
}
