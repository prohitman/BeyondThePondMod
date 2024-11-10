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
    }
}