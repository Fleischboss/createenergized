package net.fleischboss.createenergized;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.fleischboss.createenergized.rotational.components.lazers.EmitterRenderer;
import net.fleischboss.createenergized.rotational.components.lazers.EmitterTileEntity;

import static com.simibubi.create.Create.REGISTRATE;


public class TileEntities {

    public static final BlockEntityEntry<EmitterTileEntity> EMITTER = REGISTRATE
            .blockEntity("emitter", EmitterTileEntity::new)
            //.instance(() -> EmitterTileEntity::new, false)
            .validBlocks(AllCEBlocks.LAZER_EMITTER)
            .renderer(() -> EmitterRenderer::new)
            .register();
}
