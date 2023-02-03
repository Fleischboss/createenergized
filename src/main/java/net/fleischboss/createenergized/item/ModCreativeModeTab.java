package net.fleischboss.createenergized.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {

    public static final CreativeModeTab ENERGIZED = new CreativeModeTab("energized") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(net.fleischboss.createenergized.item.ModItems.DURASTEEL_INGOT.get());
        }
    };
}
