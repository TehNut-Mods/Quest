package tehnut.quest.util;

import tehnut.quest.api.IQuest;
import tehnut.quest.api.QuestAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestUtil {

    public static List<IQuest> getRandomQuests() {
        Random random = new Random();
        List<IQuest> generated = new ArrayList<IQuest>();

        for (IQuest quest : QuestAPI.questRegistry.getRegistry().values())
            if (random.nextBoolean())
                generated.add(quest);

        return generated;
    }
}
