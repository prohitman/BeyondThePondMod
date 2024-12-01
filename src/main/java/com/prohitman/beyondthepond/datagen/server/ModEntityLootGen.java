package com.prohitman.beyondthepond.datagen.server;

import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.init.ModEntities;
import com.prohitman.beyondthepond.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.stream.Stream;

public class ModEntityLootGen extends EntityLootSubProvider {
    public ModEntityLootGen(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {
        genFishLoot(ModEntities.RAINBOW_TROUT.get(), Items.COD, 1);
        genFishLoot(ModEntities.COOKIE_CUTTER_SHARK.get(), ModItems.RAW_COOKIECUTTER.get(), 2);
        genFishLoot(ModEntities.SPOTTED_EAGLE_STINGRAY.get(), ModItems.RAW_STINGRAY.get(), 2);
        genFishLoot(ModEntities.CHANNEL_CATFISH.get(), Items.COD, 1);
        genFishLoot(ModEntities.CUTTLEFISH.get(), ModItems.RAW_CUTTLEFISH.get(), 1);
        genFishLoot(ModEntities.LARGEMOUTH_BASS.get(), Items.COD, 1);
        genFishLoot(ModEntities.LEMON_SHARK.get(), ModItems.RAW_SHARK.get(), 3);
        genFishLoot(ModEntities.LONGNOSE_GAR.get(), Items.COD, 1);
        genFishLoot(ModEntities.NURSE_SHARK.get(), ModItems.RAW_SHARK.get(), 3);
        genFishLoot(ModEntities.SAILFISH.get(), ModItems.RAW_SAILFISH.get(), 3);
        genFishLoot(ModEntities.SUNFISH.get(), ModItems.RAW_SUNFISH.get(), 4);
        genFishLoot(ModEntities.GIANT_TIGER_PRAWN.get(), ModItems.RAW_SHRIMP.get(), 1);
        genFishLoot(ModEntities.GIANT_ISOPOD.get(), ModItems.RAW_ISOPOD.get(), 1);
        genFishLoot(ModEntities.COCONUT_CRAB.get(), ModItems.RAW_LARGE_CRAB.get(), 3);
        genFishLoot(ModEntities.EUROPEAN_LOBSTER.get(), ModItems.RAW_LOBSTER.get(), 2);
        genFishLoot(ModEntities.JAPANESE_SPIDER_CRAB.get(), ModItems.RAW_SPIDERCRAB.get(), 5);
        genFishLoot(ModEntities.TASMANIAN_CRAB.get(), ModItems.RAW_MEDIUM_CRAB.get(), 2);
        genFishLoot(ModEntities.SALLY_LIGHTFOOT_CRAB.get(), ModItems.RAW_SMALL_CRAB.get(), 2);
        genFishLoot(ModEntities.GREEN_SEA_TURTLE.get(), ModItems.RAW_TURTLE.get(), 4);

        this.add(ModEntities.SPINNER_DOLPHIN.get(), LootTable.lootTable());
        this.add(ModEntities.ORCA.get(), LootTable.lootTable());
        this.add(ModEntities.HUMPBACK_WHALE.get(), LootTable.lootTable());
        this.add(ModEntities.MANATEE.get(), LootTable.lootTable());
        this.add(ModEntities.TRENCH_MONSTER.get(), LootTable.lootTable());
    }

    public void genFishLoot(EntityType<?> entityType, Item rawFish, int count){
        this.add(
            entityType,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(rawFish)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, count)))
                            .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
                )
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.BONE_MEAL))
                        .when(LootItemRandomChanceCondition.randomChance(0.05F))
            )
        );
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return (Stream<EntityType<?>>) ModEntities.ENTITIES.getEntries().stream().map(DeferredHolder::get)
                .filter(type -> this.canHaveLootTable(type));
    }
}
