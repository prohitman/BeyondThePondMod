package com.prohitman.beyondthepond.entities;

import com.prohitman.beyondthepond.init.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BoPTurtle extends Turtle implements GeoEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public double maxHealth;
    public double maxSpeed;
    public EntityType<? extends Turtle> entityType;
    public BoPTurtle(EntityType<? extends Turtle> pEntityType, Level pLevel, double maxHealth, double maxSpeed) {
        super(pEntityType, pLevel);
        this.maxHealth = maxHealth;
        this.maxSpeed = maxSpeed;
        this.entityType = pEntityType;
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
        } else if(event.isMoving() && this.onGround()) {
            System.out.println("Playing land animation");
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
