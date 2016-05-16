package tehnut.quest.api;

import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.UUID;

public interface IPlayerQuestData {

    List<IQuest> getActiveQuests();

    List<ResourceLocation> getCompletedQuests();

    IQuest getPrimaryQuest();

    IPlayerQuestData setPrimaryQuest(IQuest quest);

    int getCompletedQuestCount();

    UUID getUUID();
}
