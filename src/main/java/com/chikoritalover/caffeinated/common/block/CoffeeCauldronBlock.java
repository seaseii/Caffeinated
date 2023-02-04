package com.chikoritalover.caffeinated.common.block;

import com.chikoritalover.caffeinated.core.misc.ModTags;
import com.chikoritalover.caffeinated.core.registry.ModParticles;
import com.chikoritalover.caffeinated.core.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.HitResult;

import java.util.Map;

public class CoffeeCauldronBlock extends LayeredCauldronBlock {
    public CoffeeCauldronBlock(Properties settings, Map<Item, CauldronInteraction> behaviorMap) {
        super(settings, null, behaviorMap);
    }

    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(Blocks.CAULDRON);
    }

    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (isLitFireInRange(world, pos)) {
            int level = state.getValue(LEVEL);

            double d = pos.getX() + random.nextDouble() * 0.5 + 0.25;
            double e = pos.getY() + 0.375 + level * 0.1875;
            double f = pos.getZ() + random.nextDouble() * 0.5 + 0.25;
            if (random.nextDouble() < 0.15) {
                world.playLocalSound(d, e, f, ModSounds.BLOCK_CAULDRON_BUBBLE.get(), SoundSource.BLOCKS, 0.2F, 2.5F - level * 0.5F, true);
            }

            world.addParticle(getPopParticleEffect(), d, e, f, 0.0, 0.0, 0.0);
        }
    }

    public static boolean isLitFireInRange(Level world, BlockPos pos) {
        BlockPos blockPos = pos.below();
        BlockState blockState = world.getBlockState(blockPos);

        boolean bl = blockState.is(ModTags.LIT_FIRES);

        if (blockState.hasProperty(BlockStateProperties.LIT)) {
            return bl && blockState.getValue(BlockStateProperties.LIT);
        } else {
            return bl;
        }
    }

    public ParticleOptions getPopParticleEffect() {
        return ModParticles.COFFEE_POP.get();
    }
}
