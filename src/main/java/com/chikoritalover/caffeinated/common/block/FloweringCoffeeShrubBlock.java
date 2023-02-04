package com.chikoritalover.caffeinated.common.block;

import com.chikoritalover.caffeinated.core.registry.ModItems;
import com.chikoritalover.caffeinated.core.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class FloweringCoffeeShrubBlock extends DoublePlantBlock implements BonemealableBlock {
    public static final IntegerProperty AGE;

    public FloweringCoffeeShrubBlock(Properties settings) {
        super(settings);
    }

    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(ModItems.COFFEE_BERRIES.get());
    }

    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        int i = state.getValue(AGE);
        if (i < 3 && random.nextInt(11) == 0 && world.getRawBrightness(pos.above(), 0) >= 9) {
            performBonemeal(world, random, pos, state);
            BlockState blockState = world.getBlockState(pos);

            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockState));
            if (blockState.getValue(HALF) == DoubleBlockHalf.LOWER) {
                world.setBlock(pos.above(), blockState.cycle(HALF), 2);
                world.gameEvent(GameEvent.BLOCK_CHANGE, pos.above(), GameEvent.Context.of(blockState.cycle(HALF)));
            } else {
                world.setBlock(pos.below(), blockState.cycle(HALF), 2);
                world.gameEvent(GameEvent.BLOCK_CHANGE, pos.below(), GameEvent.Context.of(blockState.cycle(HALF)));
            }
        }

    }

    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        int i = state.getValue(AGE);
        boolean bl = i == 3;
        if (!bl && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i == 3) {
            int j = 1 + world.random.nextInt(2);
            popResource(world, pos, new ItemStack(ModItems.COFFEE_BERRIES.get(), j + (bl ? 1 : 0)));
            world.playSound(null, pos, ModSounds.BLOCK_COFFEE_SHRUB_PICK_BERRIES.get(), SoundSource.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            BlockState blockState = state.setValue(AGE, 0);

            world.setBlock(pos, blockState, 2);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
            if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
                world.setBlock(pos.above(), blockState.cycle(HALF), 2);
                world.gameEvent(GameEvent.BLOCK_CHANGE, pos.above(), GameEvent.Context.of(player, blockState.cycle(HALF)));
            } else {
                world.setBlock(pos.below(), blockState.cycle(HALF), 2);
                world.gameEvent(GameEvent.BLOCK_CHANGE, pos.below(), GameEvent.Context.of(player, blockState.cycle(HALF)));
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        } else {
            return super.use(state, world, pos, player, hand, hit);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos pos, BlockState state, boolean isClient) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        int i = Math.min(3, state.getValue(AGE) + 1);
        world.setBlock(pos, state.setValue(AGE, i), 2);
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            world.setBlock(pos.above(), state.setValue(AGE, i).cycle(HALF), 2);
        } else {
            world.setBlock(pos.below(), state.setValue(AGE, i).cycle(HALF), 2);
        }
    }

    static {
        AGE = BlockStateProperties.AGE_3;
    }
}
