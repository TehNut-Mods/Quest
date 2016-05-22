package tehnut.quest.save.serialization;

import com.google.gson.*;
import net.minecraft.util.ResourceLocation;
import tehnut.quest.api.IPlayerQuestData;
import tehnut.quest.api.IQuest;
import tehnut.quest.api.QuestAPI;
import tehnut.quest.impl.PlayerQuestData;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerQuestDataSerializer implements JsonSerializer<IPlayerQuestData>, JsonDeserializer<IPlayerQuestData> {

    @Override
    public IPlayerQuestData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<String> activeQuestsString = context.deserialize(json.getAsJsonObject().get("active"), List.class);
        List<String> completedQuestsString = context.deserialize(json.getAsJsonObject().get("completed"), List.class);
        ResourceLocation primaryQuestKey = new ResourceLocation(JsonHelper.getString(json, "primary", "quest:null"));
        UUID uuid = UUID.fromString(JsonHelper.getString(json, "uuid", UUID.randomUUID().toString()));

        List<IQuest> activeQuests = new ArrayList<IQuest>();
        for (String active : activeQuestsString)
            if (QuestAPI.questRegistry.getRegistry().containsKey(new ResourceLocation(active)))
                activeQuests.add(QuestAPI.questRegistry.get(new ResourceLocation(active)));

        List<ResourceLocation> completedQuests = new ArrayList<ResourceLocation>();
        for (String completed : completedQuestsString)
            completedQuests.add(new ResourceLocation(completed));

        IPlayerQuestData playerData = new PlayerQuestData(uuid, activeQuests, completedQuests);
        if (QuestAPI.questRegistry.getRegistry().containsKey(primaryQuestKey))
            playerData.setPrimaryQuest(QuestAPI.questRegistry.get(primaryQuestKey));

        return playerData;
    }

    @Override
    public JsonElement serialize(IPlayerQuestData src, Type typeOfSrc, JsonSerializationContext context) {
        List<String> activeQuestsString = new ArrayList<String>();
        for (IQuest quest : src.getActiveQuests())
            activeQuestsString.add(quest.getRegistryName().toString());

        List<String> completedQuestsString = new ArrayList<String>();
        for (ResourceLocation questKey : src.getCompletedQuests())
            completedQuestsString.add(questKey.toString());
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("active", context.serialize(activeQuestsString, List.class));
        jsonObject.add("completed", context.serialize(completedQuestsString, List.class));
        jsonObject.addProperty("primary", src.getPrimaryQuest() != null ? src.getPrimaryQuest().getRegistryName().toString() : "quest:null");
        jsonObject.addProperty("uuid", src.getUUID().toString());
        return jsonObject;
    }
}
