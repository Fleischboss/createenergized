package net.fleischboss.createenergized.block.entity;

import net.fleischboss.createenergized.CreateEnergized;
import net.fleischboss.createenergized.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CreateEnergized.MOD_ID);

    public static final RegistryObject<BlockEntityType<LaserControllerEntity>> LASER_CONTROLLER = BLOCK_ENTITIES.register("laser_controller_entity",
            () -> BlockEntityType.Builder.of(LaserControllerEntity::new, ModBlocks.LASER_CONTROLLER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
