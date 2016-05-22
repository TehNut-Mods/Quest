package tehnut.quest.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public interface IPlayerHandler {

    /**
     * Retrieves the {@link IPlayerQuestData} from the provided player from the instances
     * chosen storage solution.
     *
     * @param player - EntityPlayer to obtain quest data for.
     * @return - The {@link IPlayerQuestData} for the provided player.
     */
    IPlayerQuestData getPlayerQuestData(EntityPlayer player);

    /**
     * Retrieves a Mutable list of active {@link IQuest}'s for the provided player.
     *
     * @param player - EntityPlayer to obtain quest data for.
     * @return - A list of active quests for the provided player.
     */
    List<IQuest> getActiveQuests(EntityPlayer player);

    /**
     * Sets the primary quest for the EntityPlayer.
     *
     * Primary quests are displayed with a higher priority than others.
     *
     * @param player - The EntityPlayer to set a primary quest for.
     * @param quest  - The {@link IQuest} to set as primary.
     * @return
     */
    IPlayerQuestData setPrimaryQuest(EntityPlayer player, IQuest quest);

    /**
     * Retrieves a list of registry keys for quests that have been completed.
     *
     * @param player - EntityPlayer to obtain quest data for.
     * @return - A list of registry keys for completed quests.
     */
    List<ResourceLocation> getCompletedQuests(EntityPlayer player);

    /**
     * Retrieves the amount of quests that the player has completed.
     *
     * @param player - EntityPlayer to obtain quest data for.
     * @return - The amount of quests that the player has completed.
     */
    int getCompletedQuestCount(EntityPlayer player);

    /**
     * Marks a quest as completed.
     *
     * The default implementation removes the provided quest from the player's active quests list and adds it to the
     * completed quests list.
     *
     * @see tehnut.quest.impl.PlayerHandler#completeQuest(EntityPlayer, IQuest)
     *
     * @param player - EntityPlayer to obtain quest data for.
     * @param quest  - Quest to be considered complete.
     * @return - The new amount of completed quests.
     */
    int completeQuest(EntityPlayer player, IQuest quest);
}
