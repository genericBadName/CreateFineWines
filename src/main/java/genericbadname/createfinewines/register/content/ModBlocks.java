package genericbadname.createfinewines.register.content;

import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.BlockEntry;
import genericbadname.createfinewines.block.WineBarrel;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static genericbadname.createfinewines.CreateFineWines.REGISTRATE;

public class ModBlocks {

    public static final BlockEntry<WineBarrel> WINE_BARREL = REGISTRATE
            .block("wine_barrel", WineBarrel::new)
            .properties(BlockBehaviour.Properties::noOcclusion)
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.standardModel(c, p)))
            .transform(axeOrPickaxe())
            .simpleItem()
            .register();

    public static void init(){}
}
