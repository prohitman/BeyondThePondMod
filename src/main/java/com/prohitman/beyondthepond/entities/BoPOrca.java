package com.prohitman.beyondthepond.entities;

import com.prohitman.beyondthepond.entities.goals.BoPJumpGoal;
import com.prohitman.beyondthepond.entities.goals.OrcaJumpGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.*;

import javax.print.DocFlavor;
import java.util.UUID;
import java.util.function.Predicate;

public class BoPOrca extends BoPDolphin implements NeutralMob {
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(BoPOrca.class, EntityDataSerializers.BOOLEAN);
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
    public BoPOrca(EntityType<? extends BoPDolphin> pEntityType, Level pLevel) {
        super(pEntityType, pLevel,
                50,
                1.35,
                7800,
                3000,
                true,
                false,
                5,
                10,
                true,
                0.2f);
        this.moveControl = new MoveHelperController(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(1, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0, 10){
            @Override
            public boolean canUse() {
                if(BoPOrca .this.isBeached()){
                    return false;
                }
                return super.canUse();
            }
        });
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this){
            @Override
            public boolean canUse() {
                if(BoPOrca .this.isBeached()){
                    return false;
                }
                return super.canUse();
            }
        });
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F){
            @Override
            public boolean canUse() {
                if(BoPOrca.this.isBeached()){
                    return false;
                }
                return super.canUse();
            }
        });
        this.goalSelector.addGoal(5, new OrcaJumpGoal(this, 10));
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.2F, true));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        //this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, Guardian.class, 8.0F, 1.0, 1.0));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers(BoPOrca.class));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    public boolean isAttacking(){
        return this.entityData.get(ATTACKING);
    }
    public void setAttacking(boolean attacking){
        updateRotation(attacking);
        this.entityData.set(ATTACKING, attacking);
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.level().isClientSide){
            if(this.getTarget() != null && !this.isAttacking()){
                this.setAttacking(this.distanceTo(this.getTarget()) <= 4);
            } else if(this.getTarget() == null && this.isAttacking()){
                this.setAttacking(false);
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(ATTACKING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.addPersistentAngerSaveData(pCompound);
        pCompound.putBoolean("attacking", this.isAttacking());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.readPersistentAngerSaveData(this.level(), pCompound);
        this.setAttacking(pCompound.getBoolean("attacking"));

    }

    @Override
    public void startPersistentAngerTimer() {
       this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @Override
    public void setRemainingPersistentAngerTime(int pTime) {
        this.remainingPersistentAngerTime = pTime;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    @Override
    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID pTarget) {
       this.persistentAngerTarget = pTarget;
    }

    @javax.annotation.Nullable
    @Override
    public UUID getPersistentAngerTarget() {
       return this.persistentAngerTarget;
    }

    public RawAnimation getBiteAnimation(){
        return RawAnimation.begin().thenLoop("bite");
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        super.registerControllers(controllers);
        controllers.add(new AnimationController<>(this, "OrcaAttack", 5, this::attackAnimController));
    }

    protected <E extends BoPDolphin> PlayState attackAnimController(AnimationState<E> event) {
        if(this.isAttacking()){
            event.setAndContinue(getBiteAnimation());
            return PlayState.CONTINUE;
        }
        return super.moveAnimController(event);
    }

    static class MoveHelperController extends MoveControl {
        private final BoPOrca dolphin;

        public MoveHelperController(BoPOrca dolphinIn) {
            super(dolphinIn);
            this.dolphin = dolphinIn;
        }

        public void tick() {
            if (this.dolphin.isInWater()) {
                this.dolphin.setDeltaMovement(this.dolphin.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MoveControl.Operation.MOVE_TO && !this.dolphin.getNavigation().isDone()) {
                final double d0 = this.wantedX - this.dolphin.getX();
                final double d1 = this.wantedY - this.dolphin.getY();
                final double d2 = this.wantedZ - this.dolphin.getZ();
                final double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double) 2.5000003E-7F) {
                    this.mob.setZza(0.0F);
                } else {
                    final float f = (float) (Mth.atan2(d2, d0) * (double) Mth.RAD_TO_DEG) - 90.0F;
                    this.dolphin.setYRot(this.rotlerp(this.dolphin.getYRot(), f, 10.0F));
                    this.dolphin.yBodyRot = this.dolphin.getYRot();
                    this.dolphin.yHeadRot = this.dolphin.getYRot();
                    final float f1 = (float) (this.speedModifier * this.dolphin.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.dolphin.isInWater()) {
                        this.dolphin.setSpeed(f1 * 0.02F);
                        float f2 = -((float) (Mth.atan2(d1, Mth.sqrt((float) (d0 * d0 + d2 * d2))) * (double) Mth.RAD_TO_DEG));
                        f2 = Mth.clamp(Mth.wrapDegrees(f2), -85.0F, 85.0F);
                        this.dolphin.setXRot(this.rotlerp(this.dolphin.getXRot(), f2, 5.0F));
                        final float xRotRad = this.dolphin.getXRot() * Mth.DEG_TO_RAD;
                        final float f3 = Mth.cos(xRotRad);
                        final float f4 = Mth.sin(xRotRad);
                        this.dolphin.zza = f3 * f1;
                        this.dolphin.yya = -f4 * f1;
                    } else {
                        this.dolphin.setSpeed(f1 * 0.1F);
                    }

                }
            } else {
                this.dolphin.setSpeed(0.0F);
                this.dolphin.setXxa(0.0F);
                this.dolphin.setYya(0.0F);
                this.dolphin.setZza(0.0F);
            }
        }
    }
}
