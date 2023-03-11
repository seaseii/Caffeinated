package com.chikoritalover.caffeinated.core.misc;

import com.chikoritalover.caffeinated.core.registry.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties COFFEE_BERRIES = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties COFFEE_BOTTLE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).effect(() -> new MobEffectInstance(ModEffects.CAFFEINE.get(), 3600, 0), 1.0F).build();
    public static final FoodProperties TIRAMISU = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).effect(() -> new MobEffectInstance(ModEffects.CAFFEINE.get(), 600, 1), 1.0F).build();
}
