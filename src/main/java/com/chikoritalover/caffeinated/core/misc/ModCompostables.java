package com.chikoritalover.caffeinated.core.misc;

import com.chikoritalover.caffeinated.core.registry.ModBlocks;
import com.chikoritalover.caffeinated.core.registry.ModItems;
import net.minecraft.world.level.block.ComposterBlock;

public class ModCompostables {

    public static void register() {
        ComposterBlock.COMPOSTABLES.put(ModBlocks.COFFEE_BEAN_BLOCK.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.GROUND_COFFEE_BLOCK.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.COFFEE_BEANS.get(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(ModItems.GROUND_COFFEE.get(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(ModItems.COFFEE_BERRIES.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.TIRAMISU.get(), 1.0F);
    }
}
