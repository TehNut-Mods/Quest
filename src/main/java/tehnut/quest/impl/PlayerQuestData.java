package tehnut.quest.impl;

import net.minecraft.util.ResourceLocation;
import tehnut.quest.api.IPlayerQuestData;
import tehnut.quest.api.IQuest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerQuestData implements IPlayerQuestData {

    private UUID uuid;
    private List<IQuest> activeQuests;
    private List<ResourceLocation> completedQuests;
    private IQuest primaryQuest;
    private int completedQuestCount;

    public PlayerQuestData(UUID uuid, List<IQuest> activeQuests, List<ResourceLocation> completedQuests) {
        this.uuid = uuid;
        this.activeQuests = new ArrayList<IQuest>(activeQuests);
        this.completedQuests = new ArrayList<ResourceLocation>(completedQuests);
    }

    @Override
    public List<IQuest> getActiveQuests() {
        return activeQuests;
    }

    @Override
    public IQuest getPrimaryQuest() {
        if (primaryQuest == null && !activeQuests.isEmpty())
            primaryQuest = activeQuests.get(0);

        return primaryQuest;
    }

    @Override
    public IPlayerQuestData setPrimaryQuest(IQuest quest) {
        this.primaryQuest = quest;
        return this;
    }

    @Override
    public List<ResourceLocation> getCompletedQuests() {
        return completedQuests;
    }

    @Override
    public int getCompletedQuestCount() {
        return completedQuests.size();
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public int increaseCompletedAmount(int amount) {
        completedQuestCount += amount;
        return completedQuestCount + amount;
    }
}
