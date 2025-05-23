package com.prohitman.beyondthepond.entities;

import com.prohitman.beyondthepond.entities.goals.BoPJumpGoal;
import com.prohitman.beyondthepond.entities.goals.BoPWaterBoundPathNavigation;
import com.prohitman.beyondthepond.init.ModEntities;
import com.sun.source.doctree.AttributeTree;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.StructureTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import org.w3c.dom.Attr;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class BoPDolphin extends WaterAnimal implements GeoEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(BoPDolphin.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(BoPDolphin.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> BEACHED = SynchedEntityData.defineId(BoPDolphin.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> PANICKING = SynchedEntityData.defineId(BoPDolphin.class, EntityDataSerializers.BOOLEAN);
    static final TargetingConditions SWIM_WITH_PLAYER_TARGETING = TargetingConditions.forNonCombat().range(10.0).ignoreLineOfSight();
    public static final Predicate<ItemEntity> ALLOWED_ITEMS = p_350083_ -> !p_350083_.hasPickUpDelay() && p_350083_.isAlive() && p_350083_.isInWater();

    public boolean canIdle;
    public boolean canBeLeashed;
    public double maxHealth;
    public double maxSpeed;
    public int maxAirSupply;
    public int maxMoistnessLevel;
    public float maxRotation=90;
    public float maxHeadRotation;

    public BoPDolphin(EntityType<? extends BoPDolphin> pEntityType, Level pLevel, double maxHealth, double maxSpeed, int maxAirSupply, int maxMoistnessLevel, boolean canIdle, boolean canBeLeashed, int maxTurnX, int maxTurnY, boolean noCulling, float inWaterSpeed) {
        super(pEntityType, pLevel);
        this.moveControl = pEntityType == ModEntities.HUMPBACK_WHALE.get() ? new AnimalSwimMoveControllerSink(this, 1,1, 3) : new SmoothSwimmingMoveControl(this, maxTurnX, maxTurnY, inWaterSpeed, 0.05F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.setCanPickUpLoot(true);
        this.maxAirSupply = maxAirSupply;
        this.maxHealth = maxHealth;
        this.maxSpeed = maxSpeed;
        this.canIdle = canIdle;
        this.canBeLeashed = canBeLeashed;
        this.maxMoistnessLevel = maxMoistnessLevel;
        this.noCulling = noCulling;
    }

    @Override
    public float maxUpStep() {
        if(this.getType() == ModEntities.HUMPBACK_WHALE.get()){
            return 1.25f;
        }
        return super.maxUpStep();
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
        this.setAirSupply(this.getMaxAirSupply());
        this.setXRot(0.0F);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
        this.setHealth(this.getMaxHealth());
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(maxSpeed);
        this.setMoisntessLevel(maxMoistnessLevel);
        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }

    @Override
    protected void handleAirSupply(int pAirSupply) {
    }

    public boolean isPanicked(){
        return this.entityData.get(PANICKING);
    }
    public void setPanicked(boolean panicked){
        updateRotation(panicked);
        this.entityData.set(PANICKING, panicked);
    }
    public boolean isBeached(){
        return this.entityData.get(BEACHED);
    }
    public void setBeached(boolean beached){
        if(beached){
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
        } else {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(maxSpeed);
        }
        this.entityData.set(BEACHED, beached);
    }
    public int getMoistnessLevel() {
        return this.entityData.get(MOISTNESS_LEVEL);
    }

    public void setMoisntessLevel(int pMoistnessLevel) {
        this.entityData.set(MOISTNESS_LEVEL, pMoistnessLevel);
    }
    public void updateRotation(boolean rotation){
        if(rotation){
            this.setMaxRotation(90);
        } else {
            this.setMaxRotation(maxHeadRotation);
        }
    }

    public float getMaxRotation(){
        return maxRotation;
    }
    public void setMaxRotation(float maxRotation){
        this.maxRotation = maxRotation;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(MOISTNESS_LEVEL, maxMoistnessLevel);
        pBuilder.define(BEACHED, false);
        pBuilder.define(PANICKING, false);
        pBuilder.define(ATTACKING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Moistness", this.getMoistnessLevel());
        pCompound.putBoolean("beached", this.isBeached());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setMoisntessLevel(pCompound.getInt("Moistness"));
        this.setBeached(pCompound.getBoolean("beached"));
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        //this.goalSelector.addGoal(1, new Dolphin.DolphinSwimToTreasureGoal(this));
        //this.goalSelector.addGoal(2, new Dolphin.DolphinSwimWithPlayerGoal(this, 4.0));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this){
            @Override
            public boolean canUse() {
                if(BoPDolphin.this.isBeached()){
                    return false;
                }
                return super.canUse();
            }
        });
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F){
            @Override
            public boolean canUse() {
                if(BoPDolphin.this.isBeached()){
                    return false;
                }
                return super.canUse();
            }
        });
        this.goalSelector.addGoal(5, new BoPJumpGoal(this, 10){
            @Override
            public boolean canUse() {
                if(BoPDolphin.this.getType() == ModEntities.MANATEE.get()){
                    return false;
                }
                return super.canUse();
            }
        });
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.2F, true));
        //this.goalSelector.addGoal(8, new Dolphin.PlayWithItemsGoal());
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        //this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, Guardian.class, 8.0F, 1.0, 1.0));
        //this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Guardian.class).setAlertOthers());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 1.2F)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.FOLLOW_RANGE, 64);
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new BoPWaterBoundPathNavigation(this, pLevel);
    }

    @Override
    public void playAttackSound() {
        this.playSound(SoundEvents.DOLPHIN_ATTACK, 1.0F, 1.0F);
    }

    @Override
    public int getMaxAirSupply() {
        return maxAirSupply;
    }

    @Override
    protected int increaseAirSupply(int pCurrentAir) {
        return this.getMaxAirSupply();
    }

    @Override
    public int getMaxHeadXRot() {
        return 1;
    }

    @Override
    public int getMaxHeadYRot() {
        return 1;
    }

    @Override
    protected boolean canRide(Entity pEntity) {
        return true;
    }

