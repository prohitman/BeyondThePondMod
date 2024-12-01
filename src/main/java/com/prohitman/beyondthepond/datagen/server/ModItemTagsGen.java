package com.prohitman.beyondthepond.datagen.server;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsGen extends ItemTagsProvider {
    public static final TagKey<Item> BOP_RAW_FISH = tag("bop_raw_fish");
    public static final TagKey<Item> BOP_COOKED_FISH = tag("bop_cooked_fish");


    public ModItemTagsGen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, BeyondThePond.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BOP_COOKED_FISH)
                .add(ModItems.COOKED_ISOPOD.get())
                .add(ModItems.COOKED_COOKIECUTTER.get())
                .add(ModItems.COOKED_CUTTLEFISH.get())
                .add(ModItems.COOKED_LARGE_CRAB.get())
                .add(ModItems.COOKED_LOBSTER.get())
                .add(ModItems.COOKED_MEDIUM_CRAB.get())
                .add(ModItems.COOKED_SAILFISH.get())
                .add(ModItems.COOKED_SHARK.get())
                .add(ModItems.COOKED_SHRIMP.get())
                .add(ModItems.COOKED_SMALL_CRAB.get())
                .add(ModItems.COOKED_SPIDERCRAB.get())
                .add(ModItems.COOKED_STINGRAY.get())
                .add(ModItems.COOKED_SUNFISH.get())
                .add(ModItems.COOKED_TURTLE.get());
        tag(BOP_RAW_FISH)
                .add(ModItems.RAW_ISOPOD.get())
                .add(ModItems.RAW_COOKIECUTTER.get())
                .add(ModItems.RAW_CUTTLEFISH.get())
                .add(ModItems.RAW_LARGE_CRAB.get())
                .add(ModItems.RAW_LOBSTER.get())
                .add(ModItems.RAW_MEDIUM_CRAB.get())
                .add(ModItems.RAW_SAILFISH.get())
                .add(ModItems.RAW_SHARK.get())
                .add(ModItems.RAW_SHRIMP.get())
                .add(ModItems.RAW_SMALL_CRAB.get())
                .add(ModItems.RAW_SPIDERCRAB.get())
                .add(ModItems.RAW_STINGRAY.get())
                .add(ModItems.RAW_SUNFISH.get())
                .add(ModItems.RAW_TURTLE.get());
        tag(Tags.Items.FOODS_COOKED_FISH)
            .addTag(BOP_COOKED_FISH);
        tag(Tags.Items.FOODS_RAW_FISH)
                .addTag(BOP_RAW_FISH);
        tag(ItemTags.OCELOT_FOOD)
                .addTag(BOP_RAW_FISH);
        tag(ItemTags.CAT_FOOD)
                .addTag(BOP_RAW_FISH);
        tag(ItemTags.FISHES)
                .addTag(BOP_RAW_FISH)
                .addTag(BOP_COOKED_FISH);
    }

    private static TagKey<Item> tag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }
}
