package net.fleischboss.createenergized;

import com.simibubi.create.content.contraptions.components.actors.DrillBlock;
import com.simibubi.create.content.contraptions.components.actors.DrillMovementBehaviour;
import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.fleischboss.createenergized.block.custom.LaserController;
import net.fleischboss.createenergized.item.ModCreativeModeTab;
import net.fleischboss.createenergized.rotational.components.lazers.EmitterBlock;
import net.fleischboss.createenergized.rotational.components.lazers.EmitterMovementBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;

import static com.simibubi.create.AllMovementBehaviours.movementBehaviour;
import static com.simibubi.create.Create.REGISTRATE;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class AllCEBlocks {
    public static final BlockEntry<EmitterBlock> LAZER_EMITTER = REGISTRATE
            .block("lazer_emitter", EmitterBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.color(MaterialColor.PODZOL))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.directionalBlockProvider(true))
            .transform(BlockStressDefaults.setImpact(4.0))
            .onRegister(movementBehaviour(new EmitterMovementBehaviour()))
            .item()
            .transform(customItemModel())
            .register();
}