package com.prohitman.beyondthepond.events;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.client.models.BoPDolphinModel;
import com.prohitman.beyondthepond.client.models.BoPTurtleModel;
import com.prohitman.beyondthepond.client.renderers.BoPBaseRenderer;
import com.prohitman.beyondthepond.client.renderers.BoPFishRenderer;
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

            registerRenderers(ModEntities.SPINNER_DOLPHIN.get(), "spinner_dolphin", false, true, 1, 1);
            registerRenderers(ModEntities.MANATEE.get(), "manatee", false, true, 1, 1);
            registerRenderers(ModEntities.ORCA.get(), "orca", false, true, 1.5f, 1.5f);
            registerRenderers(ModEntities.HUMPBACK_WHALE.get(), "humpback_whale", false, true, 2, 3);

            registerRenderers(ModEntities.GIANT_ISOPOD.get(), "giant_isopod", false, false, 0, 0.35f );
            registerRenderers(ModEntities.COCONUT_CRAB.get(), "coconut_crab", false, false, 0, 0.5f );

            registerRenderers(ModEntities.GREEN_SEA_TURTLE.get(), "green_sea_turtle", true, false, 0, 0.75f);
        });
    }

    public static <T extends LivingEntity & GeoEntity> void registerRenderers(EntityType<T> entityType, String name, boolean turnsHead, boolean hasScale, float scale, float shadow){
        EntityRenderers.register(entityType,
                (pContext -> new BoPBaseRenderer<>(pContext, name, turnsHead, hasScale, scale, shadow)));
    }
}