/*    @Override
    public boolean canTakeItem(ItemStack pItemstack) {
        EquipmentSlot equipmentslot = this.getEquipmentSlotForItem(pItemstack);
        return !this.getItemBySlot(equipmentslot).isEmpty() ? false : equipmentslot == EquipmentSlot.MAINHAND && super.canTakeItem(pItemstack);
    }*/

    /**
     * Tests if this entity should pick up a weapon or an armor piece. Entity drops current weapon or armor if the new one is better.
     */
/*    @Override
    protected void pickUpItem(ItemEntity pItemEntity) {
        if (this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
            ItemStack itemstack = pItemEntity.getItem();
            if (this.canHoldItem(itemstack)) {
                this.onItemPickup(pItemEntity);
                this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
                this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
                this.take(pItemEntity, itemstack.getCount());
                pItemEntity.discard();
            }
        }
    }*/



    @Override
    public void tick() {
        super.tick();
        if(!this.level().isClientSide){
            if(!this.isBeached() && !this.isInWaterOrBubble() && verticalCollision){
                this.setBeached(true);
            } else if(this.isBeached() && this.isInWaterOrBubble()) {
                this.setBeached(false);
            }
        }
        if (this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        } else {
            if (this.isInWaterRainOrBubble()) {
                this.setMoisntessLevel(maxMoistnessLevel);
            } else {
                this.setMoisntessLevel(this.getMoistnessLevel() - 1);
                if (this.getMoistnessLevel() <= 0) {
                    this.hurt(this.damageSources().dryOut(), 1.0F);
                }

                if (this.onGround()) {
                    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
/*                    this.setDeltaMovement(
                            this.getDeltaMovement()
                                    .add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F))
                    );
                    this.setYRot(this.random.nextFloat() * 360.0F);
                    this.setOnGround(false);
                    this.hasImpulse = true;*/
                } else if(this.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue() == 0){
                    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(maxSpeed);
                }
            }

            if (this.level().isClientSide && this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.03) {
                Vec3 vec3 = this.getViewVector(0.0F);
                float f = Mth.cos(this.getYRot() * (float) (Math.PI / 180.0)) * 0.3F;
                float f1 = Mth.sin(this.getYRot() * (float) (Math.PI / 180.0)) * 0.3F;
                float f2 = 1.2F - this.random.nextFloat() * 0.7F;

                for (int i = 0; i < 2; i++) {
                    this.level()
                            .addParticle(
                                    ParticleTypes.DOLPHIN,
                                    this.getX() - vec3.x * (double)f2 + (double)f,
                                    this.getY() - vec3.y,
                                    this.getZ() - vec3.z * (double)f2 + (double)f1,
                                    0.0,
                                    0.0,
                                    0.0
                            );
                    this.level()
                            .addParticle(
                                    ParticleTypes.DOLPHIN,
                                    this.getX() - vec3.x * (double)f2 - (double)f,
                                    this.getY() - vec3.y,
                                    this.getZ() - vec3.z * (double)f2 - (double)f1,
                                    0.0,
                                    0.0,
                                    0.0
                            );
                }
            }
        }
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 38) {
            this.addParticlesAroundSelf(ParticleTypes.HAPPY_VILLAGER);
        } else {
            super.handleEntityEvent(pId);
        }
    }

    private void addParticlesAroundSelf(ParticleOptions pParticleOption) {
        for (int i = 0; i < 7; i++) {
            double d0 = this.random.nextGaussian() * 0.01;
            double d1 = this.random.nextGaussian() * 0.01;
            double d2 = this.random.nextGaussian() * 0.01;
            this.level().addParticle(pParticleOption, this.getRandomX(1.0), this.getRandomY() + 0.2, this.getRandomZ(1.0), d0, d1, d2);
        }
    }

