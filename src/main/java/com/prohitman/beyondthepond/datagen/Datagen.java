package com.prohitman.beyondthepond.datagen;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.datagen.client.ModItemModelGen;
import com.prohitman.beyondthepond.datagen.client.ModLangGen;
import com.prohitman.beyondthepond.datagen.server.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = BeyondThePond.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Datagen {

    @SubscribeEvent
    public static void gatherEvent(final GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        CompletableFuture<TagsProvider.TagLookup<Block>> tagLookup = generator.addProvider(event.includeServer(), new ModBlockTagsGen(packOutput, lookupProvider, fileHelper)).contentsGetter();
        generator.addProvider(event.includeServer(), new ModItemTagsGen(packOutput, lookupProvider, tagLookup, fileHelper));
        generator.addProvider(event.includeServer(), new ModEntityTagsGen(packOutput, lookupProvider, fileHelper));
        generator.addProvider(event.includeServer(), new ModRecipesGen(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ModLootTableGen(packOutput, lookupProvider));

        generator.addProvider(event.includeClient(), new ModItemModelGen(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new ModLangGen(packOutput));
    }
}
