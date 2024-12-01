package com.prohitman.beyondthepond.init;

import com.prohitman.beyondthepond.BeyondThePond;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

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
            () -> new DeferredSpawnEggItem(ModEntities.GREEN_SEA_TURTLE, 0xD1E4A4, 0x5A3826, new Item.Properties()));
    public static final DeferredItem<Item> TRENCH_MONSTER_SPAWN_EGG = ITEMS.register("trench_monster_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.TRENCH_MONSTER, 0x97BF26, 0xBBEC31, new Item.Properties()));

    public static final DeferredItem<Item> RAINBOW_TROUT_BUCKET = ITEMS.register("rainbow_trout_bucket",
            () -> new MobBucketItem(
                    ModEntities.RAINBOW_TROUT.get(),
                    Fluids.WATER,
                    SoundEvents.BUCKET_EMPTY_FISH,
                    new Item.Properties().stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)
            ));
    public static final DeferredItem<Item> CHANNEL_CATFISH_BUCKET = ITEMS.register("channel_catfish_bucket",
            () -> new MobBucketItem(
                    ModEntities.CHANNEL_CATFISH.get(),
                    Fluids.WATER,
                    SoundEvents.BUCKET_EMPTY_FISH,
                    new Item.Properties().stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)
            ));
    public static final DeferredItem<Item> LONGNOSE_GAR_BUCKET = ITEMS.register("longnose_gar_bucket",
            () -> new MobBucketItem(
                    ModEntities.LONGNOSE_GAR.get(),
                    Fluids.WATER,
                    SoundEvents.BUCKET_EMPTY_FISH,
                    new Item.Properties().stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)
            ));
    public static final DeferredItem<Item> LARGEMOUTH_BASS_BUCKET = ITEMS.register("largemouth_bass_bucket",
            () -> new MobBucketItem(
                    ModEntities.LARGEMOUTH_BASS.get(),
                    Fluids.WATER,
                    SoundEvents.BUCKET_EMPTY_FISH,
                    new Item.Properties().stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)
            ));

    public static final DeferredItem<Item> RAW_ISOPOD = ITEMS.register("raw_isopod",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_FISH)));
    public static final DeferredItem<Item> RAW_COOKIECUTTER = ITEMS.register("raw_cookiecutter",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_SMALL_FISH)));
    public static final DeferredItem<Item> RAW_CUTTLEFISH = ITEMS.register("raw_cuttlefish",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_FISH)));
    public static final DeferredItem<Item> RAW_LARGE_CRAB = ITEMS.register("raw_large_crab",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_LARGE_FISH)));
    public static final DeferredItem<Item> RAW_LOBSTER = ITEMS.register("raw_lobster",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_FISH)));
    public static final DeferredItem<Item> RAW_MEDIUM_CRAB = ITEMS.register("raw_medium_crab",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_FISH)));
    public static final DeferredItem<Item> RAW_SAILFISH = ITEMS.register("raw_sailfish",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_LARGE_FISH)));
    public static final DeferredItem<Item> RAW_SHARK = ITEMS.register("raw_shark",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_LARGE_FISH)));
    public static final DeferredItem<Item> RAW_SHRIMP = ITEMS.register("raw_shrimp",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_SMALL_FISH)));
    public static final DeferredItem<Item> RAW_SMALL_CRAB = ITEMS.register("raw_small_crab",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_SMALL_FISH)));
    public static final DeferredItem<Item> RAW_SPIDERCRAB = ITEMS.register("raw_spidercrab",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_SMALL_FISH)));
    public static final DeferredItem<Item> RAW_STINGRAY = ITEMS.register("raw_stingray",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_FISH)));
    public static final DeferredItem<Item> RAW_SUNFISH = ITEMS.register("raw_sunfish",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_LARGE_FISH)));
    public static final DeferredItem<Item> RAW_TURTLE = ITEMS.register("raw_turtle",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_FISH)));

    public static final DeferredItem<Item> COOKED_ISOPOD = ITEMS.register("cooked_isopod",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_FISH)));
    public static final DeferredItem<Item> COOKED_COOKIECUTTER = ITEMS.register("cooked_cookiecutter",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_SMALL_FISH)));
    public static final DeferredItem<Item> COOKED_CUTTLEFISH = ITEMS.register("cooked_cuttlefish",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_FISH)));
    public static final DeferredItem<Item> COOKED_LARGE_CRAB = ITEMS.register("cooked_large_crab",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_LARGE_FISH)));
    public static final DeferredItem<Item> COOKED_LOBSTER = ITEMS.register("cooked_lobster",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_FISH)));
    public static final DeferredItem<Item> COOKED_MEDIUM_CRAB = ITEMS.register("cooked_medium_crab",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_FISH)));
    public static final DeferredItem<Item> COOKED_SAILFISH = ITEMS.register("cooked_sailfish",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_LARGE_FISH)));
    public static final DeferredItem<Item> COOKED_SHARK = ITEMS.register("cooked_shark",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_LARGE_FISH)));
    public static final DeferredItem<Item> COOKED_SHRIMP = ITEMS.register("cooked_shrimp",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_SMALL_FISH)));
    public static final DeferredItem<Item> COOKED_SMALL_CRAB = ITEMS.register("cooked_small_crab",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_SMALL_FISH)));
    public static final DeferredItem<Item> COOKED_SPIDERCRAB = ITEMS.register("cooked_spidercrab",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_SMALL_FISH)));
    public static final DeferredItem<Item> COOKED_STINGRAY = ITEMS.register("cooked_stingray",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_FISH)));
    public static final DeferredItem<Item> COOKED_SUNFISH = ITEMS.register("cooked_sunfish",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_LARGE_FISH)));
    public static final DeferredItem<Item> COOKED_TURTLE = ITEMS.register("cooked_turtle",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_FISH)));
}
