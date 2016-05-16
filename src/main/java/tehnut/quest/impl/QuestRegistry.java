package tehnut.quest.impl;

import com.google.common.collect.ImmutableMap;
import net.minecraft.util.ResourceLocation;
import tehnut.quest.api.IQuest;
import tehnut.quest.api.IQuestRegistry;

import java.util.HashMap;
import java.util.Map;

public class QuestRegistry implements IQuestRegistry {

    private final Map<ResourceLocation, IQuest> registry = new HashMap<ResourceLocation, IQuest>();

    @Override
    public Map<ResourceLocation, IQuest> getRegistry() {
        return ImmutableMap.copyOf(registry);
    }

    @Override
    public IQuest register(ResourceLocation key, IQuest quest) {
        return registry.put(key, quest);
    }

    @Override
    public IQuest register(IQuest quest) {
        return register(quest.getRegistryName(), quest);
    }

    @Override
    public IQuest remove(ResourceLocation key) {
        return registry.remove(key);
    }

    @Override
    public IQuest get(ResourceLocation key) {
        return getRegistry().get(key);
    }
}
