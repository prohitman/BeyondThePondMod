package com.prohitman.beyondthepond.init;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.entities.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.IronGolem;
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
                            90,
                            false,
                            false,
                            true,
                            3,
                            false,
                            false,
                            false
                            )),
                    MobCategory.CREATURE).sized(0.45f, 0.3f).build("rainbow_trout"));

    public static final DeferredHolder<EntityType<?>, EntityType<BoPFish>> COOKIE_CUTTER_SHARK = ENTITIES.register("cookie_cutter_shark",
            () -> EntityType.Builder.<BoPFish>of(((pEntityType, pLevel) ->
                            new BoPFish(pEntityType, pLevel,
                                    5,
                                    1,
                                    2,
                                    90,
                                    false,
                                    true,
                                    true,
                                    4,
                                    false,
                                    false,
                                    true
                            )),
                    MobCategory.CREATURE).sized(0.35f, 0.15f).build("cookie_cutter_shark"));
    public static final DeferredHolder<EntityType<?>, EntityType<BoPFish>> SPOTTED_EAGLE_STINGRAY = ENTITIES.register("spotted_eagle_stingray",
            () -> EntityType.Builder.<BoPFish>of(((pEntityType, pLevel) ->
                            new BoPFish(pEntityType, pLevel,
                                    12,
                                    1,
                                    0,
                                    12,
                                    false,
                                    false,
                                    false,
                                    5,
                                    true,
                                    false,
                                    false
                            )),
                    MobCategory.CREATURE).sized(0.875f, 0.3f).build("spotted_eagle_stingray"));
    public static final DeferredHolder<EntityType<?>, EntityType<BoPFish>> CHANNEL_CATFISH = ENTITIES.register("channel_catfish",
            () -> EntityType.Builder.<BoPFish>of(((pEntityType, pLevel) ->
                            new BoPFish(pEntityType, pLevel,
                                    3,
                                    1,
                                    0,
                                    85,
                                    false,
                                    false,
                                    true,
                                    3,
                                    false,
                                    false,
                                    false
                            )),
                    MobCategory.CREATURE).sized(0.35f, 0.225f).build("channel_catfish"));
    public static final DeferredHolder<EntityType<?>, EntityType<BoPFish>> CUTTLEFISH = ENTITIES.register("cuttlefish",
            () -> EntityType.Builder.<BoPFish>of(((pEntityType, pLevel) ->
                            new BoPFish(pEntityType, pLevel,
                                    8,
                                    1,
                                    0,
                                    75,
                                    false,
                                    false,
                                    false,
                                    3,
                                    false,
                                    false,
                                    false
                            )),
                    MobCategory.CREATURE).sized(0.375f, 0.375f).build("cuttlefish"));
    public static final DeferredHolder<EntityType<?>, EntityType<BoPFish>> LARGEMOUTH_BASS = ENTITIES.register("largemouth_bass",
            () -> EntityType.Builder.<BoPFish>of(((pEntityType, pLevel) ->
                            new BoPFish(pEntityType, pLevel,
                                    3,
                                    1,
                                    0,
                                    90,
                                    false,
                                    false,
                                    true,
                                    3,
                                    false,
                                    false,
                                    true
                            )),
                    MobCategory.CREATURE).sized(0.45f, 0.275f).build("largemouth_bass"));
    public static final DeferredHolder<EntityType<?>, EntityType<BoPFish>> LEMON_SHARK = ENTITIES.register("lemon_shark",
            () -> EntityType.Builder.<BoPFish>of(((pEntityType, pLevel) ->
                            new BoPFish(pEntityType, pLevel,
                                    20,
                                    1.05,
                                    2.75,
                                    10,
                                    false,
                                    true,
                                    false,
                                    5,
                                    false,
                                    true
                            )),
                    MobCategory.CREATURE).sized(1.15f, 0.6f).build("lemon_shark"));
    public static final DeferredHolder<EntityType<?>, EntityType<BoPFish>> LONGNOSE_GAR = ENTITIES.register("longnose_gar",
            () -> EntityType.Builder.<BoPFish>of(((pEntityType, pLevel) ->
                            new BoPFish(pEntityType, pLevel,
                                    3,
                                    1,
                                    0,
                                    65,
                                    false,
                                    false,
                                    true,
                                    3,
                                    false,
                                    false,
                                    false
                            )),
                    MobCategory.CREATURE).sized(0.6f, 0.225f).build("longnose_gar"));
    public static final DeferredHolder<EntityType<?>, EntityType<BoPFish>> NURSE_SHARK = ENTITIES.register("nurse_shark",
            () -> EntityType.Builder.<BoPFish>of(((pEntityType, pLevel) ->
                            new BoPFish(pEntityType, pLevel,
                                    15,
                                    1.1,
                                    2,
                                    10,
                                    false,
                                    true,
                                    false,
                                    5,
                                    false,
                                    false,
                                    false
                            )),
                    MobCategory.CREATURE).sized(1, 0.5f).build("nurse_shark"));
    public static final DeferredHolder<EntityType<?>, EntityType<BoPFish>> SAILFISH = ENTITIES.register("sailfish",
            () -> EntityType.Builder.<BoPFish>of(((pEntityType, pLevel) ->
                            new BoPFish(pEntityType, pLevel,
                                    12,
                                    1.1,
                                    2,
                                    3,
                                    false,
                                    true,
                                    false,
                                    6,
                                    true,
                                    true,
                                    0.7f,
                                    1.25f
                            )),
                    MobCategory.CREATURE).sized(1.5f, 0.75f).build("sailfish"));
    public static final DeferredHolder<EntityType<?>, EntityType<BoPFish>> SUNFISH = ENTITIES.register("sunfish",
            () -> EntityType.Builder.<BoPFish>of(((pEntityType, pLevel) ->
                            new BoPFish(pEntityType, pLevel,
                                    18,
                                    0.7,
                                    0,
                                    1,
                                    false,
                                    false,
                                    false,
                                    5,
                                    true,
                                    true,
                                    0.25f,
                                    1.75f
                            )),
                    MobCategory.CREATURE).sized(1.575f, 1.8f).build("sunfish"));
    public static final DeferredHolder<EntityType<?>, EntityType<BoPFish>> GIANT_TIGER_PRAWN = ENTITIES.register("giant_tiger_prawn",
            () -> EntityType.Builder.<BoPFish>of(((pEntityType, pLevel) ->
                            new BoPFish(pEntityType, pLevel,
                                    3,
                                    1.2,
                                    0,
                                    90,
                                    false,
                                    false,
                                    false,
                                    3,
                                    false,
                                    false,
                                    true
                            )),
                    MobCategory.CREATURE).sized(0.35f, 0.35f).build("giant_tiger_prawn"));
    public static final DeferredHolder<EntityType<?>, EntityType<BoPDolphin>> SPINNER_DOLPHIN = ENTITIES.register("spinner_dolphin",
            () -> EntityType.Builder.<BoPDolphin>of(((pEntityType, pLevel) ->
                            new BoPDolphin(pEntityType, pLevel,
                                    20,
                                    1.2,
                                    4800,
                                    2400,
                                    false,
                                    true,
                                    85,
                                    10,
                                    false,
                                    0.01f
                            )),
                    MobCategory.CREATURE).sized(0.75f, 0.6f).eyeHeight(0.3f).build("spinner_dolphin"));
