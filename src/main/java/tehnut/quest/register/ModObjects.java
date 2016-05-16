package tehnut.quest.register;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModObjects {

    public static void initBlocks() {

    }

    public static void initItems() {

    }

    private static Block registerBlock(ItemBlock itemBlock, String name) {
        Block block = itemBlock.getBlock();
        if (block.getRegistryName() == null)
            block.setRegistryName(name);

        if (itemBlock.getRegistryName() == null)
            itemBlock.setRegistryName(block.getRegistryName());

        GameRegistry.register(block);
        GameRegistry.register(itemBlock);
        return block;
    }

    private static Block registerBlock(Block block, String name) {
        if (block.getRegistryName() == null)
            block.setRegistryName(name);

        GameRegistry.register(block);
        return block;
    }

    private static Item registerItem(Item item, String name) {
        if (item.getRegistryName() == null)
            item.setRegistryName(name);

        GameRegistry.register(item);
        return item;
    }
}
