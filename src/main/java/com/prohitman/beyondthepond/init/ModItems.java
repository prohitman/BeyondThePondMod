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
            () -> new DeferredSpawnEggItem(ModEntities.RAINBOW_TROUT, 0x67723A, 0x19260A, new Item.Properties()));
    public static final DeferredItem<Item> COOKIE_CUTTER_SHARK_SPAWN_EGG = ITEMS.register("cookie_cutter_shark_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.COOKIE_CUTTER_SHARK, 0x483B2F, 0xB1977E, new Item.Properties()));
    public static final DeferredItem<Item> SPOTTED_EAGLE_STINGRAY_SPAWN_EGG = ITEMS.register("spotted_eagle_stingray_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SPOTTED_EAGLE_STINGRAY, 0x1C1B21, 0xB8B5C5, new Item.Properties()));
    public static final DeferredItem<Item> CHANNEL_CATFISH_SPAWN_EGG = ITEMS.register("channel_catfish_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.CHANNEL_CATFISH, 0x454C39, 0x81823C, new Item.Properties()));
    public static final DeferredItem<Item> CUTTLEFISH_SPAWN_EGG = ITEMS.register("cuttlefish_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.CUTTLEFISH, 0x581019, 0x9E2B3A, new Item.Properties()));
    public static final DeferredItem<Item> LARGEMOUTH_BASS_SPAWN_EGG = ITEMS.register("largemouth_bass_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.LARGEMOUTH_BASS, 0x646A21, 0xD9DCCB, new Item.Properties()));
    public static final DeferredItem<Item> LEMON_SHARK_SPAWN_EGG = ITEMS.register("lemon_shark_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.LEMON_SHARK, 0xD9C261, 0xAB5C0E, new Item.Properties()));
    public static final DeferredItem<Item> LONGNOSE_GAR_SPAWN_EGG = ITEMS.register("longnose_gar_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.LONGNOSE_GAR, 0x695737, 0x995218, new Item.Properties()));
    public static final DeferredItem<Item> NURSE_SHARK_SPAWN_EGG = ITEMS.register("nurse_shark_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.NURSE_SHARK, 0x876843, 0x876843, new Item.Properties()));
    public static final DeferredItem<Item> SAILFISH_SPAWN_EGG = ITEMS.register("sailfish_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SAILFISH, 0x10305B, 0xE0B75F, new Item.Properties()));
    public static final DeferredItem<Item> SUNFISH_SPAWN_EGG = ITEMS.register("sunfish_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SUNFISH, 0xEDEFF2, 0xA7B1BE, new Item.Properties()));
    public static final DeferredItem<Item> GIANT_TIGER_PRAWN_SPAWN_EGG = ITEMS.register("giant_tiger_prawn_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.GIANT_TIGER_PRAWN, 0x493421, 0xE5D482, new Item.Properties()));
    public static final DeferredItem<Item> SPINNER_DOLPHIN_SPAWN_EGG = ITEMS.register("spinner_dolphin_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SPINNER_DOLPHIN, 0x494949, 0x9B9B9B, new Item.Properties()));

    public static final DeferredItem<Item> ORCA_SPAWN_EGG = ITEMS.register("orca_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.ORCA, 0x0E0E0F, 0xF3F1D0, new Item.Properties()));

    public static final DeferredItem<Item> HUMPBACK_WHALE_SPAWN_EGG = ITEMS.register("humpback_whale_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.HUMPBACK_WHALE, 0x727272, 0x545454, new Item.Properties()));

    public static final DeferredItem<Item> MANATEE_SPAWN_EGG = ITEMS.register("manatee_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.MANATEE, 0xA3997B, 0x51413C, new Item.Properties()));

    public static final DeferredItem<Item> GIANT_ISOPOD_SPAWN_EGG = ITEMS.register("giant_isopod_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.GIANT_ISOPOD, 0xB7A3AD, 0xEEDEE6, new Item.Properties()));
    public static final DeferredItem<Item> COCONUT_CRAB_SPAWN_EGG = ITEMS.register("coconut_crab_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.COCONUT_CRAB, 0x9E5F40, 0x2C88BD, new Item.Properties()));
    public static final DeferredItem<Item> EUROPEAN_LOBSTER_SPAWN_EGG = ITEMS.register("european_lobster_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.EUROPEAN_LOBSTER, 0x2A4876, 0x142641, new Item.Properties()));
    public static final DeferredItem<Item> JAPANESE_SPIDER_CRAB_SPAWN_EGG = ITEMS.register("japanese_spider_crab_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.JAPANESE_SPIDER_CRAB, 0xD14E2D, 0xE8D0CA, new Item.Properties()));
    public static final DeferredItem<Item> TASMANIAN_CRAB_SPAWN_EGG = ITEMS.register("tasmanian_crab_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.TASMANIAN_CRAB, 0xD04E2D, 0x3A251F, new Item.Properties()));
    public static final DeferredItem<Item> SALLY_LIGHTFOOT_CRAB_SPAWN_EGG = ITEMS.register("sally_lightfoot_crab_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SALLY_LIGHTFOOT_CRAB, 0x972918, 0x7FB2B3, new Item.Properties()));

    public static final DeferredItem<Item> GREEN_SEA_TURTLE_SPAWN_EGG = ITEMS.register("green_sea_turtle_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.GREEN_SEA_TURTLE, 0x74a3af, 0xcdc9df, new Item.Properties()));

}
