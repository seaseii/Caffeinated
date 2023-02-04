package com.chikoritalover.caffeinated.core.registry;

import com.chikoritalover.caffeinated.core.Caffeinated;
import com.chikoritalover.caffeinated.core.misc.ModSoundTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Caffeinated.MODID);

    public static final RegistryObject<Block> COFFEE_BEAN_BLOCK = register("coffee_bean_block", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).sound(ModSoundTypes.COFFEE_BEAN_BLOCK).strength(1.8F, 3.0F)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> GROUND_COFFEE_BLOCK = register("ground_coffee_block", () -> new SandBlock(8473899, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.PODZOL).strength(0.5F).sound(ModSoundTypes.GROUND_COFFEE_BLOCK)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static RegistryObject<Block> register(String id, Supplier<Block> blockSupplier, CreativeModeTab creativeModeTab) {
        var returnBlock = BLOCKS.register(id, blockSupplier);
        registerItem(id, returnBlock, creativeModeTab);
        return returnBlock;
    }

    private static void registerItem(String id, Supplier<Block> blockSupplier, CreativeModeTab creativeModeTab) {
        ModItems.ITEMS.register(id, () -> new BlockItem(blockSupplier.get(), new Item.Properties().tab(creativeModeTab)));
    }
}
