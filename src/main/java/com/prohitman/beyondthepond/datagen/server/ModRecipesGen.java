package com.prohitman.beyondthepond.datagen.server;

import com.prohitman.beyondthepond.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipesGen extends RecipeProvider {
    public ModRecipesGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
/*        createIceCream(recipeOutput, ModItems.SAP_ICE_CREAM, ModItems.SAP_BALL);
        createIceCream(recipeOutput, ModItems.PSYCHO_BERRY_ICE_CREAM, ModItems.PSYCHO_BERRY);
        createIceCream(recipeOutput, ModItems.MELON_ICE_CREAM, Items.MELON_SLICE);
        smeltingResultFromBase(recipeOutput, ModItems.COOKED_VENISON, ModItems.RAW_VENISON);*/

        createCookingRecipe(
                ModItems.RAW_ISOPOD.get(),
                ModItems.COOKED_ISOPOD.asItem(),
                0.35f,
                200,
                recipeOutput);
        createCookingRecipe(
                ModItems.RAW_COOKIECUTTER.get(),
                ModItems.COOKED_COOKIECUTTER.asItem(),
                0.2f,
                100,
                recipeOutput);
        createCookingRecipe(
                ModItems.RAW_CUTTLEFISH.get(),
                ModItems.COOKED_CUTTLEFISH.asItem(),
                0.35f,
                200,
                recipeOutput);
        createCookingRecipe(
                ModItems.RAW_LARGE_CRAB.get(),
                ModItems.COOKED_LARGE_CRAB.asItem(),
                0.5f,
                300,
                recipeOutput);
        createCookingRecipe(
                ModItems.RAW_LOBSTER.get(),
                ModItems.COOKED_LOBSTER.asItem(),
                0.35f,
                200,
                recipeOutput);
        createCookingRecipe(
                ModItems.RAW_MEDIUM_CRAB.get(),
                ModItems.COOKED_MEDIUM_CRAB.asItem(),
                0.35f,
                200,
                recipeOutput);
        createCookingRecipe(
                ModItems.RAW_SAILFISH.get(),
                ModItems.COOKED_SAILFISH.asItem(),
                0.5f,
                300,
                recipeOutput);
        createCookingRecipe(
                ModItems.RAW_SHARK.get(),
                ModItems.COOKED_SHARK.asItem(),
                0.5f,
                300,
                recipeOutput);
        createCookingRecipe(
                ModItems.RAW_SHRIMP.get(),
                ModItems.COOKED_SHRIMP.asItem(),
                0.2f,
                100,
                recipeOutput);
        createCookingRecipe(
                ModItems.RAW_SMALL_CRAB.get(),
                ModItems.COOKED_SMALL_CRAB.asItem(),
                0.2f,
                100,
                recipeOutput);
        createCookingRecipe(
                ModItems.RAW_SPIDERCRAB.get(),
                ModItems.COOKED_SPIDERCRAB.asItem(),
                0.35f,
                200,
                recipeOutput);
        createCookingRecipe(
                ModItems.RAW_STINGRAY.get(),
                ModItems.COOKED_STINGRAY.asItem(),
                0.35f,
                200,
                recipeOutput);
        createCookingRecipe(
                ModItems.RAW_SUNFISH.get(),
                ModItems.COOKED_SUNFISH.asItem(),
                0.5f,
                300,
                recipeOutput);
        createCookingRecipe(
                ModItems.RAW_TURTLE.get(),
                ModItems.COOKED_TURTLE.asItem(),
                0.35f,
                200,
                recipeOutput);
    }

    private static void createCookingRecipe(Item rawFish, Item cookedFish, float experience, int cookingTime, RecipeOutput recipeOutput){
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(rawFish), RecipeCategory.FOOD, cookedFish, experience, cookingTime)
                .unlockedBy("has_" + rawFish.getDescriptionId(), has(rawFish))
                .save(recipeOutput, rawFish.getDescriptionId() + "_from_furnace");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(rawFish), RecipeCategory.FOOD, cookedFish, experience, cookingTime/2)
                .unlockedBy("has_" + rawFish.getDescriptionId(), has(rawFish))
                .save(recipeOutput, rawFish.getDescriptionId() + "_from_smoker");
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(rawFish), RecipeCategory.FOOD, cookedFish, experience, cookingTime*3)
                .unlockedBy("has_" + rawFish.getDescriptionId(), has(rawFish))
                .save(recipeOutput, rawFish.getDescriptionId() + "_from_campfire");
    }

    private static void createIceCream(RecipeOutput recipeOutput, ItemLike iceCream, ItemLike mainIngredient) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, iceCream)
                .requires(Items.BOWL)
                .requires(Blocks.ICE)
                .requires(Items.MILK_BUCKET)
                .requires(mainIngredient)
                .unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(iceCream.asItem()).getPath(), has(iceCream))
                .unlockedBy("has_bowl", has(Items.BOWL))
                .unlockedBy("has_ice", has(Blocks.ICE))
                .unlockedBy("has_milk_bucket", has(Items.MILK_BUCKET))
                .unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(mainIngredient.asItem()).getPath(), has(mainIngredient))
                .save(recipeOutput);
    }
}
