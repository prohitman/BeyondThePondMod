package com.prohitman.beyondthepond.init;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.entities.BoPCrab;
import com.prohitman.beyondthepond.entities.BoPDolphin;
import com.prohitman.beyondthepond.entities.BoPFish;
import com.prohitman.beyondthepond.entities.BoPTurtle;
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

    public static final DeferredHolder<EntityType<?>, EntityType<BoPDolphin>> ORCA = ENTITIES.register("orca",
            () -> EntityType.Builder.<BoPDolphin>of(((pEntityType, pLevel) ->
                            new BoPDolphin(pEntityType, pLevel,
                                    50,
                                    1.2,
                                    7800,
                                    3000,
                                    true,
                                    false
                            )),
                    MobCategory.CREATURE).sized(2.75f, 2.5f).build("orca"));

    //Fix Later
    public static final DeferredHolder<EntityType<?>, EntityType<BoPDolphin>> HUMPBACK_WHALE = ENTITIES.register("humpback_whale",
            () -> EntityType.Builder.<BoPDolphin>of(((pEntityType, pLevel) ->
                            new BoPDolphin(pEntityType, pLevel,
                                    200,
                                    1,
                                    10000,
                                    8000,
                                    false,
                                    false
                            )),
                    MobCategory.CREATURE).sized(8f, 6f).build("humpback_whale"));

    public static final DeferredHolder<EntityType<?>, EntityType<BoPDolphin>> MANATEE = ENTITIES.register("manatee",
            () -> EntityType.Builder.<BoPDolphin>of(((pEntityType, pLevel) ->
                            new BoPDolphin(pEntityType, pLevel,
                                    40,
                                    0.5f,
                                    4000,
                                    5000,
                                    true,
                                    true
                            )),
                    MobCategory.CREATURE).sized(2.2f, 1.85f).build("manatee"));

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

    public static final DeferredHolder<EntityType<?>, EntityType<BoPTurtle>> GREEN_SEA_TURTLE = ENTITIES.register("green_sea_turtle",
            () -> EntityType.Builder.<BoPTurtle>of(((pEntityType, pLevel) ->
                            new BoPTurtle(pEntityType, pLevel,
                                    15,
                                    0.25
                            )),
                    MobCategory.CREATURE).sized(1f, 0.75f).build("green_sea_turtle"));
    /**
     * Add Scaling, then the rest of BoPDolphins, then Special Mobs,/ then the Rest of Mobs, then TrenchMonster, then items/buckets/drops, then recipes,/ then Marianas Trench biome, then Mob Spawns/
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
