package com.prohitman.beyondthepond.datagen.client;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.init.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelGen extends ItemModelProvider {

    public ModItemModelGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BeyondThePond.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Items

        //basicItem(ModItems.FROST_POTTERY_SHERD.get());
        withExistingParent(ModItems.RAINBOW_TROUT_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.SPINNER_DOLPHIN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.GIANT_ISOPOD_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.GREEN_SEA_TURTLE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.ORCA_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.MANATEE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.HUMPBACK_WHALE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.COOKIE_CUTTER_SHARK_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.SPOTTED_EAGLE_STINGRAY_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.COCONUT_CRAB_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.CHANNEL_CATFISH_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.CUTTLEFISH_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.LARGEMOUTH_BASS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.LEMON_SHARK_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.LONGNOSE_GAR_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.NURSE_SHARK_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.SAILFISH_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.SUNFISH_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.GIANT_TIGER_PRAWN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.EUROPEAN_LOBSTER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.JAPANESE_SPIDER_CRAB_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.TASMANIAN_CRAB_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.SALLY_LIGHTFOOT_CRAB_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.TRENCH_MONSTER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        basicItem(ModItems.RAINBOW_TROUT_BUCKET.get());
        basicItem(ModItems.CHANNEL_CATFISH_BUCKET.get());
        basicItem(ModItems.LONGNOSE_GAR_BUCKET.get());
        basicItem(ModItems.LARGEMOUTH_BASS_BUCKET.get());

        basicItem(ModItems.RAW_ISOPOD.get());
        basicItem(ModItems.RAW_COOKIECUTTER.get());
        basicItem(ModItems.RAW_CUTTLEFISH.get());
        basicItem(ModItems.RAW_LARGE_CRAB.get());
        basicItem(ModItems.RAW_LOBSTER.get());
        basicItem(ModItems.RAW_MEDIUM_CRAB.get());
        basicItem(ModItems.RAW_SAILFISH.get());
        basicItem(ModItems.RAW_SHARK.get());
        basicItem(ModItems.RAW_SHRIMP.get());
        basicItem(ModItems.RAW_SMALL_CRAB.get());
        basicItem(ModItems.RAW_SPIDERCRAB.get());
        basicItem(ModItems.RAW_STINGRAY.get());
        basicItem(ModItems.RAW_SUNFISH.get());
        basicItem(ModItems.RAW_TURTLE.get());
        basicItem(ModItems.COOKED_ISOPOD.get());
        basicItem(ModItems.COOKED_COOKIECUTTER.get());
        basicItem(ModItems.COOKED_CUTTLEFISH.get());
        basicItem(ModItems.COOKED_LARGE_CRAB.get());
        basicItem(ModItems.COOKED_LOBSTER.get());
        basicItem(ModItems.COOKED_MEDIUM_CRAB.get());
        basicItem(ModItems.COOKED_SAILFISH.get());
        basicItem(ModItems.COOKED_SHARK.get());
        basicItem(ModItems.COOKED_SHRIMP.get());
        basicItem(ModItems.COOKED_SMALL_CRAB.get());
        basicItem(ModItems.COOKED_SPIDERCRAB.get());
        basicItem(ModItems.COOKED_STINGRAY.get());
        basicItem(ModItems.COOKED_SUNFISH.get());
        basicItem(ModItems.COOKED_TURTLE.get());

    }
}