package com.chikoritalover.caffeinated.core.registry;

import com.chikoritalover.caffeinated.core.Caffeinated;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModBannerPattern {

    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registry.BANNER_PATTERN_REGISTRY, Caffeinated.MODID);

    public static final RegistryObject<BannerPattern> JAVA = BANNER_PATTERNS.register("java", () -> new BannerPattern("jav"));
}
