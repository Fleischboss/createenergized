package net.fleischboss.createenergized.ct;

import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;
import net.fleischboss.createenergized.CreateEnergized;
import net.minecraft.resources.ResourceLocation;

public class SpriteShifts {
    public static final CTSpriteShiftEntry DURASTEEL_CASING = getCT(AllCTTypes.OMNIDIRECTIONAL, "durasteel_casing");

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName) {
        return CTSpriteShifter.getCT(type, new ResourceLocation(CreateEnergized.MOD_ID, "block/" + blockTextureName), new ResourceLocation(CreateEnergized.MOD_ID, "block/" + blockTextureName + "_connected"));
    }
}
