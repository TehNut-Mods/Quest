package tehnut.quest.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public interface IPlayerHandler {

    IPlayerQuestData getPlayerQuestData(EntityPlayer player);

    List<IQuest> getActiveQuests(EntityPlayer player);

    IPlayerQuestData setPrimaryQuest(EntityPlayer player, IQuest quest);

    List<ResourceLocation> getCompletedQuests(EntityPlayer player);

    int getCompletedQuestCount(EntityPlayer player);

    int completeQuest(EntityPlayer player, IQuest quest);
}
