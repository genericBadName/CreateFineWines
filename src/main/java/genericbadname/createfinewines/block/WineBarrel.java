package genericbadname.createfinewines.block;

import com.simibubi.create.foundation.block.IBE;
import genericbadname.createfinewines.register.content.ModBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class WineBarrel extends Block implements IBE<WineBarrelBlockEntity> {
    public WineBarrel(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Class<WineBarrelBlockEntity> getBlockEntityClass() {
        return WineBarrelBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends WineBarrelBlockEntity> getBlockEntityType() {
        return ModBlockEntity.WINE_BARREL.get();
    }
}
