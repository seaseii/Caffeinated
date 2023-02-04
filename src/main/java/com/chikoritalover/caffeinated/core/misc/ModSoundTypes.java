package com.chikoritalover.caffeinated.core.misc;

import com.chikoritalover.caffeinated.core.registry.ModSounds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;

public class ModSoundTypes {

    public static final SoundType COFFEE_BEAN_BLOCK = new ForgeSoundType(1.0F, 1.0F,
            ModSounds.BLOCK_COFFEE_BEAN_BLOCK_BREAK,
            ModSounds.BLOCK_COFFEE_BEAN_BLOCK_STEP,
            ModSounds.BLOCK_COFFEE_BEAN_BLOCK_PLACE,
            ModSounds.BLOCK_COFFEE_BEAN_BLOCK_HIT,
            ModSounds.BLOCK_COFFEE_BEAN_BLOCK_FALL
    );
    public static final SoundType GROUND_COFFEE_BLOCK = new ForgeSoundType(1.0F, 1.0F,
            ModSounds.BLOCK_GROUND_COFFEE_BREAK,
            () -> SoundEvents.ROOTED_DIRT_STEP,
            ModSounds.BLOCK_GROUND_COFFEE_PLACE,
            () -> SoundEvents.ROOTED_DIRT_HIT,
            () -> SoundEvents.ROOTED_DIRT_FALL
    );
}
