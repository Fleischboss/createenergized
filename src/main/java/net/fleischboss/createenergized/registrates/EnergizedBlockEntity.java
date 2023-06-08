package net.fleischboss.createenergized.registrates;

import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;
import com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.fleischboss.createenergized.block.entity.AirLiquifierBlockEntity;

import static net.fleischboss.createenergized.CreateEnergized.REGISTRATE;

public class EnergizedBlockEntity {

    public static final BlockEntityEntry<AirLiquifierBlockEntity> AIR_LIQUIFIER = REGISTRATE
            .blockEntity("air_liquifier", AirLiquifierBlockEntity::new)
            .validBlocks(EnergizedBlock.AIR_LIQUIFIER)
            .renderer(()-> SmartBlockEntityRenderer::new)
            .register();

    public static void init() { }

}
