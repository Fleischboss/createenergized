package net.fleischboss.createcogwars.item;

import net.fleischboss.createcogwars.createcogwars;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, createcogwars.MOD_ID);
    public static final RegistryObject<Item> ZERSIUM_CRYSTAL = ITEMS.register("zersium_crystal",
        () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STARCOGS)));

    public static final RegistryObject<Item> ZERSIUM_SEED = ITEMS.register("zersium_seed",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STARCOGS)));

    public static final RegistryObject<Item> ZERSIUM_DUST = ITEMS.register("zersium_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STARCOGS)));

    public static final RegistryObject<Item> DURASTEEL_INGOT = ITEMS.register("durasteel_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STARCOGS)));

    public static final RegistryObject<Item> DURASTEEL_COMPOUND = ITEMS.register("durasteel_compound",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STARCOGS)));


    public static final RegistryObject<Item> CERAMISTEEL_COMPOUND = ITEMS.register("ceramisteel_compound",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STARCOGS)));

    public static final RegistryObject<Item> INCOMPLETE_CERAMISTEEL_COMPOUND = ITEMS.register("incomplete_ceramisteel_compound",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STARCOGS)));

    public static final RegistryObject<Item> DURASTEEL_SHEET = ITEMS.register("durasteel_sheet",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.STARCOGS)));
    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);
    }
}
