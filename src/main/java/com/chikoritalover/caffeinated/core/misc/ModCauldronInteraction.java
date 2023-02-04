package com.chikoritalover.caffeinated.core.misc;

import com.chikoritalover.caffeinated.core.registry.ModBlocks;
import com.chikoritalover.caffeinated.core.registry.ModItems;
import com.chikoritalover.caffeinated.core.registry.ModSounds;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;

public class ModCauldronInteraction {

    public static final Map<Item, CauldronInteraction> COFFEE = CauldronInteraction.newInteractionMap();
    public static final Map<Item, CauldronInteraction> GROUND_COFFEE = CauldronInteraction.newInteractionMap();

    public static void register() {
        CauldronInteraction.EMPTY.put(ModItems.COFFEE_BOTTLE.get(), (state, world, pos, player, hand, stack) -> {
            if (stack.getItem() != ModItems.COFFEE_BOTTLE.get()) {
                return InteractionResult.PASS;
            } else {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    world.setBlockAndUpdate(pos, ModBlocks.COFFEE_CAULDRON.get().defaultBlockState());
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            }
        });
        CauldronInteraction.WATER.put(ModItems.GROUND_COFFEE.get(), (state, world, pos, player, hand, stack) -> {
            if (stack.getItem() != ModItems.GROUND_COFFEE.get()) {
                return InteractionResult.PASS;
            } else {
                IntegerProperty level3 = BlockStateProperties.LEVEL_CAULDRON;
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Blocks.AIR)));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    world.setBlockAndUpdate(pos, ModBlocks.GROUND_COFFEE_CAULDRON.get().defaultBlockState().setValue(level3, state.getValue(level3)));
                    world.playSound(null, pos, ModSounds.ITEM_GROUND_COFFEE_SPLASH.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
        });
        COFFEE.put(Items.GLASS_BOTTLE, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(ModItems.COFFEE_BOTTLE.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                LayeredCauldronBlock.lowerFillLevel(state, world, pos);
                world.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });
        COFFEE.put(ModItems.COFFEE_BOTTLE.get(), (state, world, pos, player, hand, stack) -> {
            if (state.getValue(LayeredCauldronBlock.LEVEL) != 3 && stack.getItem() == ModItems.COFFEE_BOTTLE.get()) {
                if (!world.isClientSide) {
                    player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                    world.setBlockAndUpdate(pos, state.cycle(LayeredCauldronBlock.LEVEL));
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });
        GROUND_COFFEE.put(Items.GLASS_BOTTLE, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                LayeredCauldronBlock.lowerFillLevel(state, world, pos);
                world.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });
        GROUND_COFFEE.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.WATER_BUCKET)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });
        GROUND_COFFEE.put(Items.POTION, (state, world, pos, player, hand, stack) -> {
            if (state.getValue(LayeredCauldronBlock.LEVEL) != 3 && PotionUtils.getPotion(stack) == Potions.WATER) {
                if (!world.isClientSide) {
                    player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                    world.setBlockAndUpdate(pos, state.cycle(LayeredCauldronBlock.LEVEL));
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }
                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });
    }
}
