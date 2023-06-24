package genericbadname.createfinewines.register.content;

import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.FluidEntry;
import genericbadname.createfinewines.CreateFineWines;

import static genericbadname.createfinewines.CreateFineWines.REGISTRATE;

public class ModFluids {

    public static final FluidEntry<VirtualFluid> RED_GRAPE_JUICE =
            REGISTRATE.virtualFluid("red_grape_juice", CreateFineWines.asResource("fluid/red_grape_juice_still"), CreateFineWines.asResource("fluid/red_grape_juice_still"), CreateRegistrate::defaultFluidType,VirtualFluid::new)
                    .lang("Red Grape Juice")
                    .register();
    public static final FluidEntry<VirtualFluid> WHITE_GRAPE_JUICE =
            REGISTRATE.virtualFluid("white_grape_juice", CreateFineWines.asResource("fluid/white_grape_juice_still"), CreateFineWines.asResource("fluid/white_grape_juice_still"), CreateRegistrate::defaultFluidType,VirtualFluid::new)
                    .lang("White Grape Juice")
                    .register();
    public static final FluidEntry<VirtualFluid> RED_WINE =
            REGISTRATE.virtualFluid("red_wine", CreateFineWines.asResource("fluid/red_grape_juice_still"), CreateFineWines.asResource("fluid/red_grape_juice_still"), CreateRegistrate::defaultFluidType,VirtualFluid::new)
                    .lang("Red Wine")
                    .register();
    public static final FluidEntry<VirtualFluid> WHITE_WINE =
            REGISTRATE.virtualFluid("white_wine", CreateFineWines.asResource("fluid/white_grape_juice_still"), CreateFineWines.asResource("fluid/white_grape_juice_still"), CreateRegistrate::defaultFluidType,VirtualFluid::new)
                    .lang("White Wine")
                    .register();



    public static void init() {}
}
