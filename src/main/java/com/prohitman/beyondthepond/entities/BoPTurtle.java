package com.prohitman.beyondthepond.entities;

import com.prohitman.beyondthepond.init.ModEntities;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
//import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Predicate;

public class BoPTurtle extends Animal implements GeoEntity {
    private static final EntityDataAccessor<BlockPos> HOME_POS = SynchedEntityData.defineId(BoPTurtle.class, EntityDataSerializers.BLOCK_POS);
    //private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(BoPTurtle.class, EntityDataSerializers.BOOLEAN);
    //private static final EntityDataAccessor<Boolean> LAYING_EGG = SynchedEntityData.defineId(BoPTurtle.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<BlockPos> TRAVEL_POS = SynchedEntityData.defineId(BoPTurtle.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> GOING_HOME = SynchedEntityData.defineId(BoPTurtle.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> TRAVELLING = SynchedEntityData.defineId(BoPTurtle.class, EntityDataSerializers.BOOLEAN);
    private static final float BABY_SCALE = 0.3F;
    private static final EntityDimensions BABY_DIMENSIONS = EntityType.TURTLE
            .getDimensions()
            .withAttachments(EntityAttachments.builder().attach(EntityAttachment.PASSENGER, 0.0F, EntityType.TURTLE.getHeight(), -0.25F))
            .scale(0.3F);
    int layEggCounter;
    public static final Predicate<LivingEntity> BABY_ON_LAND_SELECTOR = p_350092_ -> p_350092_.isBaby() && !p_350092_.isInWater();

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public double maxHealth;
    public double maxSpeed;
    public EntityType<? extends Animal> entityType;
    public BoPTurtle(EntityType<? extends Animal> pEntityType, Level pLevel, double maxHealth, double maxSpeed) {
        super(pEntityType, pLevel);
        this.maxHealth = maxHealth;
        this.maxSpeed = maxSpeed;
        this.entityType = pEntityType;
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.setPathfindingMalus(PathType.DOOR_IRON_CLOSED, -1.0F);
        this.setPathfindingMalus(PathType.DOOR_WOOD_CLOSED, -1.0F);
        this.setPathfindingMalus(PathType.DOOR_OPEN, -1.0F);
        this.moveControl = new BoPTurtle.TurtleMoveControl(this);
    }

    public void setHomePos(BlockPos pHomePos) {
        this.entityData.set(HOME_POS, pHomePos);
    }

    BlockPos getHomePos() {
        return this.entityData.get(HOME_POS);
    }

    void setTravelPos(BlockPos pTravelPos) {
        this.entityData.set(TRAVEL_POS, pTravelPos);
    }

    BlockPos getTravelPos() {
        return this.entityData.get(TRAVEL_POS);
    }

    /*public boolean hasEgg() {
        return this.entityData.get(HAS_EGG);
    }

    void setHasEgg(boolean pHasEgg) {
        this.entityData.set(HAS_EGG, pHasEgg);
    }

    public boolean isLayingEgg() {
        return this.entityData.get(LAYING_EGG);
    }

    void setLayingEgg(boolean pIsLayingEgg) {
        this.layEggCounter = pIsLayingEgg ? 1 : 0;
        this.entityData.set(LAYING_EGG, pIsLayingEgg);
    }*/

    boolean isGoingHome() {
        return this.entityData.get(GOING_HOME);
    }

    void setGoingHome(boolean pIsGoingHome) {
        this.entityData.set(GOING_HOME, pIsGoingHome);
    }

    boolean isTravelling() {
        return this.entityData.get(TRAVELLING);
    }

