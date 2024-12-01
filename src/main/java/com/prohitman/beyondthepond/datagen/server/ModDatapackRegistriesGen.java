package com.prohitman.beyondthepond.datagen.server;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.init.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackRegistriesGen extends DatapackBuiltinEntriesProvider {
    public ModDatapackRegistriesGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, datapackEntriesBuilder(), Set.of(BeyondThePond.MODID));
    }

    private static RegistrySetBuilder datapackEntriesBuilder(){
        return new RegistrySetBuilder()
                .add(Registries.BIOME, ModBiomes::register);
    }
}
