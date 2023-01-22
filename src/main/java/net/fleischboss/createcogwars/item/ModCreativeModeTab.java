package net.fleischboss.createcogwars.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {

    public static final CreativeModeTab STARCOGS = new CreativeModeTab("starcogs") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.DURASTEEL_INGOT.get());
        }
    };
}
