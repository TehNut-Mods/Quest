package tehnut.quest.api;

import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.UUID;

public interface IPlayerQuestData {

    /**
     * Retrieves the list of active quests for the player.
     *
     * @return - The list of active quests for the player.
     */
    List<IQuest> getActiveQuests();

    /**
     * Retrieves the list of completed quest keys for the player.
     *
     * @return - The list of completed quest keys for the player.
     */
    List<ResourceLocation> getCompletedQuests();

    /**
     * Retrieves the current primary quest for the player.
     *
     * @return - The current primary quest for the player.
     */
    IQuest getPrimaryQuest();

    /**
     * Sets the primary quest for the player.
     *
     * @param quest - The quest to set as primary.
     * @return - The current instance of {@link IPlayerQuestData} for chaining.
     */
    IPlayerQuestData setPrimaryQuest(IQuest quest);

    /**
     * Retrieves the amount of quests that have been completed by the player.
     *
     * @return - The amount of quests that have been completed by the player.
     */
    int getCompletedQuestCount();

    /**
     * The UUID of the player this {@link IPlayerQuestData} instance is tracking.
     *
     * @return - The UUID of the tracked player.
     */
    UUID getUUID();
}
