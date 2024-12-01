package com.prohitman.beyondthepond.init;

import com.prohitman.beyondthepond.BeyondThePond;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModBiomes {
    public static final ResourceKey<Biome> MARIANAS_TRENCH = ResourceKey.create(
            Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(BeyondThePond.MODID, "marianas_trench")
    );

    public static final int TRENCH_WATER_COLOR = 0x022559;

    public static void register(BootstrapContext<Biome> biomeBootstrapContext) {
        HolderGetter<PlacedFeature> holdergetter = biomeBootstrapContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> worldCarvers = biomeBootstrapContext.lookup(Registries.CONFIGURED_CARVER);
        biomeBootstrapContext.register(
                // The resource key of our configured feature.
                MARIANAS_TRENCH,
                // The actual configured feature.
                createMarianasTrenchBiome(holdergetter, worldCarvers)
        );
    }

    // Biome definition
    public static Biome createMarianasTrenchBiome(
            HolderGetter<PlacedFeature> placedFeatures,
            HolderGetter<ConfiguredWorldCarver<?>> worldCarvers
    ) {
        // Mob spawning
        MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder();
        mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.TROPICAL_FISH, 25, 8, 8));
        mobSpawnSettings.addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SQUID, 15, 1, 4));
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.DROWNED, 10, 1, 3));

        // Biome generation settings
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND_MEDIUM);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND_LARGE);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_EMERALD);

        // Add deep ocean vegetation
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_DEEP);

        // Special effects (visuals)
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(12638463)
                .waterColor(4159204)
                .waterFogColor(329011)
                .skyColor(calculateSkyColor(0.2F))
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .build();

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.5F)
                .downfall(0.8F)
                .specialEffects(effects)
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

    // Calculate sky color based on temperature
    private static int calculateSkyColor(float temperature) {
        return (int) ((1.0F - temperature) * 0.6225F * 65536.0F + 52736.0F);
    }
}
