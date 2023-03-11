package com.chikoritalover.caffeinated.core.data.client;

import com.chikoritalover.caffeinated.core.Caffeinated;
import com.chikoritalover.caffeinated.core.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Caffeinated.MODID, existingFileHelper);
    }

    protected void registerModels() {
        this.basicItem(ModItems.GROUND_COFFEE.get());

        this.basicItem(ModItems.COFFEE_BERRIES.get());
        this.basicItem(ModItems.COFFEE_BOTTLE.get());
        this.basicItem(ModItems.TIRAMISU.get());
        this.basicItem(ModItems.JAVA_BANNER_PATTERN.get());
    }
}
