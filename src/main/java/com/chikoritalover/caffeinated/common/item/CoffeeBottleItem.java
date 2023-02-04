package com.chikoritalover.caffeinated.common.item;

import com.chikoritalover.caffeinated.core.registry.ModSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class CoffeeBottleItem extends Item {
    private static final int MAX_USE_TIME = 40;

    public CoffeeBottleItem(Properties properties) {
        super(properties);
    }

    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity user) {
        super.finishUsingItem(itemStack, level, user);
        if (user instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }
        if (!level.isClientSide()) {
            user.removeEffect(MobEffects.DIG_SLOWDOWN);
            user.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        }
        if (itemStack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        }
        else {
            if (user instanceof Player player && player.getAbilities().instabuild) {
                ItemStack glassBottle = new ItemStack(Items.GLASS_BOTTLE);
                if (!player.getInventory().add(glassBottle)) {
                    player.drop(glassBottle, false);
                }
            }
            return itemStack;
        }
    }

    public int getUseDuration(ItemStack itemStack) {
        return MAX_USE_TIME;
    }

    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return ModSounds.ITEM_COFFEE_BOTTLE_DRINK.get();
    }

    public SoundEvent getEatingSound() {
        return ModSounds.ITEM_COFFEE_BOTTLE_DRINK.get();
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }
}
