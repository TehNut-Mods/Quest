package tehnut.quest.impl;

import net.minecraft.util.ResourceLocation;
import tehnut.quest.api.IPlayerQuestData;
import tehnut.quest.api.IQuest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerQuestData implements IPlayerQuestData {

    private final UUID uuid;
    private List<IQuest> activeQuests;
    private List<ResourceLocation> completedQuests;
    private IQuest primaryQuest;
    private int completedQuestCount;

    public PlayerQuestData(UUID uuid, List<IQuest> activeQuests, List<ResourceLocation> completedQuests) {
        this.uuid = uuid;
        this.activeQuests = activeQuests;
        this.completedQuests = completedQuests;
    }

    @Override
    public List<IQuest> getActiveQuests() {
        return activeQuests;
    }

    @Override
    public IQuest getPrimaryQuest() {
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
        return completedQuestCount;
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
