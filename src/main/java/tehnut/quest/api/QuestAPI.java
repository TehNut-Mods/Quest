package tehnut.quest.api;

public class QuestAPI {

    /**
     * Used to register your quests.
     *
     * Set when QuestBoard is constructed during startup by FML.
     */
    public static IQuestRegistry questRegistry = null;
    /**
     * Used to get quest data from players. Set
     *
     * Set when QuestBoard is constructed during startup by FML.
     */
    public static IPlayerHandler playerHandler = null;
}
