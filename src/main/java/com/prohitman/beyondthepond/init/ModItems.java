package com.prohitman.beyondthepond.init;

import com.prohitman.beyondthepond.BeyondThePond;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BeyondThePond.MODID);

    public static final DeferredItem<Item> RAINBOW_TROUT_SPAWN_EGG = ITEMS.register("rainbow_trout_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.RAINBOW_TROUT, 0x74a3af, 0xcdc9df, new Item.Properties()));
    public static final DeferredItem<Item> COOKIE_CUTTER_SHARK_SPAWN_EGG = ITEMS.register("cookie_cutter_shark_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.COOKIE_CUTTER_SHARK, 0x74a3af, 0xcdc9df, new Item.Properties()));
    public static final DeferredItem<Item> SPOTTED_EAGLE_STINGRAY_SPAWN_EGG = ITEMS.register("spotted_eagle_stingray_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SPOTTED_EAGLE_STINGRAY, 0x74a3af, 0xcdc9df, new Item.Properties()));
    public static final DeferredItem<Item> SPINNER_DOLPHIN_SPAWN_EGG = ITEMS.register("spinner_dolphin_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SPINNER_DOLPHIN, 0x74a3af, 0xcdc9df, new Item.Properties()));

    public static final DeferredItem<Item> ORCA_SPAWN_EGG = ITEMS.register("orca_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.ORCA, 0x74a3af, 0xcdc9df, new Item.Properties()));

    public static final DeferredItem<Item> HUMPBACK_WHALE_SPAWN_EGG = ITEMS.register("humpback_whale_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.HUMPBACK_WHALE, 0x74a3af, 0xcdc9df, new Item.Properties()));

    public static final DeferredItem<Item> MANATEE_SPAWN_EGG = ITEMS.register("manatee_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.MANATEE, 0x74a3af, 0xcdc9df, new Item.Properties()));

    public static final DeferredItem<Item> GIANT_ISOPOD_SPAWN_EGG = ITEMS.register("giant_isopod_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.GIANT_ISOPOD, 0x74a3af, 0xcdc9df, new Item.Properties()));
    public static final DeferredItem<Item> COCONUT_CRAB_SPAWN_EGG = ITEMS.register("coconut_crab_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.COCONUT_CRAB, 0x74a3af, 0xcdc9df, new Item.Properties()));

    public static final DeferredItem<Item> GREEN_SEA_TURTLE_SPAWN_EGG = ITEMS.register("green_sea_turtle_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.GREEN_SEA_TURTLE, 0x74a3af, 0xcdc9df, new Item.Properties()));

}
