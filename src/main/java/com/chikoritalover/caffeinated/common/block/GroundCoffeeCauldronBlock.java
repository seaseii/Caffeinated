package com.chikoritalover.caffeinated.common.block;

import com.chikoritalover.caffeinated.core.misc.ModCauldronInteraction;
import com.chikoritalover.caffeinated.core.registry.ModBlocks;
import com.chikoritalover.caffeinated.core.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class GroundCoffeeCauldronBlock extends CoffeeCauldronBlock {
    public GroundCoffeeCauldronBlock(Properties settings) {
        super(settings, ModCauldronInteraction.GROUND_COFFEE);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean notify) {
        if (isLitFireInRange(world, pos)) {
            world.scheduleTick(pos, ModBlocks.GROUND_COFFEE_CAULDRON.get(), 200);
        }
    }

    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (isLitFireInRange(world, pos)) {
            world.scheduleTick(pos, ModBlocks.GROUND_COFFEE_CAULDRON.get(), 200);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (isLitFireInRange(world, pos)) {
            BlockState blockState = ModBlocks.COFFEE_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL));

            world.setBlockAndUpdate(pos, blockState);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockState));

            world.playSound(null, pos, ModSounds.BLOCK_CAULDRON_BREW.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    public ParticleOptions getPopParticleEffect() {
        return ParticleTypes.BUBBLE_POP;
    }
}
