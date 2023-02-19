package net.fleischboss.createenergized.item;

import net.fleischboss.createenergized.block.custom.LaserController;
import net.fleischboss.createenergized.particle.LaserParticleData;
import net.fleischboss.createenergized.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlasterItem extends Item {
    public BlasterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            for (ServerPlayer serverPlayer : serverLevel.players()) {
                serverLevel.sendParticles(serverPlayer, new LaserParticleData(Direction.NORTH, 20, 0.2f), true, player.getX(), player.getY(1), player.getZ(-2), 1, 0, 0, 0, 0);


            }
        }
        return super.use(level, player, hand);
    }
}