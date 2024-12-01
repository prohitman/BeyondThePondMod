package com.prohitman.beyondthepond.datagen.server;

import com.prohitman.beyondthepond.BeyondThePond;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTags extends BiomeTagsProvider {
    public static final TagKey<Biome> FRESHWATER_BIOMES = createTag("freshwater_biomes");
    public static final TagKey<Biome> COLD_SALTWATER_BIOMES = createTag("cold_saltwater_biomes");
    public static final TagKey<Biome> WARM_SALTWATER_BIOMES = createTag("warm_saltwater_biomes");
    public static final TagKey<Biome> SALTWATER_BIOMES = createTag("saltwater_biomes");
    public static final TagKey<Biome> COCONUT_CRAB_BIOMES = createTag("coconut_crab_biomes");
    public static final TagKey<Biome> REEFS_BIOMES = createTag("reefs_biomes");

    public ModBiomeTags(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, BeyondThePond.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(FRESHWATER_BIOMES)
                .add(Biomes.RIVER)
                .add(Biomes.FROZEN_RIVER)
                .add(Biomes.SWAMP)
                .add(Biomes.MANGROVE_SWAMP)
                .add(Biomes.LUSH_CAVES);
        tag(COLD_SALTWATER_BIOMES)
                .add(Biomes.OCEAN)
                .add(Biomes.DEEP_OCEAN)
                .add(Biomes.COLD_OCEAN)
                .add(Biomes.DEEP_COLD_OCEAN)
                .add(Biomes.DEEP_FROZEN_OCEAN)
                .add(Biomes.FROZEN_OCEAN);
        tag(WARM_SALTWATER_BIOMES)
                .add(Biomes.DEEP_LUKEWARM_OCEAN)
                .add(Biomes.LUKEWARM_OCEAN)
                .add(Biomes.WARM_OCEAN);
        tag(SALTWATER_BIOMES)
                .addTag(COLD_SALTWATER_BIOMES)
                .addTag(WARM_SALTWATER_BIOMES);
        tag(REEFS_BIOMES)
                .add(Biomes.WARM_OCEAN);
        tag(COCONUT_CRAB_BIOMES)
                .addTag(BiomeTags.IS_BEACH)
                .addTag(BiomeTags.IS_JUNGLE);
    }

    private static TagKey<Biome> createTag(String name) {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(BeyondThePond.MODID, name));
    }
}
