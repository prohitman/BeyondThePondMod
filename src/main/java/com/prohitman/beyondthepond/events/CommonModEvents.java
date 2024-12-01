package com.prohitman.beyondthepond.events;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.entities.*;
import com.prohitman.beyondthepond.init.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = BeyondThePond.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.RAINBOW_TROUT.get(), BoPFish.createAttributes().build());
        event.put(ModEntities.COOKIE_CUTTER_SHARK.get(), BoPFish.createAttributes().build());
        event.put(ModEntities.SPOTTED_EAGLE_STINGRAY.get(), BoPFish.createAttributes().build());
        event.put(ModEntities.CHANNEL_CATFISH.get(), BoPFish.createAttributes().build());
        event.put(ModEntities.CUTTLEFISH.get(), BoPFish.createAttributes().build());
        event.put(ModEntities.LARGEMOUTH_BASS.get(), BoPFish.createAttributes().build());
        event.put(ModEntities.LEMON_SHARK.get(), BoPFish.createAttributes().build());
        event.put(ModEntities.LONGNOSE_GAR.get(), BoPFish.createAttributes().build());
        event.put(ModEntities.NURSE_SHARK.get(), BoPFish.createAttributes().build());
        event.put(ModEntities.SAILFISH.get(), BoPFish.createAttributes().build());
        event.put(ModEntities.SUNFISH.get(), BoPFish.createAttributes().build());
        event.put(ModEntities.GIANT_TIGER_PRAWN.get(), BoPFish.createAttributes().build());

        event.put(ModEntities.SPINNER_DOLPHIN.get(), BoPDolphin.createAttributes().build());
        event.put(ModEntities.MANATEE.get(), BoPDolphin.createAttributes().build());
        event.put(ModEntities.ORCA.get(), BoPDolphin.createAttributes().build());
        event.put(ModEntities.HUMPBACK_WHALE.get(), BoPDolphin.createAttributes().build());

        event.put(ModEntities.GIANT_ISOPOD.get(), BoPCrab.createAttributes().build());
        event.put(ModEntities.COCONUT_CRAB.get(), BoPCrab.createAttributes().build());
        event.put(ModEntities.EUROPEAN_LOBSTER.get(), BoPCrab.createAttributes().build());
        event.put(ModEntities.JAPANESE_SPIDER_CRAB.get(), BoPCrab.createAttributes().build());
        event.put(ModEntities.TASMANIAN_CRAB.get(), BoPCrab.createAttributes().build());
        event.put(ModEntities.SALLY_LIGHTFOOT_CRAB.get(), BoPCrab.createAttributes().build());

        event.put(ModEntities.GREEN_SEA_TURTLE.get(), BoPTurtle.createAttributes().build());

        event.put(ModEntities.TRENCH_MONSTER.get(), TrenchMonster.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerSpawns(@NotNull RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.RAINBOW_TROUT.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.COOKIE_CUTTER_SHARK.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoPFish::checkDeepWaterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.SPOTTED_EAGLE_STINGRAY.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ((pEntityType, pServerLevel, pSpawnType, pPos, pRandom) ->
                BoPFish.checkWithRaritySpawnRules(pEntityType, pServerLevel, pSpawnType, pPos, pRandom, 4)), RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.CHANNEL_CATFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoPFish::checkCaftishSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.CUTTLEFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.LARGEMOUTH_BASS.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.LEMON_SHARK.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.LONGNOSE_GAR.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.NURSE_SHARK.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.SAILFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.SUNFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ((pEntityType, pServerLevel, pSpawnType, pPos, pRandom) ->
                BoPFish.checkWithRaritySpawnRules(pEntityType, pServerLevel, pSpawnType, pPos, pRandom, 8)), RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.GIANT_TIGER_PRAWN.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.SPINNER_DOLPHIN.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.ORCA.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ((pEntityType, pServerLevel, pSpawnType, pPos, pRandom) ->
                BoPFish.checkWithRaritySpawnRules(pEntityType, pServerLevel, pSpawnType, pPos, pRandom, 6)), RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.HUMPBACK_WHALE.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ((pEntityType, pServerLevel, pSpawnType, pPos, pRandom) ->
                BoPFish.checkWithRaritySpawnRules(pEntityType, pServerLevel, pSpawnType, pPos, pRandom, 10)), RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.MANATEE.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(ModEntities.COCONUT_CRAB.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoPCrab::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.EUROPEAN_LOBSTER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.OCEAN_FLOOR, BoPCrab::checkDeepWaterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.JAPANESE_SPIDER_CRAB.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.OCEAN_FLOOR, ((pEntityType, pServerLevel, pSpawnType, pPos, pRandom) ->
                BoPCrab.checkDeepWaterWithRaritySpawnRules(pEntityType, pServerLevel, pSpawnType, pPos, pRandom, 5)), RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.TASMANIAN_CRAB.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.OCEAN_FLOOR, ((pEntityType, pServerLevel, pSpawnType, pPos, pRandom) ->
                BoPCrab.checkDeepWaterWithRaritySpawnRules(pEntityType, pServerLevel, pSpawnType, pPos, pRandom, 4)), RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.SALLY_LIGHTFOOT_CRAB.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.OCEAN_FLOOR, ((pEntityType, pServerLevel, pSpawnType, pPos, pRandom) ->
                BoPFish.checkWithRaritySpawnRules(pEntityType, pServerLevel, pSpawnType, pPos, pRandom, 3)), RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ModEntities.GIANT_ISOPOD.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.OCEAN_FLOOR, BoPCrab::checkDeepWaterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(ModEntities.GREEN_SEA_TURTLE.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoPTurtle::checkGreenTurtleSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(ModEntities.TRENCH_MONSTER.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.OCEAN_FLOOR, TrenchMonster::checkSurfaceWaterAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
    }

}
