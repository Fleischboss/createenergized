package net.fleischboss.createenergized.registrates;

import com.simibubi.create.AllFluids;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.fleischboss.createenergized.CreateEnergized;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import static net.fleischboss.createenergized.CreateEnergized.REGISTRATE;

public class EnergizedFluid {


    public static final FluidEntry<ForgeFlowingFluid.Flowing> LIQUID_AIR =
            REGISTRATE.fluid("liquid_air", CreateEnergized.asResource("fluid/water_still"), CreateEnergized.asResource("fluid/water_flow"), NoColorFluidAttributes::new)
                    .lang("Liquid Air")
                    .properties(b -> b.viscosity(2000)
                            .density(1400))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .source(ForgeFlowingFluid.Source::new)
                    .bucket()
                    .build()
                    .register();

    public static void init() { }

    private static class NoColorFluidAttributes extends AllFluids.TintedFluidType {

        public NoColorFluidAttributes(Properties properties, ResourceLocation stillTexture,
                                      ResourceLocation flowingTexture) {
            super(properties, stillTexture, flowingTexture);
        }


        @Override
        protected int getTintColor(FluidStack stack) {
            return NO_TINT;
        }

        @Override
        public int getTintColor(FluidState state, BlockAndTintGetter world, BlockPos pos) {
            return 0x00ffffff;
        }
    }
}
