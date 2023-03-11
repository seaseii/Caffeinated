package com.chikoritalover.caffeinated.core.data.client;

import com.chikoritalover.caffeinated.core.Caffeinated;
import com.chikoritalover.caffeinated.core.registry.ModBlocks;
import com.google.errorprone.annotations.Var;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
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
