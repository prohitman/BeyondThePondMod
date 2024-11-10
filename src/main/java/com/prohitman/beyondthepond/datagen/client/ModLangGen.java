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

        //Entities
        addEntity(ModEntities.RAINBOW_TROUT);
        addEntity(ModEntities.SPINNER_DOLPHIN);
        addEntity(ModEntities.GIANT_ISOPOD);
        addEntity(ModEntities.GREEN_SEA_TURTLE);
        addEntity(ModEntities.ORCA);
        addEntity(ModEntities.HUMPBACK_WHALE);
        addEntity(ModEntities.MANATEE);

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