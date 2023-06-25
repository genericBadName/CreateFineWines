package genericbadname.createfinewines.block;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.utility.Lang;
import genericbadname.createfinewines.CreateFineWines;
import genericbadname.createfinewines.recipe.FermentingRecipe;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class WineBarrelBlockEntity extends SmartBlockEntity implements IHaveGoggleInformation {

    private static final int TANK_CAPACITY= 1000; //in milibuckets
    private static final int PROCESSING_TIME= 200; //in ticks
    private static final String QUALITY_TAG_KEY= CreateFineWines.MODID+":quality";
    public int processingTicks;
    public boolean running;

    SmartFluidTankBehaviour internalTank;


    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        running = compound.getBoolean("Running");
        processingTicks = compound.getInt("Ticks");
        super.read(compound, clientPacket);

    }


    @Override
    public void write(CompoundTag compound, boolean clientPacket) {
        compound.putBoolean("Running", running);
        compound.putInt("Ticks", processingTicks);
        super.write(compound, clientPacket);
    }

    public WineBarrelBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }


    @Override
    public void tick() {
        super.tick();

        if(!recipePresent()&&!agingPresent()) return;
        if(!running) {
            processingTicks = PROCESSING_TIME;
            running=true;
        }
        processingTicks--;
        if (processingTicks == 0) {
            if(agingPresent())
                applyAging();
            if(recipePresent())
                applyRecipe();
            internalTank.allowExtraction();
            sendData();
            running=false;
        }
    }

    private void applyAging() {
        CompoundTag tag=internalTank.getPrimaryHandler().getFluid().getOrCreateTag();
        int i=tag.getInt(QUALITY_TAG_KEY);
        i++;
        System.out.println(level);
        System.out.println(i);
        tag.putInt(QUALITY_TAG_KEY,i);
    }

    private boolean agingPresent() {
        if(internalTank==null||internalTank.getPrimaryHandler().isEmpty()) return false;

        if(internalTank.getPrimaryHandler().getFluid().getOrCreateTag().contains(QUALITY_TAG_KEY)) {
           return internalTank.getPrimaryHandler().getFluid().getOrCreateTag().getInt(QUALITY_TAG_KEY)<10;
        }
       return false;
    }


    private void applyRecipe() {

        FluidStack wine=new FluidStack(FermentingRecipe.getResult(internalTank.getPrimaryHandler().getFluid(),level),internalTank.getPrimaryHandler().getFluidAmount());
        CompoundTag tag= wine.getOrCreateTag();
        tag.putInt(QUALITY_TAG_KEY,1);
        wine.setTag(tag);
        internalTank.getPrimaryHandler().setFluid(wine);
    }

    private boolean recipePresent() {

       if(FermentingRecipe.matches(internalTank.getPrimaryHandler().getFluid(),level)) {
           internalTank.forbidExtraction();
           return internalTank.getPrimaryHandler().getFluidAmount() == 1000;
       }
       return false;
    }


    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        behaviours.add(internalTank = SmartFluidTankBehaviour.single(this, TANK_CAPACITY)
                .allowExtraction()
                .allowInsertion());
    }

    @Override
    @NotNull
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction side) {

        if (isFluidHandlerCap(capability))
            return internalTank.getCapability().cast();
        return super.getCapability(capability, side);
    }



    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        containedFluidTooltip(tooltip, isPlayerSneaking, internalTank.getCapability()
                .cast());
        if(!internalTank.getPrimaryHandler().isEmpty()) {
            if (internalTank.getPrimaryHandler().getFluid().getOrCreateTag().contains(QUALITY_TAG_KEY)) {
                int quality = internalTank.getPrimaryHandler().getFluid().getOrCreateTag().getInt(QUALITY_TAG_KEY);
                ChatFormatting color;
                if (quality < 4)
                    color = ChatFormatting.RED;
                else if (quality > 7)
                    color = ChatFormatting.GREEN;
                else
                    color = ChatFormatting.YELLOW;

                tooltip.add(Lang.builder()
                        .text("     Quality ")
                        .add(Component.literal("|".repeat(quality)).withStyle(color, ChatFormatting.BOLD))
                        .component());
            }
        }
        return true;
    }
}
