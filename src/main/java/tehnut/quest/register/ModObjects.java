package tehnut.quest.register;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tehnut.quest.QuestBoard;
import tehnut.quest.api.IQuest;
import tehnut.quest.api.QuestAPI;
import tehnut.quest.api.QuestType;
import tehnut.quest.block.BlockQuestPost;
import tehnut.quest.impl.Quest;

import java.util.Random;

public class ModObjects {

    public static Block questPost;

    public static void initBlocks() {
        questPost = registerBlock(new ItemBlock(new BlockQuestPost()), "BlockQuestPost");
    }

    public static void initItems() {

    }

    public static void initQuests() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            IQuest debugQuest = new Quest(new ResourceLocation(QuestBoard.MODID, "debugQuest" + i), QuestType.values()[random.nextInt(QuestType.values().length)]).setTitle(new TextComponentString("debug " + i));
            QuestAPI.questRegistry.register(debugQuest);
        }
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
