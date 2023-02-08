package com.chikoritalover.caffeinated.core.integration.farmersdelight;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;

public class FDTab {

    @Nullable
    public static CreativeModeTab getFDItemTab() {
        if (!ModList.get().isLoaded("farmersdelight")) {
            return null;
        }
        return FarmersDelight.CREATIVE_TAB;
    }
}
