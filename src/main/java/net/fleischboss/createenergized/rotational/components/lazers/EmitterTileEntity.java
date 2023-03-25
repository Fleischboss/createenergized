package net.fleischboss.createenergized.rotational.components.lazers;

import com.simibubi.create.content.contraptions.components.actors.BlockBreakingKineticTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class EmitterTileEntity extends BlockBreakingKineticTileEntity {

	public EmitterTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	protected BlockPos getBreakingPos() {
		return getBlockPos().relative(getBlockState().getValue(EmitterBlock.FACING));
	}

}