/*    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (!itemstack.isEmpty() && itemstack.is(ItemTags.FISHES)) {
            if (!this.level().isClientSide) {
                this.playSound(SoundEvents.DOLPHIN_EAT, 1.0F, 1.0F);
            }

            itemstack.consume(1, pPlayer);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }*/

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.DOLPHIN_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.DOLPHIN_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return this.isInWater() ? SoundEvents.DOLPHIN_AMBIENT_WATER : SoundEvents.DOLPHIN_AMBIENT;
    }

    @Override
    protected SoundEvent getSwimSplashSound() {
        return SoundEvents.DOLPHIN_SPLASH;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.DOLPHIN_SWIM;
    }

    protected boolean closeToNextPos() {
        BlockPos blockpos = this.getNavigation().getTargetPos();
        return blockpos != null ? blockpos.closerToCenterThan(this.position(), 12.0) : false;
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

    @Override
    public boolean canBeLeashed() {
        return canBeLeashed;
    }

    public RawAnimation getSwimAnimation(){
        return RawAnimation.begin().thenLoop("swim");
    }

    public RawAnimation getBeachedAnimation(){
        return RawAnimation.begin().thenLoop("beached");
    }
    public RawAnimation getIdleAnimation(){
        return RawAnimation.begin().thenLoop("idle");
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Dolphin", 5, this::moveAnimController));
    }

    protected <E extends BoPDolphin> PlayState moveAnimController(final AnimationState<E> event) {
        if (this.isInWaterOrBubble() && event.isMoving()){
            event.setAndContinue(getSwimAnimation());
        } else if(canIdle && !event.isMoving() && this.isInWaterOrBubble()) {
            event.setAndContinue(getIdleAnimation());
        } else if(this.isBeached()){
            event.setAndContinue(getBeachedAnimation());
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }

    @Override
    protected void pushEntities() {
        if(!(this.getType() == ModEntities.HUMPBACK_WHALE.get() && !this.isBeached())){
            super.pushEntities();
        }
    }

    static class AnimalSwimMoveControllerSink extends MoveControl {
        private final PathfinderMob entity;
        private final float speedMulti;
        private float ySpeedMod = 1;
        private float yawLimit = 10.0F;

        public AnimalSwimMoveControllerSink(PathfinderMob entity, float speedMulti, float ySpeedMod) {
            super(entity);
            this.entity = entity;
            this.speedMulti = speedMulti;
            this.ySpeedMod = ySpeedMod;
        }

        public AnimalSwimMoveControllerSink(PathfinderMob entity, float speedMulti, float ySpeedMod, float yawLimit) {
            super(entity);
            this.entity = entity;
            this.speedMulti = speedMulti;
            this.ySpeedMod = ySpeedMod;
            this.yawLimit = yawLimit;
        }

        public void tick() {

            if (this.operation == Operation.MOVE_TO && !this.entity.getNavigation().isDone()) {
                double lvt_1_1_ = this.wantedX - this.entity.getX();
                double lvt_3_1_ = this.wantedY - this.entity.getY();
                double lvt_5_1_ = this.wantedZ - this.entity.getZ();
                double lvt_7_1_ = lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_ + lvt_5_1_ * lvt_5_1_;
                if (lvt_7_1_ < 2.500000277905201E-7D) {
                    this.mob.setZza(0.0F);
                } else {
                    float lvt_9_1_ = (float) (Mth.atan2(lvt_5_1_, lvt_1_1_) * 57.2957763671875D) - 90.0F;
                    this.entity.setYRot(this.rotlerp(this.entity.getYRot(), lvt_9_1_, yawLimit));
                    this.entity.yBodyRot = this.entity.getYRot();
                    this.entity.yHeadRot = this.entity.getYRot();
                    float lvt_10_1_ = (float) (this.speedModifier * speedMulti /** 3*/ * this.entity.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.entity.isInWater()) {
                        if(lvt_3_1_ > 0 && entity.horizontalCollision){
                            this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, 0.08F, 0.0D));
                        }else{
                            this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, (double) this.entity.getSpeed() * lvt_3_1_ * 0.6D * ySpeedMod, 0.0D));
                        }
                        this.entity.setSpeed(lvt_10_1_ * 0.02F);
                        float lvt_11_1_ = -((float) (Mth.atan2(lvt_3_1_, Mth.sqrt((float) (lvt_1_1_ * lvt_1_1_ + lvt_5_1_ * lvt_5_1_))) * 57.2957763671875D));
                        lvt_11_1_ = Mth.clamp(Mth.wrapDegrees(lvt_11_1_), -85.0F, 85.0F);
                        this.entity.setXRot(this.rotlerp(this.entity.getXRot(), lvt_11_1_, 1.0F));
                        float lvt_12_1_ = Mth.cos(this.entity.getXRot() * 0.017453292F);
                        float lvt_13_1_ = Mth.sin(this.entity.getXRot() * 0.017453292F);
                        this.entity.zza = lvt_12_1_ * lvt_10_1_;
                        this.entity.yya = -lvt_13_1_ * lvt_10_1_;
                    } else {
                        this.entity.setSpeed(lvt_10_1_ * 0.1F);
                    }

                }
            } else {
                this.entity.setSpeed(0.0F);
                this.entity.setXxa(0.0F);
                this.entity.setYya(0.0F);
                this.entity.setZza(0.0F);
            }
        }
    }

    public static boolean checkWithRaritySpawnRules(
            EntityType<? extends WaterAnimal> pWaterAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom, int chance
    ) {
        int i = pLevel.getSeaLevel();
        int j = i - 20;
        return pPos.getY() >= j
                && pPos.getY() <= i
                && pLevel.getFluidState(pPos.below()).is(FluidTags.WATER)
                && pLevel.getBlockState(pPos.above()).is(Blocks.WATER)
                && pRandom.nextInt(chance) == 0;
    }
}
