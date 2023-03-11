package com.chikoritalover.caffeinated.common.lootmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class AppendLootTableModifier extends LootModifier {
    private final ResourceLocation injectedLoot;

    public static final Codec<AppendLootTableModifier> CODEC = RecordCodecBuilder.create(instance -> codecStart(instance).and(ResourceLocation.CODEC.fieldOf("injected_loot").forGetter(addLootTableModifier -> addLootTableModifier.injectedLoot)).apply(instance, AppendLootTableModifier::new));

    protected AppendLootTableModifier(LootItemCondition[] conditionsIn, ResourceLocation injectedLoot) {
        super(conditionsIn);
        this.injectedLoot = injectedLoot;
    }

    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        LootTable lootTable = context.getLootTable(injectedLoot);
        lootTable.getRandomItems(context, generatedLoot::add);
        return generatedLoot;
    }

    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
