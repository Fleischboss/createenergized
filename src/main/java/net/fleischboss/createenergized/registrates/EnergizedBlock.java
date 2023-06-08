package net.fleischboss.createenergized.registrates;

import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.fleischboss.createenergized.block.custom.AirLiquifier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static net.fleischboss.createenergized.CreateEnergized.REGISTRATE;

public class EnergizedBlock {


    public static final BlockEntry<AirLiquifier> AIR_LIQUIFIER = REGISTRATE
            .block("air_liquifier", AirLiquifier::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(BlockBehaviour.Properties::noOcclusion)
            .blockstate(BlockStateGen.horizontalBlockProvider(false))
            .addLayer(() -> RenderType::cutoutMipped)
            .simpleItem()
            .register();

    public static void init() { }

}