//New Path navigator needed for stuck detection
    public static final DeferredHolder<EntityType<?>, EntityType<BoPOrca>> ORCA = ENTITIES.register("orca",
            () -> EntityType.Builder.of((BoPOrca::new),
                    MobCategory.CREATURE).sized(3.3f, 2.5f).eyeHeight(1.5f).build("orca"));

    //Fix Later
    public static final DeferredHolder<EntityType<?>, EntityType<BoPDolphin>> HUMPBACK_WHALE = ENTITIES.register("humpback_whale",
            () -> EntityType.Builder.<BoPDolphin>of(((pEntityType, pLevel) ->
                            new BoPDolphin(pEntityType, pLevel,
                                    200,
                                    0.65f,
                                    10000,
                                    8000,
                                    false,
                                    false,
                                    1,
                                    1,
                                    true,
                                    0.008f
                            )),
                    MobCategory.CREATURE).sized(8f, 5f).eyeHeight(4f).build("humpback_whale"));

    public static final DeferredHolder<EntityType<?>, EntityType<BoPDolphin>> MANATEE = ENTITIES.register("manatee",
            () -> EntityType.Builder.<BoPDolphin>of(((pEntityType, pLevel) ->
                            new BoPDolphin(pEntityType, pLevel,
                                    40,
                                    0.3f,
                                    4000,
                                    5000,
                                    true,
                                    true,
                                    5,
                                    5,
                                    false,
                                    0.08f
                            )),
                    MobCategory.CREATURE).sized(1.5f, 1f).eyeHeight(0.5f).build("manatee"));

    public static final DeferredHolder<EntityType<?>, EntityType<BoPCrab>> GIANT_ISOPOD = ENTITIES.register("giant_isopod",
            () -> EntityType.Builder.<BoPCrab>of(((pEntityType, pLevel) ->
                            new BoPCrab(pEntityType, pLevel,
                                    6,
                                    0.2,
                                    0,
                                    25,
                                    2400,
                                    false,
                                    true,
                                    true
                            )),
                    MobCategory.CREATURE).sized(0.5f, 0.5f).build("giant_isopod"));

    public static final DeferredHolder<EntityType<?>, EntityType<BoPCrab>> COCONUT_CRAB = ENTITIES.register("coconut_crab",
            () -> EntityType.Builder.<BoPCrab>of(((pEntityType, pLevel) ->
                            new BoPCrab(pEntityType, pLevel,
                                    20,
                                    0.15,
                                    6,
                                    25,
                                    2400,
                                    true,
                                    false,
                                    false
                            )),
                    MobCategory.CREATURE).sized(0.5f, 0.5f).build("coconut_crab"));

    public static final DeferredHolder<EntityType<?>, EntityType<BoPTurtle>> GREEN_SEA_TURTLE = ENTITIES.register("green_sea_turtle",
            () -> EntityType.Builder.<BoPTurtle>of(((pEntityType, pLevel) ->
                            new BoPTurtle(pEntityType, pLevel,
                                    15,
                                    0.25
                            )),
                    MobCategory.CREATURE).sized(1f, 0.75f).build("green_sea_turtle"));
    /**
     * Add Scaling, then the rest of BoPDolphins, then Special Mobs,/ then the Rest of Mobs, then Tweak each Mob, TrenchMonster/, then items/buckets/drops, then recipes,/ then Marianas Trench biome, then Mob Spawns/
     Need configurable eye heights for all mobs
     BoPFish (fightsBack?, maxHeadRotation?, isPosinous?, dealsDamage?, canFlop?, Bucketable?):
        -Channel Catfish -B
        -CuttleFish
        -Cookie Cutter Shark
        -LargemouthBass -B
        -Lemon Shark
        -Longnose Gar -B
        -Nurse Shark
        -Rainbow Trout -B
        -SailFish ??
        -StingRay
        -Sunfish??
        -BlueRibbonEel --> Needs Animation
     BoPDolphin (maxHeadRotation?¿, maxHealth?, maxSpeed?, hasIdle?, canBeLeashed?, maxAirSupply?):
     Add Beached behaviour, Replicate maxRotation Behaviour, investiaate large bounding boxes bugs (HumpBack Whale)
        -Orca
        -HumpBackWhale --> Needs Conversion (also displacement of body)
        -Manatee
        -Spinner Dolphin --> Needs Conversion
     BoPCrab (maxHeadRotation?, fightsBack?, prefersWater?, driesOutOfWater?):
        -CoconutCrab --> Needs Conversion
        -European Lobster -->  Needs Conversion
        -Giant Isopod --> Needs Conversion
        -Japanese Spider Crab --> Needs Conversion
        -Sally light foot Crab --> Needs Conversion
        -Tasmanian Crab --> Needs Conversion
     BoPTurtle:
        -Green Sea Turtle
     BoPStoneFly
     BoPNymph
     BoPPrawn?? --> Needs conversion, Missing Flopped or Beached
     BoPTrenchMonster --> Needs Conversion/Texture Update
     */
}
