package net.fleischboss.createenergized.item;

import net.fleischboss.createenergized.particle.LaserParticleData;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DebugLaserItem extends Item {
    public DebugLaserItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            for (ServerPlayer serverPlayer : serverLevel.players()) {
                serverLevel.sendParticles(serverPlayer, new LaserParticleData(Direction.NORTH, 100, 0.5f), true, player.getX(), player.getY(), player.getZ(), 1, 0, 0, 0, 0);
            }
        }
        return super.use(level, player, hand);
    }
}
