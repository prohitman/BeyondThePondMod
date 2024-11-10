package com.prohitman.beyondthepond.init;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.entities.BoPCrab;
import com.prohitman.beyondthepond.entities.BoPDolphin;
import com.prohitman.beyondthepond.entities.BoPFish;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.AbstractFish;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.logging.log4j.core.appender.rolling.action.IfAll;

public class ModEntities {
    public static DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, BeyondThePond.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<BoPFish>> RAINBOW_TROUT = ENTITIES.register("rainbow_trout",
            () -> EntityType.Builder.<BoPFish>of(((pEntityType, pLevel) ->
                    new BoPFish(pEntityType, pLevel,
                            3,
                            1,
                            0,
                            75,
                            false,
                            false,
                            true,
                            3,
                            false,
                            false
                            )),
                    MobCategory.CREATURE).sized(0.5f, 0.5f).build("rainbow_trout"));

    public static final DeferredHolder<EntityType<?>, EntityType<BoPDolphin>> SPINNER_DOLPHIN = ENTITIES.register("spinner_dolphin",
            () -> EntityType.Builder.<BoPDolphin>of(((pEntityType, pLevel) ->
                            new BoPDolphin(pEntityType, pLevel,
                                    20,
                                    1.2,
                                    4800,
                                    2400,
                                    false,
                                    true
                            )),
                    MobCategory.CREATURE).sized(1.2f, 1).build("spinner_dolphin"));

    public static final DeferredHolder<EntityType<?>, EntityType<BoPCrab>> GIANT_ISOPOD = ENTITIES.register("giant_isopod",
            () -> EntityType.Builder.<BoPCrab>of(((pEntityType, pLevel) ->
                            new BoPCrab(pEntityType, pLevel,
                                    6,
                                    0.75,
                                    0,
                                    25,
                                    2400,
                                    false,
                                    true,
                                    true
                            )),
                    MobCategory.CREATURE).sized(0.5f, 0.5f).build("giant_isopod"));
    /**
     BoPFish (fightsBack?, maxHeadRotation?, isPosinous?, dealsDamage?, canFlop?, Bucketable?):
        -Channel Catfish
        -CuttleFish
        -Cookie Cutter Shark
        -LargemouthBass
        -Lemon Shark
        -Longnose Gar
        -Nurse Shark
        -Rainbow Trout
        -SailFish ??
        -StingRay
        -Sunfish??
        -BlueRibbonEel
     BoPDolphin (maxHeadRotation?¿, maxHealth?, maxSpeed?, hasIdle?, canBeLeashed?, maxAirSupply?):
        -Orca
        -HumpBackWhale
        -Manatee
        -Spinner Dolphin
     BoPCrab (maxHeadRotation?, fightsBack?, prefersWater?, driesOutOfWater?):
        -CoconutCrab
        -European Lobster
        -Giant Isopod
        -Japanese Spider Crab
        -Sally light foot Crab
        -Tasmanian Crab
     BoPTurtle:
        -Green Sea Turtle
     BoPStoneFly
     BoPNymph
     BoPPrawn??
     BoPTrenchMonster
     */
}
