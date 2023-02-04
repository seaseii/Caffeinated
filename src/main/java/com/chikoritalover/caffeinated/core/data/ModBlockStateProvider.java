package com.chikoritalover.caffeinated.core.data;

import com.chikoritalover.caffeinated.core.Caffeinated;
import com.chikoritalover.caffeinated.core.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Caffeinated.MODID, existingFileHelper);
    }

    protected void registerStatesAndModels() {
        this.cubeAll(ModBlocks.COFFEE_BEAN_BLOCK.get());
        this.cubeAll(ModBlocks.GROUND_COFFEE_BLOCK.get());
    }
}
