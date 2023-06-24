package genericbadname.createfinewines.register.content;

import com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import genericbadname.createfinewines.block.WineBarrelBlockEntity;

import static genericbadname.createfinewines.CreateFineWines.REGISTRATE;

public class ModBlockEntity {

    public static final BlockEntityEntry<WineBarrelBlockEntity> WINE_BARREL = REGISTRATE
            .blockEntity("wine_barrel", WineBarrelBlockEntity::new)
            .validBlocks(ModBlocks.WINE_BARREL)
            .renderer(()-> SmartBlockEntityRenderer::new)
            .register();

    public static void init() {}

}
