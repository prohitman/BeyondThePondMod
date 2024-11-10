package com.prohitman.beyondthepond.events;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.client.models.BoPDolphinModel;
import com.prohitman.beyondthepond.client.renderers.BoPFishRenderer;
import com.prohitman.beyondthepond.init.ModEntities;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@EventBusSubscriber(modid = BeyondThePond.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {
            EntityRenderers.register(ModEntities.RAINBOW_TROUT.get(),
                    (pContext -> new BoPFishRenderer(pContext, "rainbow_trout")));

            EntityRenderers.register(ModEntities.SPINNER_DOLPHIN.get(),
                    (pContext -> new GeoEntityRenderer<>(pContext,
                            new BoPDolphinModel(ResourceLocation.fromNamespaceAndPath(BeyondThePond.MODID,
                                    "spinner_dolphin")))));

            EntityRenderers.register(ModEntities.GIANT_ISOPOD.get(),
                    (pContext -> new GeoEntityRenderer<>(pContext,
                            new DefaultedEntityGeoModel<>(ResourceLocation.fromNamespaceAndPath(BeyondThePond.MODID,
                                    "giant_isopod")))));

        });
    }
}
