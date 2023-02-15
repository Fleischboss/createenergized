package net.fleischboss.createenergized.item;

import net.fleischboss.createenergized.CreateEnergized;
import net.fleischboss.createenergized.fluid.ModFluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CreateEnergized.MOD_ID);

    // Zersium
    public static final RegistryObject<Item> ZERSIUM_CRYSTAL = ITEMS.register("zersium_crystal",
        () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ENERGIZED)));
    public static final RegistryObject<Item> ZERSIUM_DUST = ITEMS.register("zersium_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ENERGIZED)));

    // Durasteel
    public static final RegistryObject<Item> DURASTEEL_INGOT = ITEMS.register("durasteel_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ENERGIZED)));
    public static final RegistryObject<Item> DURASTEEL_COMPOUND = ITEMS.register("durasteel_compound",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ENERGIZED)));
    public static final RegistryObject<Item> DURASTEEL_SHEET = ITEMS.register("durasteel_sheet",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ENERGIZED)));

    // Ceramisteel
    public static final RegistryObject<Item> CERAMISTEEL_COMPOUND = ITEMS.register("ceramisteel_compound",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ENERGIZED)));
    public static final RegistryObject<Item> INCOMPLETE_CERAMISTEEL_COMPOUND = ITEMS.register("incomplete_ceramisteel_compound",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ENERGIZED)));

    // Other
    public static final RegistryObject<Item> LAPIS_SHEET = ITEMS.register("lapis_sheet",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ENERGIZED)));
    public static final RegistryObject<Item> INTEGRATED_CIRCUIT = ITEMS.register("integrated_circuit",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ENERGIZED)));

    public static final RegistryObject<Item> NITROGEN_BUCKET = ITEMS.register("nitrogen_bucket",
            () -> new BucketItem(ModFluids.SOURCE_NITROGEN,
                    new Item.Properties().tab(ModCreativeModeTab.ENERGIZED).craftRemainder(Items.BUCKET).stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
