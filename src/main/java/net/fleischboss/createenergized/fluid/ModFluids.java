package net.fleischboss.createenergized.fluid;

import net.fleischboss.createenergized.CreateEnergized;
import net.fleischboss.createenergized.block.ModBlocks;
import net.fleischboss.createenergized.item.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, CreateEnergized.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_NITROGEN = FLUIDS.register("nitrogen_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.NITROGEN_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_NITROGEN = FLUIDS.register("flowing_nitrogen",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.NITROGEN_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties NITROGEN_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.NITROGEN_FLUID_TYPE, SOURCE_NITROGEN, FLOWING_NITROGEN)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.NITROGEN_BLOCK)
            .bucket(ModItems.NITROGEN_BUCKET);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
