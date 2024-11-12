package com.prohitman.beyondthepond.datagen.client;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

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

    }
}