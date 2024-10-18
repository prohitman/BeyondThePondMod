package com.prohitman.beyondthepond.init;

import com.prohitman.beyondthepond.BeyondThePond;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BeyondThePond.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BTP_TAB = CREATIVE_MODE_TABS.register("beyondthepond_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.beyondthepond"))
            .icon(() -> Items.REDSTONE.getDefaultInstance())
            .displayItems((parameters, output) -> {
                //output.accept(EXAMPLE_ITEM.get());
            }).build());

}
