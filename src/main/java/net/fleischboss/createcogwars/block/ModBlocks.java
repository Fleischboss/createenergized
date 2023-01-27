package net.fleischboss.createcogwars.block;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.fleischboss.createcogwars.createcogwars;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import net.fleischboss.createcogwars.item.ModCreativeModeTab;
import net.fleischboss.createcogwars.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import static com.simibubi.create.AllInteractionBehaviours.interactionBehaviour;
import static com.simibubi.create.AllMovementBehaviours.movementBehaviour;
import static com.simibubi.create.Create.REGISTRATE;
import static com.simibubi.create.content.AllSections.SCHEMATICS;
import static com.simibubi.create.content.logistics.block.display.AllDisplayBehaviours.assignDataBehaviour;
import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;
import static com.simibubi.create.foundation.data.BlockStateGen.simpleCubeAll;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOnly;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static com.simibubi.create.foundation.data.TagGen.tagBlockAndItem;

import com.simibubi.create.AllTags.AllBlockTags;
import com.simibubi.create.AllTags.AllItemTags;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.content.contraptions.components.AssemblyOperatorBlockItem;
import com.simibubi.create.content.contraptions.components.actors.BellMovementBehaviour;
import com.simibubi.create.content.contraptions.components.actors.DrillBlock;
import com.simibubi.create.content.contraptions.components.actors.DrillMovementBehaviour;
import com.simibubi.create.content.contraptions.components.actors.HarvesterBlock;
import com.simibubi.create.content.contraptions.components.actors.HarvesterMovementBehaviour;
import com.simibubi.create.content.contraptions.components.actors.PloughBlock;
import com.simibubi.create.content.contraptions.components.actors.PloughMovementBehaviour;
import com.simibubi.create.content.contraptions.components.actors.PortableStorageInterfaceBlock;
import com.simibubi.create.content.contraptions.components.actors.PortableStorageInterfaceMovement;
import com.simibubi.create.content.contraptions.components.actors.SawMovementBehaviour;
import com.simibubi.create.content.contraptions.components.actors.SeatBlock;
import com.simibubi.create.content.contraptions.components.actors.SeatInteractionBehaviour;
import com.simibubi.create.content.contraptions.components.actors.SeatMovementBehaviour;
import com.simibubi.create.content.contraptions.components.clock.CuckooClockBlock;
import com.simibubi.create.content.contraptions.components.crafter.CrafterCTBehaviour;
import com.simibubi.create.content.contraptions.components.crafter.MechanicalCrafterBlock;
import com.simibubi.create.content.contraptions.components.crank.HandCrankBlock;
import com.simibubi.create.content.contraptions.components.crank.ValveHandleBlock;
import com.simibubi.create.content.contraptions.components.crusher.CrushingWheelBlock;
import com.simibubi.create.content.contraptions.components.crusher.CrushingWheelControllerBlock;
import com.simibubi.create.content.contraptions.components.deployer.DeployerBlock;
import com.simibubi.create.content.contraptions.components.deployer.DeployerMovementBehaviour;
import com.simibubi.create.content.contraptions.components.deployer.DeployerMovingInteraction;
import com.simibubi.create.content.contraptions.components.fan.EncasedFanBlock;
import com.simibubi.create.content.contraptions.components.fan.NozzleBlock;
import com.simibubi.create.content.contraptions.components.flywheel.FlywheelBlock;
import com.simibubi.create.content.contraptions.components.millstone.MillstoneBlock;
import com.simibubi.create.content.contraptions.components.mixer.MechanicalMixerBlock;
import com.simibubi.create.content.contraptions.components.motor.CreativeMotorBlock;
import com.simibubi.create.content.contraptions.components.motor.CreativeMotorGenerator;
import com.simibubi.create.content.contraptions.components.press.MechanicalPressBlock;
import com.simibubi.create.content.contraptions.components.saw.SawBlock;
import com.simibubi.create.content.contraptions.components.saw.SawGenerator;
import com.simibubi.create.content.contraptions.components.steam.PoweredShaftBlock;
import com.simibubi.create.content.contraptions.components.steam.SteamEngineBlock;
import com.simibubi.create.content.contraptions.components.steam.whistle.WhistleBlock;
import com.simibubi.create.content.contraptions.components.steam.whistle.WhistleExtenderBlock;
import com.simibubi.create.content.contraptions.components.steam.whistle.WhistleGenerator;
import com.simibubi.create.content.contraptions.components.structureMovement.bearing.BlankSailBlockItem;
import com.simibubi.create.content.contraptions.components.structureMovement.bearing.ClockworkBearingBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.bearing.MechanicalBearingBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.bearing.SailBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.bearing.StabilizedBearingMovementBehaviour;
import com.simibubi.create.content.contraptions.components.structureMovement.bearing.WindmillBearingBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.chassis.LinearChassisBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.chassis.LinearChassisBlock.ChassisCTBehaviour;
import com.simibubi.create.content.contraptions.components.structureMovement.chassis.RadialChassisBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.chassis.StickerBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.gantry.GantryCarriageBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.interaction.controls.ControlsBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.interaction.controls.ControlsInteractionBehaviour;
import com.simibubi.create.content.contraptions.components.structureMovement.interaction.controls.ControlsMovementBehaviour;
import com.simibubi.create.content.contraptions.components.structureMovement.mounted.CartAssemblerBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.mounted.CartAssemblerBlock.MinecartAnchorBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.mounted.CartAssemblerBlockItem;
import com.simibubi.create.content.contraptions.components.structureMovement.piston.MechanicalPistonBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.piston.MechanicalPistonHeadBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.piston.PistonExtensionPoleBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.pulley.PulleyBlock;
import com.simibubi.create.content.contraptions.components.tracks.ControllerRailBlock;
import com.simibubi.create.content.contraptions.components.tracks.ControllerRailGenerator;
import com.simibubi.create.content.contraptions.components.turntable.TurntableBlock;
import com.simibubi.create.content.contraptions.components.waterwheel.WaterWheelBlock;
import com.simibubi.create.content.contraptions.fluids.PipeAttachmentModel;
import com.simibubi.create.content.contraptions.fluids.PumpBlock;
import com.simibubi.create.content.contraptions.fluids.actors.HosePulleyBlock;
import com.simibubi.create.content.contraptions.fluids.actors.ItemDrainBlock;
import com.simibubi.create.content.contraptions.fluids.actors.SpoutBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.BracketBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.BracketBlockItem;
import com.simibubi.create.content.contraptions.fluids.pipes.BracketGenerator;
import com.simibubi.create.content.contraptions.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.FluidValveBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.GlassFluidPipeBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.SmartFluidPipeBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.SmartFluidPipeGenerator;
import com.simibubi.create.content.contraptions.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.contraptions.fluids.tank.FluidTankGenerator;
import com.simibubi.create.content.contraptions.fluids.tank.FluidTankItem;
import com.simibubi.create.content.contraptions.fluids.tank.FluidTankModel;
import com.simibubi.create.content.contraptions.processing.BasinBlock;
import com.simibubi.create.content.contraptions.processing.BasinGenerator;
import com.simibubi.create.content.contraptions.processing.BasinMovementBehaviour;
import com.simibubi.create.content.contraptions.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.contraptions.processing.burner.BlazeBurnerBlockItem;
import com.simibubi.create.content.contraptions.processing.burner.BlazeBurnerInteractionBehaviour;
import com.simibubi.create.content.contraptions.processing.burner.BlazeBurnerMovementBehaviour;
import com.simibubi.create.content.contraptions.processing.burner.LitBlazeBurnerBlock;
import com.simibubi.create.content.contraptions.relays.advanced.GantryShaftBlock;
import com.simibubi.create.content.contraptions.relays.advanced.SpeedControllerBlock;
import com.simibubi.create.content.contraptions.relays.advanced.sequencer.SequencedGearshiftBlock;
import com.simibubi.create.content.contraptions.relays.advanced.sequencer.SequencedGearshiftGenerator;
import com.simibubi.create.content.contraptions.relays.belt.BeltBlock;
import com.simibubi.create.content.contraptions.relays.belt.BeltGenerator;
import com.simibubi.create.content.contraptions.relays.belt.BeltModel;
import com.simibubi.create.content.contraptions.relays.elementary.BracketedKineticBlockModel;
import com.simibubi.create.content.contraptions.relays.elementary.CogWheelBlock;
import com.simibubi.create.content.contraptions.relays.elementary.CogwheelBlockItem;
import com.simibubi.create.content.contraptions.relays.elementary.ShaftBlock;
import com.simibubi.create.content.contraptions.relays.encased.AdjustablePulleyBlock;
import com.simibubi.create.content.contraptions.relays.encased.ClutchBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedBeltBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedBeltGenerator;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCTBehaviour;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedShaftBlock;
import com.simibubi.create.content.contraptions.relays.encased.GearshiftBlock;
import com.simibubi.create.content.contraptions.relays.gauge.GaugeBlock;
import com.simibubi.create.content.contraptions.relays.gauge.GaugeGenerator;
import com.simibubi.create.content.contraptions.relays.gearbox.GearboxBlock;
import com.simibubi.create.content.curiosities.armor.CopperBacktankBlock;
import com.simibubi.create.content.curiosities.bell.HauntedBellBlock;
import com.simibubi.create.content.curiosities.bell.HauntedBellMovementBehaviour;
import com.simibubi.create.content.curiosities.bell.PeculiarBellBlock;
import com.simibubi.create.content.curiosities.deco.MetalLadderBlock;
import com.simibubi.create.content.curiosities.deco.PlacardBlock;
import com.simibubi.create.content.curiosities.deco.SlidingDoorBlock;
import com.simibubi.create.content.curiosities.deco.TrainTrapdoorBlock;
import com.simibubi.create.content.curiosities.deco.TrapdoorCTBehaviour;
import com.simibubi.create.content.curiosities.girder.ConnectedGirderModel;
import com.simibubi.create.content.curiosities.girder.GirderBlock;
import com.simibubi.create.content.curiosities.girder.GirderBlockStateGenerator;
import com.simibubi.create.content.curiosities.girder.GirderEncasedShaftBlock;
import com.simibubi.create.content.curiosities.toolbox.ToolboxBlock;
import com.simibubi.create.content.logistics.block.belts.tunnel.BeltTunnelBlock;
import com.simibubi.create.content.logistics.block.belts.tunnel.BrassTunnelBlock;
import com.simibubi.create.content.logistics.block.belts.tunnel.BrassTunnelCTBehaviour;
import com.simibubi.create.content.logistics.block.chute.ChuteBlock;
import com.simibubi.create.content.logistics.block.chute.ChuteGenerator;
import com.simibubi.create.content.logistics.block.chute.ChuteItem;
import com.simibubi.create.content.logistics.block.chute.SmartChuteBlock;
import com.simibubi.create.content.logistics.block.depot.DepotBlock;
import com.simibubi.create.content.logistics.block.depot.EjectorBlock;
import com.simibubi.create.content.logistics.block.depot.EjectorItem;
import com.simibubi.create.content.logistics.block.diodes.AbstractDiodeGenerator;
import com.simibubi.create.content.logistics.block.diodes.BrassDiodeBlock;
import com.simibubi.create.content.logistics.block.diodes.BrassDiodeGenerator;
import com.simibubi.create.content.logistics.block.diodes.PoweredLatchBlock;
import com.simibubi.create.content.logistics.block.diodes.PoweredLatchGenerator;
import com.simibubi.create.content.logistics.block.diodes.ToggleLatchBlock;
import com.simibubi.create.content.logistics.block.diodes.ToggleLatchGenerator;
import com.simibubi.create.content.logistics.block.display.DisplayLinkBlock;
import com.simibubi.create.content.logistics.block.display.DisplayLinkBlockItem;
import com.simibubi.create.content.logistics.block.display.source.AccumulatedItemCountDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.BoilerDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.EntityNameDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.FillLevelDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.FluidAmountDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.FluidListDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.ItemCountDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.ItemListDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.ItemNameDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.ItemThroughputDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.KineticSpeedDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.KineticStressDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.ObservedTrainNameSource;
import com.simibubi.create.content.logistics.block.display.source.StationSummaryDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.StopWatchDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.TimeOfDayDisplaySource;
import com.simibubi.create.content.logistics.block.display.source.TrainStatusDisplaySource;
import com.simibubi.create.content.logistics.block.display.target.DisplayBoardTarget;
import com.simibubi.create.content.logistics.block.funnel.AndesiteFunnelBlock;
import com.simibubi.create.content.logistics.block.funnel.BeltFunnelBlock;
import com.simibubi.create.content.logistics.block.funnel.BeltFunnelGenerator;
import com.simibubi.create.content.logistics.block.funnel.BrassFunnelBlock;
import com.simibubi.create.content.logistics.block.funnel.FunnelGenerator;
import com.simibubi.create.content.logistics.block.funnel.FunnelItem;
import com.simibubi.create.content.logistics.block.funnel.FunnelMovementBehaviour;
import com.simibubi.create.content.logistics.block.inventories.CreativeCrateBlock;
import com.simibubi.create.content.logistics.block.mechanicalArm.ArmBlock;
import com.simibubi.create.content.logistics.block.mechanicalArm.ArmItem;
import com.simibubi.create.content.logistics.block.redstone.AnalogLeverBlock;
import com.simibubi.create.content.logistics.block.redstone.ContactMovementBehaviour;
import com.simibubi.create.content.logistics.block.redstone.ContentObserverBlock;
import com.simibubi.create.content.logistics.block.redstone.NixieTubeBlock;
import com.simibubi.create.content.logistics.block.redstone.NixieTubeGenerator;
import com.simibubi.create.content.logistics.block.redstone.RedstoneContactBlock;
import com.simibubi.create.content.logistics.block.redstone.RedstoneLinkBlock;
import com.simibubi.create.content.logistics.block.redstone.RedstoneLinkGenerator;
import com.simibubi.create.content.logistics.block.redstone.RoseQuartzLampBlock;
import com.simibubi.create.content.logistics.block.redstone.StockpileSwitchBlock;
import com.simibubi.create.content.logistics.block.vault.ItemVaultBlock;
import com.simibubi.create.content.logistics.block.vault.ItemVaultCTBehaviour;
import com.simibubi.create.content.logistics.block.vault.ItemVaultItem;
import com.simibubi.create.content.logistics.item.LecternControllerBlock;
import com.simibubi.create.content.logistics.trains.management.display.FlapDisplayBlock;
import com.simibubi.create.content.logistics.trains.management.edgePoint.EdgePointType;
import com.simibubi.create.content.logistics.trains.management.edgePoint.TrackTargetingBlockItem;
import com.simibubi.create.content.logistics.trains.management.edgePoint.observer.TrackObserverBlock;
import com.simibubi.create.content.logistics.trains.management.edgePoint.signal.SignalBlock;
import com.simibubi.create.content.logistics.trains.management.edgePoint.station.StationBlock;
import com.simibubi.create.content.logistics.trains.track.FakeTrackBlock;
import com.simibubi.create.content.logistics.trains.track.StandardBogeyBlock;
import com.simibubi.create.content.logistics.trains.track.TrackBlock;
import com.simibubi.create.content.logistics.trains.track.TrackBlockItem;
import com.simibubi.create.content.logistics.trains.track.TrackBlockStateGenerator;
import com.simibubi.create.content.schematics.block.SchematicTableBlock;
import com.simibubi.create.content.schematics.block.SchematicannonBlock;
import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.block.CopperBlockSet;
import com.simibubi.create.foundation.block.DyedBlockList;
import com.simibubi.create.foundation.block.ItemUseOverrides;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.ModelGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.UncontainableBlockItem;
import com.simibubi.create.foundation.utility.ColorHandlers;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.foundation.utility.DyeHelper;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.PistonType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;


