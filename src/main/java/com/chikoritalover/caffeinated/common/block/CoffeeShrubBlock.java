package com.chikoritalover.caffeinated.common.block;

import com.chikoritalover.caffeinated.core.registry.ModBlocks;
import com.chikoritalover.caffeinated.core.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoffeeShrubBlock extends BushBlock implements BonemealableBlock {
    public static final IntegerProperty AGE;
    private static final VoxelShape SMALL_SHAPE;
    private static final VoxelShape LARGE_SHAPE;

    public CoffeeShrubBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(ModItems.COFFEE_BERRIES.get());
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return state.getValue(AGE) < 2 ? SMALL_SHAPE : LARGE_SHAPE;
    }

    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        int i = state.getValue(AGE);
        BlockState aboveState = world.getBlockState(pos.above());
        if ((i < 3 || aboveState.getMaterial().isReplaceable()) && random.nextInt(5) == 0 && world.getRawBrightness(pos.above(), 0) >= 9) {
            performBonemeal(world, random, pos, state);
            BlockState state2 = world.getBlockState(pos);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state2));
        }

    }

    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        int i = state.getValue(AGE);
        BlockState aboveState = world.getBlockState(pos.above());
        boolean bl = i == 3 && !aboveState.getMaterial().isReplaceable();
        if (!bl && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else {
            return super.use(state, world, pos, player, hand, hit);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public boolean isValidBonemealTarget(BlockGetter world, BlockPos pos, BlockState state, boolean isClient) {
        int i = state.getValue(AGE);
        BlockState aboveState = world.getBlockState(pos.above());
        return i < 3 || aboveState.getMaterial().isReplaceable();
    }

    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        BlockState state2 = state.getValue(AGE) < 3 ? state.cycle(AGE) : ModBlocks.FLOWERING_COFFEE_SHRUB.get().defaultBlockState();
        world.setBlock(pos, state2, 2);
        if (state.getValue(AGE) == 3) {
            world.setBlock(pos.above(), state2.cycle(BlockStateProperties.DOUBLE_BLOCK_HALF), 2);
        }
    }

    static {
        AGE = BlockStateProperties.AGE_3;
        SMALL_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 11.0, 13.0);
        LARGE_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
    }
}
