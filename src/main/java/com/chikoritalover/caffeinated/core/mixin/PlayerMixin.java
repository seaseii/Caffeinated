package com.chikoritalover.caffeinated.core.mixin;

import com.chikoritalover.caffeinated.core.registry.ModEffects;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {

    @Inject(method = "causeFoodExhaustion", at = @At("HEAD"), cancellable = true)
    private void reduceFoodExhaustion(float amount, CallbackInfo ci) {
        Player player = Player.class.cast(this);
        if (player.hasEffect(ModEffects.CAFFEINE.get())) {
            int amplifier = player.getEffect(ModEffects.CAFFEINE.get()).getAmplifier();
            if (player.getRandom().nextInt(amplifier + 1) > 1) {
                ci.cancel();
            }
        }
    }
}
