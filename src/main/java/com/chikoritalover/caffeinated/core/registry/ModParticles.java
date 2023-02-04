package com.chikoritalover.caffeinated.core.registry;

import com.chikoritalover.caffeinated.core.Caffeinated;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Caffeinated.MODID);

    public static final RegistryObject<SimpleParticleType> COFFEE_POP = PARTICLE_TYPES.register("coffee_pop", () -> new SimpleParticleType(false));
}
