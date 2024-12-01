package com.prohitman.beyondthepond.events;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.client.models.BoPDolphinModel;
import com.prohitman.beyondthepond.client.models.BoPTurtleModel;
import com.prohitman.beyondthepond.client.renderers.BoPBaseRenderer;
import com.prohitman.beyondthepond.client.renderers.BoPDolphinRenderer;
import com.prohitman.beyondthepond.client.renderers.BoPFishRenderer;
import com.prohitman.beyondthepond.entities.BoPDolphin;
import com.prohitman.beyondthepond.entities.BoPTurtle;
import com.prohitman.beyondthepond.init.ModEntities;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@EventBusSubscriber(modid = BeyondThePond.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {
            registerRenderers(ModEntities.RAINBOW_TROUT.get(), "rainbow_trout", false, false, 0, 0.2f);
            registerRenderers(ModEntities.COOKIE_CUTTER_SHARK.get(), "cookie_cutter_shark", false, false, 0, 0.1f);
            registerRenderers(ModEntities.SPOTTED_EAGLE_STINGRAY.get(), "spotted_eagle_stingray", false, true, 0.5f, 0.2f);
            registerRenderers(ModEntities.CHANNEL_CATFISH.get(), "channel_catfish", false, false, 0, 0.1f);
            registerRenderers(ModEntities.CUTTLEFISH.get(), "cuttlefish", false, false, 0, 0.1f);
            registerRenderers(ModEntities.LARGEMOUTH_BASS.get(), "largemouth_bass", false, false, 0, 0.1f);
            registerRenderers(ModEntities.LEMON_SHARK.get(), "lemon_shark", false, false, 0, 0.35f);
            registerRenderers(ModEntities.LONGNOSE_GAR.get(), "longnose_gar", false, false, 0, 0.1f);
            registerRenderers(ModEntities.NURSE_SHARK.get(), "nurse_shark", false, false, 0, 0.35f);
            registerRenderers(ModEntities.SAILFISH.get(), "sailfish", false, false, 0, 0.45f);
            registerRenderers(ModEntities.SUNFISH.get(), "sunfish", false, false, 0, 0.7f);
            registerRenderers(ModEntities.GIANT_TIGER_PRAWN.get(), "giant_tiger_prawn", false, false, 0, 0.1f);

            registerDolphinRenderers(ModEntities.SPINNER_DOLPHIN.get(), "spinner_dolphin", false, true, 1, 0.5f);
            registerDolphinRenderers(ModEntities.MANATEE.get(), "manatee", false, true, 1, 1);
            registerDolphinRenderers(ModEntities.ORCA.get(), "orca", false, true, 1.5f, 1.5f);
            registerDolphinRenderers(ModEntities.HUMPBACK_WHALE.get(), "humpback_whale", false, true, 2, 3);

            registerRenderers(ModEntities.GIANT_ISOPOD.get(), "giant_isopod", false, false, 0, 0.35f );
            registerRenderers(ModEntities.COCONUT_CRAB.get(), "coconut_crab", false, false, 0, 0.5f );
            registerRenderers(ModEntities.EUROPEAN_LOBSTER.get(), "european_lobster", false, false, 0, 0.2f );
            registerRenderers(ModEntities.JAPANESE_SPIDER_CRAB.get(), "japanese_spider_crab", false, false, 0, 1);
            registerRenderers(ModEntities.TASMANIAN_CRAB.get(), "tasmanian_crab", false, false, 0, 0.25f );
            registerRenderers(ModEntities.SALLY_LIGHTFOOT_CRAB.get(), "sally_lightfoot_crab", false, false, 0, 0.1f );

            registerRenderers(ModEntities.GREEN_SEA_TURTLE.get(), "green_sea_turtle", true, false, 0, 0.75f);

            registerRenderers(ModEntities.TRENCH_MONSTER.get(), "trench_monster", false, false, 0, 0);
        });
    }

    public static <T extends LivingEntity & GeoEntity> void registerRenderers(EntityType<T> entityType, String name, boolean turnsHead, boolean hasScale, float scale, float shadow){
        EntityRenderers.register(entityType,
                (pContext -> new BoPBaseRenderer<>(pContext, name, turnsHead, hasScale, scale, shadow)));
    }

    public static <T extends BoPDolphin> void registerDolphinRenderers(EntityType<T> entityType, String name, boolean turnsHead, boolean hasScale, float scale, float shadow){
        EntityRenderers.register(entityType,
                (pContext -> new BoPDolphinRenderer<>(pContext, name, turnsHead, hasScale, scale, shadow)));
    }
}
