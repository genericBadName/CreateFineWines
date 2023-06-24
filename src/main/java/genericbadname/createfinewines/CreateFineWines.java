package genericbadname.createfinewines;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import genericbadname.createfinewines.register.content.ModBlockEntity;
import genericbadname.createfinewines.register.content.ModBlocks;
import genericbadname.createfinewines.register.content.ModFluids;
import genericbadname.createfinewines.register.content.ModRecipes;
import genericbadname.createfinewines.register.handler.FineWineCreativeTab;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CreateFineWines.MODID)
public class CreateFineWines {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "createfinewines";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(CreateFineWines.MODID).creativeModeTab(() -> FineWineCreativeTab.FINE_WINE);;

    public CreateFineWines() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ModFluids.init();
        ModBlocks.init();
        ModBlockEntity.init();
        ModRecipes.register(modEventBus);
        REGISTRATE.registerEventListeners(modEventBus);

    }


    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(MODID, name);
    }
}
