package com.chikoritalover.caffeinated.core.registry;

import com.chikoritalover.caffeinated.core.Caffeinated;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Caffeinated.MODID);

    private static RegistryObject<SoundEvent> register(String id) {
        return SOUND_EVENTS.register(id, () -> new SoundEvent(new ResourceLocation(Caffeinated.MODID, id)));
    }

    public static final RegistryObject<SoundEvent> BLOCK_CAULDRON_BREW = register("block.cauldron.brew");
    public static final RegistryObject<SoundEvent> BLOCK_CAULDRON_BUBBLE = register("block.cauldron.bubble");

    public static final RegistryObject<SoundEvent> BLOCK_COFFEE_BEAN_BLOCK_BREAK = register("block.coffee_bean_block.break");
    public static final RegistryObject<SoundEvent> BLOCK_COFFEE_BEAN_BLOCK_FALL = register("block.coffee_bean_block.fall");
    public static final RegistryObject<SoundEvent> BLOCK_COFFEE_BEAN_BLOCK_HIT = register("block.coffee_bean_block.hit");
    public static final RegistryObject<SoundEvent> BLOCK_COFFEE_BEAN_BLOCK_PLACE = register("block.coffee_bean_block.place");
    public static final RegistryObject<SoundEvent> BLOCK_COFFEE_BEAN_BLOCK_STEP = register("block.coffee_bean_block.step");

    public static final RegistryObject<SoundEvent> BLOCK_COFFEE_SHRUB_PICK_BERRIES = register("block.coffee_shrub.pick_berries");

    public static final RegistryObject<SoundEvent> BLOCK_GROUND_COFFEE_BREAK = register("block.ground_coffee_block.break");
    public static final RegistryObject<SoundEvent> BLOCK_GROUND_COFFEE_PLACE = register("block.ground_coffee_block.place");

    public static final RegistryObject<SoundEvent> ITEM_COFFEE_BOTTLE_DRINK = register("item.coffee_bottle.drink");
    public static final RegistryObject<SoundEvent> ITEM_GROUND_COFFEE_SPLASH = register("item.ground_coffee.splash");
}
