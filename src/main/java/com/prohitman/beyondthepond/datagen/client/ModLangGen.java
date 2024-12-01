package com.prohitman.beyondthepond.datagen.client;


import com.prohitman.beyondthepond.BeyondThePond;
import com.prohitman.beyondthepond.init.ModEntities;
import com.prohitman.beyondthepond.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.codehaus.plexus.util.StringUtils;

public class ModLangGen extends LanguageProvider {

    public ModLangGen(PackOutput output) {
        super(output, BeyondThePond.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //Items
        addItem(ModItems.RAINBOW_TROUT_SPAWN_EGG);
        addItem(ModItems.SPINNER_DOLPHIN_SPAWN_EGG);
        addItem(ModItems.GIANT_ISOPOD_SPAWN_EGG);
        addItem(ModItems.GREEN_SEA_TURTLE_SPAWN_EGG);
        addItem(ModItems.ORCA_SPAWN_EGG);
        addItem(ModItems.HUMPBACK_WHALE_SPAWN_EGG);
        addItem(ModItems.MANATEE_SPAWN_EGG);
        addItem(ModItems.COOKIE_CUTTER_SHARK_SPAWN_EGG);
        addItem(ModItems.SPOTTED_EAGLE_STINGRAY_SPAWN_EGG);
        addItem(ModItems.COCONUT_CRAB_SPAWN_EGG);
        addItem(ModItems.CHANNEL_CATFISH_SPAWN_EGG);
        addItem(ModItems.CUTTLEFISH_SPAWN_EGG);
        addItem(ModItems.LARGEMOUTH_BASS_SPAWN_EGG);
        addItem(ModItems.LEMON_SHARK_SPAWN_EGG);
        addItem(ModItems.LONGNOSE_GAR_SPAWN_EGG);
        addItem(ModItems.NURSE_SHARK_SPAWN_EGG);
        addItem(ModItems.SAILFISH_SPAWN_EGG);
        addItem(ModItems.SUNFISH_SPAWN_EGG);
        addItem(ModItems.GIANT_TIGER_PRAWN_SPAWN_EGG);
        addItem(ModItems.EUROPEAN_LOBSTER_SPAWN_EGG);
        addItem(ModItems.JAPANESE_SPIDER_CRAB_SPAWN_EGG);
        addItem(ModItems.TASMANIAN_CRAB_SPAWN_EGG);
        addItem(ModItems.SALLY_LIGHTFOOT_CRAB_SPAWN_EGG);
        addItem(ModItems.TRENCH_MONSTER_SPAWN_EGG);

        addItem(ModItems.RAINBOW_TROUT_BUCKET);
        addItem(ModItems.CHANNEL_CATFISH_BUCKET);
        addItem(ModItems.LONGNOSE_GAR_BUCKET);
        addItem(ModItems.LARGEMOUTH_BASS_BUCKET);

        addItem(ModItems.RAW_ISOPOD);
        addItem(ModItems.RAW_COOKIECUTTER);
        addItem(ModItems.RAW_CUTTLEFISH);
        addItem(ModItems.RAW_LARGE_CRAB);
        addItem(ModItems.RAW_LOBSTER);
        addItem(ModItems.RAW_MEDIUM_CRAB);
        addItem(ModItems.RAW_SAILFISH);
        addItem(ModItems.RAW_SHARK);
        addItem(ModItems.RAW_SHRIMP);
        addItem(ModItems.RAW_SMALL_CRAB);
        addItem(ModItems.RAW_SPIDERCRAB);
        addItem(ModItems.RAW_STINGRAY);
        addItem(ModItems.RAW_SUNFISH);
        addItem(ModItems.RAW_TURTLE);
        addItem(ModItems.COOKED_ISOPOD);
        addItem(ModItems.COOKED_COOKIECUTTER);
        addItem(ModItems.COOKED_CUTTLEFISH);
        addItem(ModItems.COOKED_LARGE_CRAB);
        addItem(ModItems.COOKED_LOBSTER);
        addItem(ModItems.COOKED_MEDIUM_CRAB);
        addItem(ModItems.COOKED_SAILFISH);
        addItem(ModItems.COOKED_SHARK);
        addItem(ModItems.COOKED_SHRIMP);
        addItem(ModItems.COOKED_SMALL_CRAB);
        addItem(ModItems.COOKED_SPIDERCRAB);
        addItem(ModItems.COOKED_STINGRAY);
        addItem(ModItems.COOKED_SUNFISH);
        addItem(ModItems.COOKED_TURTLE);
        
        //Entities
        addEntity(ModEntities.RAINBOW_TROUT);
        addEntity(ModEntities.SPINNER_DOLPHIN);
        addEntity(ModEntities.GIANT_ISOPOD);
        addEntity(ModEntities.GREEN_SEA_TURTLE);
        addEntity(ModEntities.ORCA);
        addEntity(ModEntities.HUMPBACK_WHALE);
        addEntity(ModEntities.MANATEE);
        addEntity(ModEntities.COOKIE_CUTTER_SHARK);
        addEntity(ModEntities.SPOTTED_EAGLE_STINGRAY);
        addEntity(ModEntities.COCONUT_CRAB);
        addEntity(ModEntities.CHANNEL_CATFISH);
        addEntity(ModEntities.CUTTLEFISH);
        addEntity(ModEntities.LARGEMOUTH_BASS);
        addEntity(ModEntities.LEMON_SHARK);
        addEntity(ModEntities.LONGNOSE_GAR);
        addEntity(ModEntities.NURSE_SHARK);
        addEntity(ModEntities.SAILFISH);
        addEntity(ModEntities.SUNFISH);
        addEntity(ModEntities.GIANT_TIGER_PRAWN);
        addEntity(ModEntities.EUROPEAN_LOBSTER);
        addEntity(ModEntities.JAPANESE_SPIDER_CRAB);
        addEntity(ModEntities.TASMANIAN_CRAB);
        addEntity(ModEntities.SALLY_LIGHTFOOT_CRAB);
        addEntity(ModEntities.TRENCH_MONSTER);

        add("itemGroup." + BeyondThePond.MODID, "Beyond The Pond");
    }

    private void addBlock(DeferredBlock<? extends Block> key) {
        add(key.get().getDescriptionId(), StringUtils.capitaliseAllWords(key.getId().getPath().replaceAll("_", " ")));
    }

    private void addBlockWithSuffix(DeferredBlock<? extends Block> key, String suffix) {
        add(key.get().getDescriptionId(), StringUtils.capitaliseAllWords(key.getId().getPath().replaceAll("_", " ")).concat(suffix));
    }

    private void addItem(DeferredItem<? extends Item> key){
        add(key.get().getDescriptionId(), StringUtils.capitaliseAllWords(key.getId().getPath().replaceAll("_", " ")));
    }

    private <T extends Entity> void addEntity(DeferredHolder<EntityType<?>, EntityType<T>> key){
        add(key.get().getDescriptionId(), StringUtils.capitaliseAllWords(key.getId().getPath().replaceAll("_", " ")));
    }
}