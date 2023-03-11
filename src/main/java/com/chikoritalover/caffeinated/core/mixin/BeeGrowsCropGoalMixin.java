package com.chikoritalover.caffeinated.core.mixin;

import com.chikoritalover.caffeinated.common.block.CoffeeShrubBlock;
import com.chikoritalover.caffeinated.common.block.FloweringCoffeeShrubBlock;
import com.chikoritalover.caffeinated.core.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Bee.BeeGrowCropGoal.class)
public class BeeGrowsCropGoalMixin {

    @Final
    Bee this$0;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void tick(CallbackInfo ci, int i, BlockPos blockPos, BlockState blockState, Block block, boolean flag, IntegerProperty integerproperty) {
        Bee bee = this$0;
        boolean bl = false;

        if (block instanceof CoffeeShrubBlock) {
            bl = true;

            if (blockState.getValue(CoffeeShrubBlock.AGE) == 3) {
                bee.level.setBlockAndUpdate(blockPos, ModBlocks.FLOWERING_COFFEE_SHRUB.get().defaultBlockState());
                bee.level.setBlockAndUpdate(blockPos, ModBlocks.FLOWERING_COFFEE_SHRUB.get().defaultBlockState().cycle(FloweringCoffeeShrubBlock.HALF));
            }
            else {
                bee.level.setBlockAndUpdate(blockPos, blockState.cycle(CoffeeShrubBlock.AGE));
            }
        }
        else if (block instanceof FloweringCoffeeShrubBlock) {
            if (blockState.getValue(FloweringCoffeeShrubBlock.AGE) < 3) {
                bl = true;
                IntegerProperty intProperty = FloweringCoffeeShrubBlock.AGE;

                BlockState nextState = blockState.setValue(intProperty, blockState.getValue(intProperty) + 1);

                bee.level.setBlockAndUpdate(blockPos, nextState);
                bee.level.setBlockAndUpdate(nextState.getValue(FloweringCoffeeShrubBlock.HALF) == DoubleBlockHalf.LOWER ? blockPos.above() : blockPos.below(), blockState.cycle(FloweringCoffeeShrubBlock.HALF));
            }
        }

        if (bl) {
            bee.level.levelEvent(2005, blockPos, 0);
            bee.incrementNumCropsGrownSincePollination();
        }
    }
}
