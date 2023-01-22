package net.fleischboss.createcogwars.block;

import net.fleischboss.createcogwars.createcogwars;
import net.fleischboss.createcogwars.item.ModCreativeModeTab;
import net.fleischboss.createcogwars.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, createcogwars.MOD_ID);


    public static final RegistryObject<Block> DURASTEEL_BLOCK = registerBlock("durasteel_block",
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
    public static final RegistryObject<Block> DURASTEEL_CASING = registerBlock("durasteel_casing",
            ()  -> new Block(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(4f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARCOGS);
    public static final RegistryObject<Block> CERAMISTEEL_BRICKS = registerBlock("ceramisteel_bricks",
            ()  -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(4f).requiresCorrectToolForDrops()), ModCreativeModeTab.STARCOGS);






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
