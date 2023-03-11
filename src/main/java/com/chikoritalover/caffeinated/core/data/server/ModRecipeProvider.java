package com.chikoritalover.caffeinated.core.data.server;

import com.chikoritalover.caffeinated.core.Caffeinated;
import com.chikoritalover.caffeinated.core.registry.ModBlocks;
import com.chikoritalover.caffeinated.core.registry.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    private static ResourceLocation createId(String id) {
        return new ResourceLocation(Caffeinated.MODID, id);
    }

    protected void buildCraftingRecipes(Consumer<FinishedRecipe> exporter) {
        this.createCompactBlockRecipe(ModItems.COFFEE_BEANS.get(), ModBlocks.COFFEE_BEAN_BLOCK.get(), exporter);
        this.createCompactBlockRecipe(ModItems.GROUND_COFFEE.get(), ModBlocks.GROUND_COFFEE_BLOCK.get(), exporter);

        ShapelessRecipeBuilder.shapeless(ModItems.COFFEE_BEANS.get())
                .requires(ModItems.COFFEE_BERRIES.get())
                .group("coffee_beans")
                .unlockedBy(getHasName(ModItems.COFFEE_BERRIES.get()), has(ModItems.COFFEE_BERRIES.get()))
                .save(exporter, createId("coffee_beans_from_coffee_berries"));

        ShapedRecipeBuilder.shaped(ModItems.GROUND_COFFEE.get())
                .define('#', ModItems.COFFEE_BEANS.get())
                .pattern("###")
                .group("ground_coffee")
                .unlockedBy(getHasName(ModItems.COFFEE_BEANS.get()), has(ModItems.COFFEE_BEANS.get()))
                .save(exporter, createId("ground_coffee_from_coffee_beans"));

        ShapelessRecipeBuilder.shapeless(ModItems.JAVA_BANNER_PATTERN.get())
                .requires(Items.PAPER)
                .requires(ModItems.COFFEE_BOTTLE.get())
                .unlockedBy(getHasName(ModItems.COFFEE_BOTTLE.get()), has(ModItems.COFFEE_BOTTLE.get()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(ModItems.TIRAMISU.get(), 2)
                .define('#', ModItems.COFFEE_BOTTLE.get())
                .define('C', Items.COCOA_BEANS)
                .define('E', Items.EGG)
                .define('M', Items.MILK_BUCKET)
                .define('W', Items.WHEAT)
                .pattern("C#C")
                .pattern("EME")
                .pattern("WWW")
                .group("tiramisu")
                .unlockedBy(getHasName(ModItems.COFFEE_BOTTLE.get()), has(ModItems.COFFEE_BOTTLE.get())).save(exporter);

    }


    private void createCompactBlockRecipe(ItemLike input, ItemLike output, Consumer<FinishedRecipe> consumer) {
        String inputGroup = ForgeRegistries.ITEMS.getKey(input.asItem()).getPath();
        String outputGroup = ForgeRegistries.ITEMS.getKey(output.asItem()).getPath();

        ShapedRecipeBuilder.shaped(output)
                .define('#', input)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_ingredient", has(input))
                .group(outputGroup)
                .save(consumer, createId(outputGroup));
        ShapelessRecipeBuilder.shapeless(input, 9)
                .requires(output)
                .unlockedBy("has_compact_block", has(output))
                .group(inputGroup)
                .save(consumer, createId(inputGroup + "_from_block"));
    }
}
