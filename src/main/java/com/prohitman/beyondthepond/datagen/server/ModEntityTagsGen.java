package com.prohitman.beyondthepond.datagen.server;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.init.ModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagsGen extends EntityTypeTagsProvider {
    public static final TagKey<EntityType<?>> TURTLES = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(BeyondThePond.MODID, "turtles"));
    public static final TagKey<EntityType<?>> DOLPHINS = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(BeyondThePond.MODID, "dolphins"));

    public ModEntityTagsGen(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, BeyondThePond.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(TURTLES)
                .add(ModEntities.GREEN_SEA_TURTLE.get());
        this.tag(DOLPHINS)
                .add(ModEntities.MANATEE.get())
                .add(ModEntities.HUMPBACK_WHALE.get())
                .add(ModEntities.ORCA.get());
        this.tag(EntityTypeTags.AQUATIC)
                .add(ModEntities.TRENCH_MONSTER.get())
                .add(ModEntities.SALLY_LIGHTFOOT_CRAB.get())
                .add(ModEntities.TASMANIAN_CRAB.get())
                .add(ModEntities.JAPANESE_SPIDER_CRAB.get())
                .add(ModEntities.EUROPEAN_LOBSTER.get())
                .add(ModEntities.GIANT_TIGER_PRAWN.get())
                .add(ModEntities.SUNFISH.get())
                .add(ModEntities.SAILFISH.get())
                .add(ModEntities.NURSE_SHARK.get())
                .add(ModEntities.LONGNOSE_GAR.get())
                .add(ModEntities.LEMON_SHARK.get())
                .add(ModEntities.LARGEMOUTH_BASS.get())
                .add(ModEntities.CUTTLEFISH.get())
                .add(ModEntities.CHANNEL_CATFISH.get())
                .add(ModEntities.COCONUT_CRAB.get())
                .add(ModEntities.COOKIE_CUTTER_SHARK.get())
                .add(ModEntities.SPOTTED_EAGLE_STINGRAY.get())
                .add(ModEntities.RAINBOW_TROUT.get())
                .add(ModEntities.GIANT_ISOPOD.get())
                .add(ModEntities.GREEN_SEA_TURTLE.get())
                .add(ModEntities.SPINNER_DOLPHIN.get());
        this.tag(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH)
                .add(ModEntities.TRENCH_MONSTER.get())
                .add(ModEntities.SALLY_LIGHTFOOT_CRAB.get())
                .add(ModEntities.TASMANIAN_CRAB.get())
                .add(ModEntities.JAPANESE_SPIDER_CRAB.get())
                .add(ModEntities.EUROPEAN_LOBSTER.get())
                .add(ModEntities.COCONUT_CRAB.get())
                .add(ModEntities.GIANT_TIGER_PRAWN.get())
                .add(ModEntities.SUNFISH.get())
                .add(ModEntities.SAILFISH.get())
                .add(ModEntities.NURSE_SHARK.get())
                .add(ModEntities.LONGNOSE_GAR.get())
                .add(ModEntities.LEMON_SHARK.get())
                .add(ModEntities.LARGEMOUTH_BASS.get())
                .add(ModEntities.CUTTLEFISH.get())
                .add(ModEntities.CHANNEL_CATFISH.get())
                .add(ModEntities.COCONUT_CRAB.get())
                .add(ModEntities.COOKIE_CUTTER_SHARK.get())
                .add(ModEntities.SPOTTED_EAGLE_STINGRAY.get())
                .add(ModEntities.ORCA.get())
                .add(ModEntities.HUMPBACK_WHALE.get())
                .add(ModEntities.MANATEE.get())
                .add(ModEntities.RAINBOW_TROUT.get())
                .add(ModEntities.GIANT_ISOPOD.get())
                .add(ModEntities.GREEN_SEA_TURTLE.get())
                .add(ModEntities.SPINNER_DOLPHIN.get());
        this.tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER)
                .add(ModEntities.TRENCH_MONSTER.get())
                .add(ModEntities.SALLY_LIGHTFOOT_CRAB.get())
                .add(ModEntities.TASMANIAN_CRAB.get())
                .add(ModEntities.JAPANESE_SPIDER_CRAB.get())
                .add(ModEntities.EUROPEAN_LOBSTER.get())
                .add(ModEntities.GIANT_TIGER_PRAWN.get())
                .add(ModEntities.SUNFISH.get())
                .add(ModEntities.SAILFISH.get())
                .add(ModEntities.NURSE_SHARK.get())
                .add(ModEntities.LONGNOSE_GAR.get())
                .add(ModEntities.LEMON_SHARK.get())
                .add(ModEntities.LARGEMOUTH_BASS.get())
                .add(ModEntities.CUTTLEFISH.get())
                .add(ModEntities.CHANNEL_CATFISH.get())
                .add(ModEntities.SPOTTED_EAGLE_STINGRAY.get())
                .add(ModEntities.COOKIE_CUTTER_SHARK.get())
                .add(ModEntities.ORCA.get())
                .add(ModEntities.HUMPBACK_WHALE.get())
                .add(ModEntities.MANATEE.get())
                .add(ModEntities.GREEN_SEA_TURTLE.get())
                .add(ModEntities.GIANT_ISOPOD.get())
                .add(ModEntities.RAINBOW_TROUT.get());
    }
}
