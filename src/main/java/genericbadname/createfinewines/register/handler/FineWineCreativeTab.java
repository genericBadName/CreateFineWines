package genericbadname.createfinewines.register.handler;

import com.tterrag.registrate.util.entry.RegistryEntry;
import genericbadname.createfinewines.CreateFineWines;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;

public class FineWineCreativeTab extends CreativeModeTab {
    public FineWineCreativeTab() {
        super(CreateFineWines.MODID);
    }

    public static final CreativeModeTab FINE_WINE = new FineWineCreativeTab();

    @Override
    public ItemStack makeIcon() {
        return Items.SWEET_BERRIES.getDefaultInstance();
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> items) {
        addItems(items, true);
        addBlocks(items);
        addItems(items, false);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Create: Fine Wine");
    }

    protected Collection<RegistryEntry<Item>> registeredItems() {
        return CreateFineWines.REGISTRATE.getAll(ForgeRegistries.ITEMS.getRegistryKey());
    }

    public void addBlocks(NonNullList<ItemStack> items) {
        for (RegistryEntry<Item> entry : registeredItems())
            if (entry.get() instanceof BlockItem blockItem)
                blockItem.fillItemCategory(this, items);
    }

    public void addItems(NonNullList<ItemStack> items, boolean specialItems) {
        ItemRenderer itemRenderer = Minecraft.getInstance()
                .getItemRenderer();

        for (RegistryEntry<Item> entry : registeredItems()) {
            Item item = entry.get();
            if (item instanceof BlockItem)
                continue;
            ItemStack stack = new ItemStack(item);
            BakedModel model = itemRenderer.getModel(stack, null, null, 0);
            if (model.isGui3d() == specialItems)
                item.fillItemCategory(this, items);
        }
    }
}