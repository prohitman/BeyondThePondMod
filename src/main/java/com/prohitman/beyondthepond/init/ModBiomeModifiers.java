package com.prohitman.beyondthepond.init;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.worldgen.AddMobSpawnsBiomeModifier;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, BeyondThePond.MODID);

    public static DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<AddMobSpawnsBiomeModifier>> ADD_MOB_SPAWNS_CODEC = BIOME_MODIFIER_SERIALIZERS.register("add_mob_spawns", () -> MapCodec.unit(AddMobSpawnsBiomeModifier::new));

}
