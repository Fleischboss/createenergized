package net.fleischboss.createenergized.rotational.components.lazers;

import com.jozufozu.flywheel.core.virtual.VirtualRenderWorld;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.render.ContraptionMatrices;
import com.simibubi.create.content.contraptions.render.ContraptionRenderDispatcher;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AngleHelper;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class EmitterRenderer extends KineticBlockEntityRenderer<EmitterTileEntity> {

	public EmitterRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	protected SuperByteBuffer getRotatedModel(EmitterTileEntity te, BlockState state) {
		return CachedBufferer.partialFacing(AllPartialModels.DRILL_HEAD, state);
	}

	public static void renderInContraption(MovementContext context, VirtualRenderWorld renderWorld,
										   ContraptionMatrices matrices, MultiBufferSource buffer) {
		BlockState state = context.state;
		SuperByteBuffer superBuffer = CachedBufferer.partial(AllPartialModels.DRILL_HEAD, state);
		Direction facing = state.getValue(EmitterBlock.FACING);

		float speed = (float) (context.contraption.stalled
				|| !VecHelper.isVecPointingTowards(context.relativeMotion, facing
				.getOpposite()) ? context.getAnimationSpeed() : 0);
		float time = AnimationTickHolder.getRenderTime() / 20;
		float angle = (float) (((time * speed) % 360));

		superBuffer
			.transform(matrices.getModel())
			.centre()
			.rotateY(AngleHelper.horizontalAngle(facing))
			.rotateX(AngleHelper.verticalAngle(facing))
			.rotateZ(angle)
			.unCentre()
			.light(matrices.getWorld(),
					ContraptionRenderDispatcher.getContraptionWorldLight(context, renderWorld))
			.renderInto(matrices.getViewProjection(), buffer.getBuffer(RenderType.solid()));
	}

}
