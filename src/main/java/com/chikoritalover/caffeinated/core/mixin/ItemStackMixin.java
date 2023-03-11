package com.chikoritalover.caffeinated.core.mixin;

import com.chikoritalover.caffeinated.core.misc.ModTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(method = "finishUsingItem", at = @At("HEAD"))
    private void finishUsingItem(Level level, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack itemStack = ItemStack.class.cast(this);

        if (itemStack.is(ModTags.COFFEE_FOOD) && user.getType().is(ModTags.COFFEE_INFLICTS_POISON)) {
            user.addEffect(new MobEffectInstance(MobEffects.POISON, 900));
        }
    }
}
