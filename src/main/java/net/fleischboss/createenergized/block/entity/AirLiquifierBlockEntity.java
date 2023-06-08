package net.fleischboss.createenergized.block.entity;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.kinetics.fan.EncasedFanBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.utility.Lang;
import joptsimple.internal.Strings;
import mekanism.api.Action;
import mekanism.api.energy.IStrictEnergyHandler;
import mekanism.api.math.FloatingLong;
import net.fleischboss.createenergized.registrates.EnergizedFluid;
import net.fleischboss.createenergized.util.ModEnergyStorage;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class AirLiquifierBlockEntity extends SmartBlockEntity implements IHaveGoggleInformation {

    private static final int TANK_CAPACITY= 1000; //in milibuckets
    private static final int MAX_FAN_DISTANCE=8;
    private static final Fluid FLUID_PRODUCED= EnergizedFluid.LIQUID_AIR.get().getSource();
    private static final int LIQUID_AIR_PRODUCED= 50; //in milibuckets
    private static final int ENERGY_CAPACITY= 2000; //in FE
    private static final int ENERGY_USED= 200; //in FE

    SmartFluidTankBehaviour internalTank;
    private final ModEnergyStorage ENERGY_STORAGE = createEnergyStorage();


    public AirLiquifierBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        setLazyTickRate(10);
    }


    @Override
    public void lazyTick() {
        super.lazyTick();
        if (hasAir() && hasEnoughEnergy() && hasSpace()) {
            extractEnergy();
            produceLiquid();
        }
    }


    //Air Handling

    private boolean hasAir()
    {
        for (int i = 1; i < MAX_FAN_DISTANCE+1; i++) {
            if(level.getBlockEntity(getBlockPos().above(i)) instanceof EncasedFanBlockEntity fan)
            {
               return fan.getAirCurrent().direction==Direction.DOWN&&Math.abs(fan.getSpeed())>(fan.getBlockPos().getY()-getBlockPos().getY());
            }
        }
        return false;
    }



    //Fluid Handling
    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        behaviours.add(internalTank = SmartFluidTankBehaviour.single(this, TANK_CAPACITY)
                .allowExtraction()
                .forbidInsertion());
    }
    private boolean hasSpace() {
        return internalTank.getPrimaryHandler().getFluidAmount()<TANK_CAPACITY;
    }

    private void produceLiquid() {
        internalTank.allowInsertion();
        internalTank.getPrimaryHandler().fill(new FluidStack(FLUID_PRODUCED,LIQUID_AIR_PRODUCED), IFluidHandler.FluidAction.EXECUTE);
        internalTank.forbidInsertion();
    }



    //Energy Handling
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();


    @NotNull
    protected ModEnergyStorage createEnergyStorage() {
        return new ModEnergyStorage(ENERGY_CAPACITY, 200) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }
    private void extractEnergy() {
        ENERGY_STORAGE.extractEnergy(ENERGY_USED, false);
    }


    private   boolean hasEnoughEnergy() {
        return ENERGY_STORAGE.getEnergyStored() >= ENERGY_USED;
    }



    //Capability Handling
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if(cap == ForgeCapabilities.ENERGY&&side==Direction.NORTH)
            return lazyEnergyHandler.cast();

        if (!(side == Direction.NORTH||side == Direction.UP) && isFluidHandlerCap(cap))
            return internalTank.getCapability().cast();

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyEnergyHandler.invalidate();
    }

    //Goggles
    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {

        tooltip.add(Lang.builder()
                        .text("       Energy ")
                        .add(Component.literal("|".repeat((ENERGY_STORAGE.getEnergyStored()-ENERGY_USED)/(ENERGY_CAPACITY/12))).withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD))
                .component());

        return (containedFluidTooltip(tooltip, isPlayerSneaking, internalTank.getCapability()
                .cast())&&!internalTank.isEmpty());

    }


    //NBT Save

    @Override
    protected void write(CompoundTag tag, boolean clientPacket) {
        tag.put("energy", ENERGY_STORAGE.serializeNBT());
        super.write(tag, clientPacket);
    }

    @Override
    protected void read(CompoundTag tag, boolean clientPacket) {
        ENERGY_STORAGE.deserializeNBT(tag.get("energy"));
        super.read(tag, clientPacket);
    }


}
