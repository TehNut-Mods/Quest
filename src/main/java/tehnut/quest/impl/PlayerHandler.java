package tehnut.quest.impl;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import tehnut.quest.api.IPlayerHandler;
import tehnut.quest.api.IPlayerQuestData;
import tehnut.quest.api.IQuest;

import java.util.*;

public class PlayerHandler implements IPlayerHandler {

    public Map<UUID, IPlayerQuestData> playerQuestData;

    @Override
    public IPlayerQuestData getPlayerQuestData(EntityPlayer player) {
        if (!playerQuestData.containsKey(player.getGameProfile().getId()))
            playerQuestData.put(player.getGameProfile().getId(), new PlayerQuestData(player.getGameProfile().getId(), Collections.<IQuest>emptyList(), Collections.<ResourceLocation>emptyList()));
        return playerQuestData.get(player.getGameProfile().getId());
    }

    @Override
    public List<IQuest> getActiveQuests(EntityPlayer player) {
        return getPlayerQuestData(player).getActiveQuests();
    }

    @Override
    public IPlayerQuestData setPrimaryQuest(EntityPlayer player, IQuest quest) {
        return getPlayerQuestData(player).setPrimaryQuest(quest);
    }

    @Override
    public List<ResourceLocation> getCompletedQuests(EntityPlayer player) {
        return getPlayerQuestData(player).getCompletedQuests();
    }

    @Override
    public int getCompletedQuestCount(EntityPlayer player) {
        return getPlayerQuestData(player).getCompletedQuestCount();
    }

    @Override
    public int completeQuest(EntityPlayer player, IQuest quest) {
        getActiveQuests(player).remove(quest);
        getCompletedQuests(player).add(quest.getRegistryName());
        return getPlayerQuestData(player).getCompletedQuestCount();
    }

    public Map<UUID, IPlayerQuestData> getPlayerQuestData() {
        return ImmutableMap.copyOf(playerQuestData);
    }
}
