package tehnut.quest.save.serialization;

import com.google.gson.*;
import net.minecraft.util.ResourceLocation;
import tehnut.quest.api.IQuest;
import tehnut.quest.api.QuestAPI;

import java.lang.reflect.Type;

public class QuestSerializer implements JsonSerializer<IQuest>, JsonDeserializer<IQuest> {

    @Override
    public IQuest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ResourceLocation key = new ResourceLocation(JsonHelper.getString(json, "regKey", "quest:null"));
        return QuestAPI.questRegistry.get(key);
    }

    @Override
    public JsonElement serialize(IQuest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("regKey", src.getRegistryName().toString());
        return jsonObject;
    }
}
