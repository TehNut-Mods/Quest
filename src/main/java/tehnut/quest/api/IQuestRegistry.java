package tehnut.quest.api;

import net.minecraft.util.ResourceLocation;

import java.util.Map;

public interface IQuestRegistry {

    /**
     * Retrieves an Immutable copy of the quest registry map.
     * @return - An Immutable copy of the quest registry map.
     */
    Map<ResourceLocation, IQuest> getRegistry();

    /**
     * Registers a quest with the Quest registry.
     *
     * @param quest - {@link IQuest} to register.
     * @return - The registered quest.
     */
    IQuest register(IQuest quest);

    /**
     * Removes a quest from the registry.
     *
     * @param key - The key for the {@link IQuest} to remove.
     * @return - The removed quest.
     */
    IQuest remove(ResourceLocation key);

    /**
     * Retrieves an {@link IQuest} from the registry.
     *
     * @param key - The key for the {@link IQuest}.
     * @return - The retrieved quest. Null if the quest does not exist in the registry.
     */
    IQuest get(ResourceLocation key);
}
