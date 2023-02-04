package com.chikoritalover.caffeinated.core.registry;

import com.chikoritalover.caffeinated.common.item.CoffeeBottleItem;
import com.chikoritalover.caffeinated.core.Caffeinated;
import com.chikoritalover.caffeinated.core.misc.ModFoods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Caffeinated.MODID);

    public static final RegistryObject<Item> GROUND_COFFEE = ITEMS.register("ground_coffee", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static final RegistryObject<Item> COFFEE_BERRIES = ITEMS.register("coffee_berries", () -> new Item(new Item.Properties().food(ModFoods.COFFEE_BERRIES).tab(CreativeModeTab.TAB_FOOD)));
    public static final RegistryObject<Item> COFFEE_BOTTLE = ITEMS.register("coffee_bottle", () -> new CoffeeBottleItem(new Item.Properties().food(ModFoods.COFFEE_BOTTLE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));
    public static final RegistryObject<Item> TIRAMISU = ITEMS.register("tiramisu", () -> new Item(new Item.Properties().food(ModFoods.TIRAMISU).tab(CreativeModeTab.TAB_FOOD)));
}
