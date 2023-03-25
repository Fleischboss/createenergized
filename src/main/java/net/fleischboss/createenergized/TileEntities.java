package net.fleischboss.createenergized;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.components.actors.DrillInstance;
import com.simibubi.create.content.contraptions.components.actors.DrillRenderer;
import com.simibubi.create.content.contraptions.components.actors.DrillTileEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.fleischboss.createenergized.rotational.components.lazers.EmitterRenderer;
import net.fleischboss.createenergized.rotational.components.lazers.EmitterTileEntity;

import static com.simibubi.create.Create.REGISTRATE;

public class TileEntities {
    public static final BlockEntityEntry<EmitterTileEntity> EMITTER = REGISTRATE
            .tileEntity("emitter", EmitterTileEntity::new)
            .instance(() -> EmitterTileEntity::new, false)
            .validBlocks(AllCEBlocks.LAZER_EMITTER)
            .renderer(() -> EmitterRenderer::new)
            .register();

}
