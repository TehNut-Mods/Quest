package tehnut.quest.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Map;

public class QuestAPI {

    /**
     * Used to register your quests.
     *
     * Defaults to a dummy implementation. Set when QuestBoard is constructed during startup by FML.
     */
    public static IQuestRegistry questRegistry = new IQuestRegistry() {
        @Override
        public Map<ResourceLocation, IQuest> getRegistry() {
            return null; // No-Op
        }

        @Override
        public IQuest register(IQuest quest) {
            return null; // No-Op
        }

        @Override
        public IQuest remove(ResourceLocation key) {
            return null; // No-Op
        }

        @Override
        public IQuest get(ResourceLocation key) {
            return null; // No-Op
        }
    };
    /**
     * Used to get quest data from players. Set
     *
     * Defaults to a dummy implementation. Set when QuestBoard is constructed during startup by FML.
     */
    public static IPlayerHandler playerHandler = new IPlayerHandler() {
        @Override
        public IPlayerQuestData getPlayerQuestData(EntityPlayer player) {
            return null; // No-Op
        }

        @Override
        public List<IQuest> getActiveQuests(EntityPlayer player) {
            return null; // No-Op
        }

        @Override
        public IPlayerQuestData setPrimaryQuest(EntityPlayer player, IQuest quest) {
            return null; // No-Op
        }

        @Override
        public List<ResourceLocation> getCompletedQuests(EntityPlayer player) {
            return null; // No-Op
        }

        @Override
        public int getCompletedQuestCount(EntityPlayer player) {
            return 0; // No-Op
        }

        @Override
        public int completeQuest(EntityPlayer player, IQuest quest) {
            return 0; // No-Op
        }
    };
}
