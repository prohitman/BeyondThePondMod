package com.prohitman.beyondthepond.events;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.entities.*;
import com.prohitman.beyondthepond.init.ModEntities;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
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
}
