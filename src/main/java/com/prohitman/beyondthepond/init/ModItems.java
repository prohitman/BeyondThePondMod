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

}
