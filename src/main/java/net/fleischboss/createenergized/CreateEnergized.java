package net.fleischboss.createenergized;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.fleischboss.createenergized.block.ModBlocks;
import net.fleischboss.createenergized.block.entity.BlockEntityRegistry;
import net.fleischboss.createenergized.client.particle.LaserParticle;
import net.fleischboss.createenergized.fluid.ModFluidTypes;
import net.fleischboss.createenergized.fluid.ModFluids;
import net.fleischboss.createenergized.item.ModCreativeModeTab;
import net.fleischboss.createenergized.item.ModItems;
import net.fleischboss.createenergized.particle.ParticleRegistry;
import net.fleischboss.createenergized.world.feature.ModConfiguredFeatures;
import net.fleischboss.createenergized.world.feature.ModPlacedFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CreateEnergized.MOD_ID)
public class CreateEnergized
{
    public static final String MOD_ID = "createenergized";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final NonNullSupplier<Registrate> REGISTRATE = NonNullSupplier.lazy(() -> Registrate.create(MOD_ID).creativeModeTab(() -> ModCreativeModeTab.ENERGIZED));

    public CreateEnergized()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        BlockEntityRegistry.register(modEventBus);
        ParticleRegistry.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }

        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
            event.register(ParticleRegistry.LASER.get(), LaserParticle.Factory::new);
        }
    }
}
