package net.fleischboss.createenergized.block.entity;

import net.fleischboss.createenergized.block.custom.LaserController;
import net.fleischboss.createenergized.particle.LaserParticleData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LaserControllerEntity extends BlockEntity {
    public LaserControllerEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.LASER_CONTROLLER.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, LaserControllerEntity entity) {
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            for (ServerPlayer serverPlayer : serverLevel.players()) {
                serverLevel.sendParticles(serverPlayer, new LaserParticleData(state.getValue(LaserController.FACING), 100, 0.3f), true, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, 1, 0, 0, 0, 0);
            }
        }
    }
}
