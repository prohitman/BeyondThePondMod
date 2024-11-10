package com.prohitman.beyondthepond.init;

import com.prohitman.beyondthepond.BeyondThePond;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedList;
import java.util.List;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BeyondThePond.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BTP_TAB = CREATIVE_MODE_TABS.register("beyondthepond_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.beyondthepond"))
            .icon(() -> Items.COD.getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.acceptAll(getTabItems());
            }).build());

    private static List<ItemStack> getTabItems(){
        List<ItemStack> list = new LinkedList<>();
        list.addAll(ModItems.ITEMS.getEntries().stream().map(DeferredHolder::get)
                .map(Item::getDefaultInstance).toList());

        return list;
    }

}
