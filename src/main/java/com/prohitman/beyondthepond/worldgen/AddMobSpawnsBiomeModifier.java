package com.prohitman.beyondthepond.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.prohitman.beyondthepond.datagen.server.ModBiomeTags;
import com.prohitman.beyondthepond.init.ModBiomeModifiers;
import com.prohitman.beyondthepond.init.ModEntities;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

public class AddMobSpawnsBiomeModifier implements BiomeModifier {
    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase.equals(Phase.ADD)) {
            addMobSpawn(builder, biome,
                    ModBiomeTags.FRESHWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.CHANNEL_CATFISH.get(), 3, 1, 3);

            addMobSpawn(builder, biome,
                    ModBiomeTags.COCONUT_CRAB_BIOMES, MobCategory.AMBIENT,
                    ModEntities.COCONUT_CRAB.get(), 3, 1, 3);

            addMobSpawn(builder, biome,
                    ModBiomeTags.WARM_SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.COOKIE_CUTTER_SHARK.get(), 1, 1, 1);

            addMobSpawn(builder, biome,
                    ModBiomeTags.REEFS_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.CUTTLEFISH.get(), 2, 1, 1);

            addMobSpawn(builder, biome,
                    ModBiomeTags.COLD_SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.GIANT_ISOPOD.get(), 2, 2, 5);

            addMobSpawn(builder, biome,
                    ModBiomeTags.WARM_SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.EUROPEAN_LOBSTER.get(), 3, 1, 4);

            addMobSpawn(builder, biome,
                    ModBiomeTags.WARM_SALTWATER_BIOMES, MobCategory.CREATURE,
                    ModEntities.GREEN_SEA_TURTLE.get(), 1, 1, 1);

            addMobSpawn(builder, biome,
                    ModBiomeTags.COLD_SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.HUMPBACK_WHALE.get(), 1, 1, 1);

            addMobSpawn(builder, biome,
                    ModBiomeTags.COLD_SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.JAPANESE_SPIDER_CRAB.get(), 2, 1, 3);

            addMobSpawn(builder, biome,
                    ModBiomeTags.FRESHWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.LARGEMOUTH_BASS.get(), 2, 1, 3);

            addMobSpawn(builder, biome,
                    ModBiomeTags.WARM_SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.LEMON_SHARK.get(), 1, 1, 1);

            addMobSpawn(builder, biome,
                    ModBiomeTags.FRESHWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.LONGNOSE_GAR.get(), 2, 1, 2);

            addMobSpawn(builder, biome,
                    ModBiomeTags.WARM_SALTWATER_BIOMES, MobCategory.WATER_CREATURE,
                    ModEntities.MANATEE.get(), 1, 1, 2);

            addMobSpawn(builder, biome,
                    ModBiomeTags.WARM_SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.NURSE_SHARK.get(), 1, 1, 1);

            addMobSpawn(builder, biome,
                    ModBiomeTags.COLD_SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.ORCA.get(), 1, 1, 1);

            addMobSpawn(builder, biome,
                    ModBiomeTags.FRESHWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.RAINBOW_TROUT.get(), 3, 2, 4);

            addMobSpawn(builder, biome,
                    BiomeTags.IS_BEACH, MobCategory.WATER_AMBIENT,
                    ModEntities.SALLY_LIGHTFOOT_CRAB.get(), 2, 2, 4);

            addMobSpawn(builder, biome,
                    ModBiomeTags.WARM_SALTWATER_BIOMES, MobCategory.WATER_CREATURE,
                    ModEntities.SPINNER_DOLPHIN.get(), 2, 1, 2);

            addMobSpawn(builder, biome,
                    ModBiomeTags.SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.SPOTTED_EAGLE_STINGRAY.get(), 2, 1, 2);

            addMobSpawn(builder, biome,
                    ModBiomeTags.WARM_SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.SUNFISH.get(), 1, 1, 1);

            addMobSpawn(builder, biome,
                    ModBiomeTags.COLD_SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.TASMANIAN_CRAB.get(), 2, 1, 3);

            addMobSpawn(builder, biome,
                    ModBiomeTags.WARM_SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.GIANT_TIGER_PRAWN.get(), 4, 2, 4);

            addMobSpawn(builder, biome,
                    ModBiomeTags.WARM_SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.SAILFISH.get(), 1, 1, 1);

/*
            addMobSpawn(builder, biome,
                    ModBiomeTags.WARM_SALTWATER_BIOMES, MobCategory.WATER_AMBIENT,
                    ModEntities.BLUE.get(), 4, 2, 4);
*/

        }
    }

    void addMobSpawn(ModifiableBiomeInfo.BiomeInfo.Builder builder, Holder<Biome> biome, TagKey<Biome> tag, MobCategory mobCategory, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize) {
        if (biome.is(tag)) {
            builder.getMobSpawnSettings().addSpawn(mobCategory, new MobSpawnSettings.SpawnerData(entityType, weight, minGroupSize, maxGroupSize));
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return ModBiomeModifiers.ADD_MOB_SPAWNS_CODEC.get();
    }

}
