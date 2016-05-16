package tehnut.quest.api;

import net.minecraft.util.ResourceLocation;

import java.util.Map;

public interface IQuestRegistry {

    Map<ResourceLocation, IQuest> getRegistry();

    IQuest register(ResourceLocation key, IQuest quest);

    IQuest register(IQuest quest);

    IQuest remove(ResourceLocation key);

    IQuest get(ResourceLocation key);
}