import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, createcogwars.MOD_ID);

//Basic stuff
    public static final RegistryObject<Block> DURASTEEL_BLOCK = registerBlock("durasteel_block",
            ()  -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARCOGS);
    public static final RegistryObject<Block> DURASTEEL_CASING = registerBlock("durasteel_casing",
            ()  -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARCOGS);
    public static final RegistryObject<Block> ZERSIUM_BLOCK = registerBlock("zersium_block",
            ()  -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARCOGS);
    public static final RegistryObject<Block> ZERSIUM_CRYSTAL_BLOCK = registerBlock("zersium_crystal_block",
            ()  -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARCOGS);
    public static final RegistryObject<Block> DURASTEEL_PLATING = registerBlock("durasteel_plating",
            ()  -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(15f).explosionResistance(100f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARCOGS);


    public static final RegistryObject<Block> CERAMISTEEL_BRICKS = registerBlock("ceramisteel_bricks",
            ()  -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(4f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARCOGS);
//Lasers
public static final RegistryObject<Block> LASER_CONTROLLER = registerBlock("laser_controller",
        ()  -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARCOGS);

    public static final RegistryObject<Block> LASER_BARREL = registerBlock("laser_barrel",
            ()  -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARCOGS);

    public static final RegistryObject<Block> LASER_COOLANT_INPUT = registerBlock("laser_coolant_Input",
            ()  -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARCOGS);





    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
    RegistryObject<T> toReturn = BLOCKS.register(name, block);
    registerBlockItem(name, toReturn, tab);
   return toReturn;
}





private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
CreativeModeTab tab) {
    return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties().tab(tab)));
}



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
