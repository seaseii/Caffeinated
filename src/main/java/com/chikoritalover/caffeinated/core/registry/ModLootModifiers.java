package com.chikoritalover.caffeinated.core.registry;

import com.chikoritalover.caffeinated.common.lootmodifier.AppendLootTableModifier;
import com.chikoritalover.caffeinated.core.Caffeinated;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Caffeinated.MODID);

    public static final Supplier<Codec<AppendLootTableModifier>> APPEND_LOOT_TABLE = LOOT_MODIFIERS.register("append_loot_table", () -> AppendLootTableModifier.CODEC);
}
