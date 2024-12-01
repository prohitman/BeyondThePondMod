package com.prohitman.beyondthepond.init;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class ModFoods {
    public static final FoodProperties RAW_FISH = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodProperties RAW_SMALL_FISH = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build();
    public static final FoodProperties RAW_LARGE_FISH = new FoodProperties.Builder().nutrition(4).saturationModifier(0.25F).build();
    public static final FoodProperties COOKED_FISH = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).build();
    public static final FoodProperties COOKED_SMALL_FISH = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4F).build();
    public static final FoodProperties COOKED_LARGE_FISH = new FoodProperties.Builder().nutrition(7).saturationModifier(0.9F).build();

}
