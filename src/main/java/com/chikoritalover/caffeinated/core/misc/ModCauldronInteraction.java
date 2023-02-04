package com.chikoritalover.caffeinated.core.misc;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;

import java.util.Map;

public class ModCauldronInteraction {

    public static final Map<Item, CauldronInteraction> COFFEE = CauldronInteraction.newInteractionMap();
    public static final Map<Item, CauldronInteraction> GROUND_COFFEE = CauldronInteraction.newInteractionMap();

    public static void register() {
    }
}