    void setTravelling(boolean pIsTravelling) {
        this.entityData.set(TRAVELLING, pIsTravelling);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(HOME_POS, BlockPos.ZERO);
        //pBuilder.define(HAS_EGG, false);
        pBuilder.define(TRAVEL_POS, BlockPos.ZERO);
        pBuilder.define(GOING_HOME, false);
        pBuilder.define(TRAVELLING, false);
        //pBuilder.define(LAYING_EGG, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("HomePosX", this.getHomePos().getX());
        pCompound.putInt("HomePosY", this.getHomePos().getY());
        pCompound.putInt("HomePosZ", this.getHomePos().getZ());
        //pCompound.putBoolean("HasEgg", this.hasEgg());
        pCompound.putInt("TravelPosX", this.getTravelPos().getX());
        pCompound.putInt("TravelPosY", this.getTravelPos().getY());
        pCompound.putInt("TravelPosZ", this.getTravelPos().getZ());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        int i = pCompound.getInt("HomePosX");
        int j = pCompound.getInt("HomePosY");
        int k = pCompound.getInt("HomePosZ");
        this.setHomePos(new BlockPos(i, j, k));
        super.readAdditionalSaveData(pCompound);
        //this.setHasEgg(pCompound.getBoolean("HasEgg"));
        int l = pCompound.getInt("TravelPosX");
        int i1 = pCompound.getInt("TravelPosY");
        int j1 = pCompound.getInt("TravelPosZ");
        this.setTravelPos(new BlockPos(l, i1, j1));
    }

    public static boolean checkTurtleSpawnRules(
            EntityType<? extends BoPTurtle> pTurtle, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom
    ) {
        return pPos.getY() < pLevel.getSeaLevel() + 4 && TurtleEggBlock.onSand(pLevel, pPos) && isBrightEnoughToSpawn(pLevel, pPos);
    }

    public static boolean checkGreenTurtleSpawnRules(
            EntityType<? extends BoPTurtle> pTurtle, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom
    ) {
        return pPos.getY() < pLevel.getSeaLevel() + 4 /*&& TurtleEggBlock.onSand(pLevel, pPos) && isBrightEnoughToSpawn(pLevel, pPos)*/;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BoPTurtle.TurtlePanicGoal(this, 1.2));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        //this.goalSelector.addGoal(1, new BoPTurtle.TurtleLayEggGoal(this, 1.0));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.1, p_335260_ -> p_335260_.is(ItemTags.TURTLE_FOOD), false));
        this.goalSelector.addGoal(3, new BoPTurtle.TurtleGoToWaterGoal(this, 1.0));
        this.goalSelector.addGoal(4, new BoPTurtle.TurtleGoHomeGoal(this, 1.0));
        this.goalSelector.addGoal(7, new BoPTurtle.TurtleTravelGoal(this, 1.0));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(9, new BoPTurtle.TurtleRandomStrollGoal(this, 1.0, 100));
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public int getAmbientSoundInterval() {
        return 200;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return !this.isInWater() && this.onGround() && !this.isBaby() ? SoundEvents.TURTLE_AMBIENT_LAND : super.getAmbientSound();
    }

    @Override
    protected void playSwimSound(float pVolume) {
        super.playSwimSound(pVolume * 1.5F);
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.TURTLE_SWIM;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return this.isBaby() ? SoundEvents.TURTLE_HURT_BABY : SoundEvents.TURTLE_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return this.isBaby() ? SoundEvents.TURTLE_DEATH_BABY : SoundEvents.TURTLE_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        SoundEvent soundevent = this.isBaby() ? SoundEvents.TURTLE_SHAMBLE_BABY : SoundEvents.TURTLE_SHAMBLE;
        this.playSound(soundevent, 0.15F, 1.0F);
    }

    @Override
    public boolean canFallInLove() {
        return super.canFallInLove()/* && !this.hasEgg()*/;
    }

    @Override
    protected float nextStep() {
        return this.moveDist + 0.15F;
    }

