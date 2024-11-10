package com.prohitman.beyondthepond.events;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.entities.BoPCrab;
import com.prohitman.beyondthepond.entities.BoPDolphin;
import com.prohitman.beyondthepond.entities.BoPFish;
import com.prohitman.beyondthepond.entities.BoPTurtle;
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
        event.put(ModEntities.SPINNER_DOLPHIN.get(), BoPDolphin.createAttributes().build());
        event.put(ModEntities.GIANT_ISOPOD.get(), BoPCrab.createAttributes().build());
        event.put(ModEntities.GREEN_SEA_TURTLE.get(), BoPTurtle.createAttributes().build());

    }
}
