package com.chikoritalover.caffeinated.core.registry;

import com.chikoritalover.caffeinated.core.Caffeinated;
import com.chikoritalover.caffeinated.common.effect.CaffeineMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Caffeinated.MODID);

    public static final RegistryObject<MobEffect> CAFFEINE = MOB_EFFECTS.register("caffeine", CaffeineMobEffect::new);
}
