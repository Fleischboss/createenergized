package net.fleischboss.createenergized.block.custom;

import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.foundation.block.IBE;
import net.fleischboss.createenergized.block.entity.AirLiquifierBlockEntity;
import net.fleischboss.createenergized.registrates.EnergizedBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;


public class AirLiquifier extends HorizontalDirectionalBlock implements IBE<AirLiquifierBlockEntity> {
    public AirLiquifier(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Class<AirLiquifierBlockEntity> getBlockEntityClass() {
        return AirLiquifierBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends AirLiquifierBlockEntity> getBlockEntityType() {
        return EnergizedBlockEntity.AIR_LIQUIFIER.get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(HORIZONTAL_FACING, context.getHorizontalDirection()
                        .getOpposite());
    }


}