    @Override
    public float getAgeScale() {
        return this.isBaby() ? 0.3F : 1.0F;
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new BoPTurtle.TurtlePathNavigation(this, pLevel);
    }


    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on the animal type)
     */
    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(ItemTags.TURTLE_FOOD);
    }

    @Override
    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        if (!this.isGoingHome() && pLevel.getFluidState(pPos).is(FluidTags.WATER)) {
            return 10.0F;
        } else {
            return TurtleEggBlock.onSand(pLevel, pPos) ? 10.0F : pLevel.getPathfindingCostFromLightLevels(pPos);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
/*        if (this.isAlive() && this.isLayingEgg() && this.layEggCounter >= 1 && this.layEggCounter % 5 == 0) {
            BlockPos blockpos = this.blockPosition();
            if (TurtleEggBlock.onSand(this.level(), blockpos)) {
                this.level().levelEvent(2001, blockpos, Block.getId(this.level().getBlockState(blockpos.below())));
                this.gameEvent(GameEvent.ENTITY_ACTION);
            }
        }*/
    }

    @Override
    protected void ageBoundaryReached() {
        super.ageBoundaryReached();
        if (!this.isBaby() && this.level().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            this.spawnAtLocation(Items.TURTLE_SCUTE, 1);
        }
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isControlledByLocalInstance() && this.isInWater()) {
            this.moveRelative(0.1F, pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
            if (this.getTarget() == null && (!this.isGoingHome() || !this.getHomePos().closerToCenterThan(this.position(), 20.0))) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(pTravelVector);
        }
    }

    @Override
    public boolean canBeLeashed() {
        return false;
    }

    @Override
    public void thunderHit(ServerLevel pLevel, LightningBolt pLightning) {
        this.hurt(this.damageSources().lightningBolt(), Float.MAX_VALUE);
    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose pPose) {
        return this.isBaby() ? BABY_DIMENSIONS : super.getDefaultDimensions(pPose);
    }

    static class TurtleBreedGoal extends BreedGoal {
        private final BoPTurtle turtle;

        TurtleBreedGoal(BoPTurtle pTurtle, double pSpeedModifier) {
            super(pTurtle, pSpeedModifier);
            this.turtle = pTurtle;
        }

        @Override
        public boolean canUse() {
            return super.canUse() /*&& !this.turtle.hasEgg()*/;
        }

        @Override
        protected void breed() {
            ServerPlayer serverplayer = this.animal.getLoveCause();
            if (serverplayer == null && this.partner.getLoveCause() != null) {
                serverplayer = this.partner.getLoveCause();
            }

            if (serverplayer != null) {
                serverplayer.awardStat(Stats.ANIMALS_BRED);
                CriteriaTriggers.BRED_ANIMALS.trigger(serverplayer, this.animal, this.partner, null);
            }

            //this.turtle.setHasEgg(true);
            this.animal.setAge(6000);
            this.partner.setAge(6000);
            this.animal.resetLove();
            this.partner.resetLove();
            RandomSource randomsource = this.animal.getRandom();
            if (this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                this.level
                        .addFreshEntity(new ExperienceOrb(this.level, this.animal.getX(), this.animal.getY(), this.animal.getZ(), randomsource.nextInt(7) + 1));
            }
        }
    }

    static class TurtleGoHomeGoal extends Goal {
        private final BoPTurtle turtle;
        private final double speedModifier;
        private boolean stuck;
        private int closeToHomeTryTicks;
        private static final int GIVE_UP_TICKS = 600;

        TurtleGoHomeGoal(BoPTurtle pTurtle, double pSpeedModifier) {
            this.turtle = pTurtle;
            this.speedModifier = pSpeedModifier;
        }

        @Override
        public boolean canUse() {
            if (this.turtle.isBaby()) {
                return false;
            } /*else if (this.turtle.hasEgg()) {
                return true;
            }*/ else {
                return this.turtle.getRandom().nextInt(reducedTickDelay(700)) != 0
                        ? false
                        : !this.turtle.getHomePos().closerToCenterThan(this.turtle.position(), 64.0);
            }
        }

        @Override
        public void start() {
            this.turtle.setGoingHome(true);
            this.stuck = false;
            this.closeToHomeTryTicks = 0;
        }

        @Override
        public void stop() {
            this.turtle.setGoingHome(false);
        }

        @Override
        public boolean canContinueToUse() {
            return !this.turtle.getHomePos().closerToCenterThan(this.turtle.position(), 7.0)
                    && !this.stuck
                    && this.closeToHomeTryTicks <= this.adjustedTickDelay(600);
        }

        @Override
        public void tick() {
            BlockPos blockpos = this.turtle.getHomePos();
            boolean flag = blockpos.closerToCenterThan(this.turtle.position(), 16.0);
            if (flag) {
                this.closeToHomeTryTicks++;
            }

            if (this.turtle.getNavigation().isDone()) {
                Vec3 vec3 = Vec3.atBottomCenterOf(blockpos);
                Vec3 vec31 = DefaultRandomPos.getPosTowards(this.turtle, 16, 3, vec3, (float) (Math.PI / 10));
                if (vec31 == null) {
                    vec31 = DefaultRandomPos.getPosTowards(this.turtle, 8, 7, vec3, (float) (Math.PI / 2));
                }

                if (vec31 != null && !flag && !this.turtle.level().getBlockState(BlockPos.containing(vec31)).is(Blocks.WATER)) {
                    vec31 = DefaultRandomPos.getPosTowards(this.turtle, 16, 5, vec3, (float) (Math.PI / 2));
                }

                if (vec31 == null) {
                    this.stuck = true;
                    return;
                }

                this.turtle.getNavigation().moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
            }
        }
    }

    static class TurtleGoToWaterGoal extends MoveToBlockGoal {
        private static final int GIVE_UP_TICKS = 1200;
        private final BoPTurtle turtle;

        TurtleGoToWaterGoal(BoPTurtle pTurtle, double pSpeedModifier) {
            super(pTurtle, pTurtle.isBaby() ? 2.0 : pSpeedModifier, 24);
            this.turtle = pTurtle;
            this.verticalSearchStart = -1;
        }

        @Override
        public boolean canContinueToUse() {
            return !this.turtle.isInWater() && this.tryTicks <= 1200 && this.isValidTarget(this.turtle.level(), this.blockPos);
        }

        @Override
        public boolean canUse() {
            if (this.turtle.isBaby() && !this.turtle.isInWater()) {
                return super.canUse();
            } else {
                return !this.turtle.isGoingHome() && !this.turtle.isInWater() /*&& !this.turtle.hasEgg() */? super.canUse() : false;
            }
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
    }

    /*static class TurtleLayEggGoal extends MoveToBlockGoal {
        private final BoPTurtle turtle;

        TurtleLayEggGoal(BoPTurtle pTurtle, double pSpeedModifier) {
            super(pTurtle, pSpeedModifier, 16);
            this.turtle = pTurtle;
        }

        @Override
        public boolean canUse() {
            return this.turtle.hasEgg() && this.turtle.getHomePos().closerToCenterThan(this.turtle.position(), 9.0) ? super.canUse() : false;
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && this.turtle.hasEgg() && this.turtle.getHomePos().closerToCenterThan(this.turtle.position(), 9.0);
        }

        @Override
        public void tick() {
            super.tick();
            BlockPos blockpos = this.turtle.blockPosition();
            if (!this.turtle.isInWater() && this.isReachedTarget()) {
                if (this.turtle.layEggCounter < 1) {
                    this.turtle.setLayingEgg(true);
                } else if (this.turtle.layEggCounter > this.adjustedTickDelay(200)) {
                    Level level = this.turtle.level();
                    level.playSound(null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + level.random.nextFloat() * 0.2F);
                    BlockPos blockpos1 = this.blockPos.above();
                    BlockState blockstate = Blocks.TURTLE_EGG
                            .defaultBlockState()
                            .setValue(TurtleEggBlock.EGGS, Integer.valueOf(this.turtle.random.nextInt(4) + 1));
                    level.setBlock(blockpos1, blockstate, 3);
                    level.gameEvent(GameEvent.BLOCK_PLACE, blockpos1, GameEvent.Context.of(this.turtle, blockstate));
                    this.turtle.setHasEgg(false);
                    this.turtle.setLayingEgg(false);
                    this.turtle.setInLoveTime(600);
                }

                if (this.turtle.isLayingEgg()) {
                    this.turtle.layEggCounter++;
                }
            }
        }

        *//**
         * Return {@code true} to set given position as destination
         *//*
        @Override
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return !pLevel.isEmptyBlock(pPos.above()) ? false : TurtleEggBlock.isSand(pLevel, pPos);
        }
    }*/

    static class TurtleMoveControl extends MoveControl {
        private final BoPTurtle turtle;

        TurtleMoveControl(BoPTurtle pTurtle) {
            super(pTurtle);
            this.turtle = pTurtle;
        }

        private void updateSpeed() {
            if (this.turtle.isInWater()) {
                this.turtle.setDeltaMovement(this.turtle.getDeltaMovement().add(0.0, 0.005, 0.0));
                if (!this.turtle.getHomePos().closerToCenterThan(this.turtle.position(), 16.0)) {
                    this.turtle.setSpeed(Math.max(this.turtle.getSpeed() / 2.0F, 0.08F));
                }

                if (this.turtle.isBaby()) {
                    this.turtle.setSpeed(Math.max(this.turtle.getSpeed() / 3.0F, 0.06F));
                }
            } else if (this.turtle.onGround()) {
                this.turtle.setSpeed(Math.max(this.turtle.getSpeed() / 2.0F, 0.06F));
            }
        }

        @Override
        public void tick() {
            this.updateSpeed();
            if (this.operation == MoveControl.Operation.MOVE_TO && !this.turtle.getNavigation().isDone()) {
                double d0 = this.wantedX - this.turtle.getX();
                double d1 = this.wantedY - this.turtle.getY();
                double d2 = this.wantedZ - this.turtle.getZ();
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                if (d3 < 1.0E-5F) {
                    this.mob.setSpeed(0.0F);
                } else {
                    d1 /= d3;
                    float f = (float)(Mth.atan2(d2, d0) * 180.0F / (float)Math.PI) - 90.0F;
                    this.turtle.setYRot(this.rotlerp(this.turtle.getYRot(), f, 90.0F));
                    this.turtle.yBodyRot = this.turtle.getYRot();
                    float f1 = (float)(this.speedModifier * this.turtle.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    this.turtle.setSpeed(Mth.lerp(0.125F, this.turtle.getSpeed(), f1));
                    this.turtle.setDeltaMovement(this.turtle.getDeltaMovement().add(0.0, (double)this.turtle.getSpeed() * d1 * 0.1, 0.0));
                }
            } else {
                this.turtle.setSpeed(0.0F);
            }
        }
    }

    static class TurtlePanicGoal extends PanicGoal {
        TurtlePanicGoal(BoPTurtle pTurtle, double pSpeedModifier) {
            super(pTurtle, pSpeedModifier);
        }

        @Override
        public boolean canUse() {
            if (!this.shouldPanic()) {
                return false;
            } else {
                BlockPos blockpos = this.lookForWater(this.mob.level(), this.mob, 7);
                if (blockpos != null) {
                    this.posX = (double)blockpos.getX();
                    this.posY = (double)blockpos.getY();
                    this.posZ = (double)blockpos.getZ();
                    return true;
                } else {
                    return this.findRandomPosition();
                }
            }
        }
    }

    static class TurtlePathNavigation extends AmphibiousPathNavigation {
        TurtlePathNavigation(BoPTurtle pTurtle, Level pLevel) {
            super(pTurtle, pLevel);
        }

        @Override
        public boolean isStableDestination(BlockPos pPos) {
            if (this.mob instanceof BoPTurtle turtle && turtle.isTravelling()) {
                return this.level.getBlockState(pPos).is(Blocks.WATER);
            }

            return !this.level.getBlockState(pPos.below()).isAir();
        }
    }

    static class TurtleRandomStrollGoal extends RandomStrollGoal {
        private final BoPTurtle turtle;

        TurtleRandomStrollGoal(BoPTurtle pTurtle, double pSpeedModifier, int pInterval) {
            super(pTurtle, pSpeedModifier, pInterval);
            this.turtle = pTurtle;
        }

        @Override
        public boolean canUse() {
            return !this.mob.isInWater() && !this.turtle.isGoingHome() /*&& !this.turtle.hasEgg()*/ ? super.canUse() : false;
        }
    }

    static class TurtleTravelGoal extends Goal {
        private final BoPTurtle turtle;
        private final double speedModifier;
        private boolean stuck;

        TurtleTravelGoal(BoPTurtle pTurtle, double pSpeedModifier) {
            this.turtle = pTurtle;
            this.speedModifier = pSpeedModifier;
        }

        @Override
        public boolean canUse() {
            return !this.turtle.isGoingHome() /*&& !this.turtle.hasEgg()*/ && this.turtle.isInWater();
        }

        @Override
        public void start() {
            int i = 512;
            int j = 4;
            RandomSource randomsource = this.turtle.random;
            int k = randomsource.nextInt(1025) - 512;
            int l = randomsource.nextInt(9) - 4;
            int i1 = randomsource.nextInt(1025) - 512;
            if ((double)l + this.turtle.getY() > (double)(this.turtle.level().getSeaLevel() - 1)) {
                l = 0;
            }

            BlockPos blockpos = BlockPos.containing((double)k + this.turtle.getX(), (double)l + this.turtle.getY(), (double)i1 + this.turtle.getZ());
            this.turtle.setTravelPos(blockpos);
            this.turtle.setTravelling(true);
            this.stuck = false;
        }

        @Override
        public void tick() {
            if (this.turtle.getNavigation().isDone()) {
                Vec3 vec3 = Vec3.atBottomCenterOf(this.turtle.getTravelPos());
                Vec3 vec31 = DefaultRandomPos.getPosTowards(this.turtle, 16, 3, vec3, (float) (Math.PI / 10));
                if (vec31 == null) {
                    vec31 = DefaultRandomPos.getPosTowards(this.turtle, 8, 7, vec3, (float) (Math.PI / 2));
                }

                if (vec31 != null) {
                    int i = Mth.floor(vec31.x);
                    int j = Mth.floor(vec31.z);
                    int k = 34;
                    if (!this.turtle.level().hasChunksAt(i - 34, j - 34, i + 34, j + 34)) {
                        vec31 = null;
                    }
                }

                if (vec31 == null) {
                    this.stuck = true;
                    return;
                }

                this.turtle.getNavigation().moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !this.turtle.getNavigation().isDone() && !this.stuck && !this.turtle.isGoingHome() && !this.turtle.isInLove() /*&& !this.turtle.hasEgg()*/;
        }

        @Override
        public void stop() {
            this.turtle.setTravelling(false);
            super.stop();
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.STEP_HEIGHT, 1.0);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
        this.setHealth(this.getMaxHealth());
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(maxSpeed);
        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }

    public RawAnimation getSwimAnimation(){
        return RawAnimation.begin().thenLoop("swim");
    }

    public RawAnimation getLandAnimation(){
        return RawAnimation.begin().thenLoop("land");
    }

    public RawAnimation getIdleAnimation(){
        return RawAnimation.begin().thenLoop("idle");
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Turtle", 5, this::moveAnimController));
    }

    protected <E extends BoPTurtle> PlayState moveAnimController(final AnimationState<E> event) {
        if (this.isInWaterOrBubble() && event.isMoving()){
            event.setAndContinue(getSwimAnimation());
        } else if(event.isMoving()) {
            event.setAndContinue(getLandAnimation());
        } else {
            event.setAndContinue(getIdleAnimation());
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return this.entityType.create(pLevel);
    }
}
