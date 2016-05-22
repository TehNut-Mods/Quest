package tehnut.quest.save;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import tehnut.quest.QuestBoard;
import tehnut.quest.api.IPlayerQuestData;
import tehnut.quest.api.IQuest;
import tehnut.quest.save.serialization.PlayerQuestDataSerializer;
import tehnut.quest.save.serialization.QuestSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class JsonSaveHandler {

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .disableHtmlEscaping()
            .registerTypeHierarchyAdapter(IPlayerQuestData.class, new PlayerQuestDataSerializer())
            .registerTypeHierarchyAdapter(IQuest.class, new QuestSerializer())
            .create();

    public static File playerDataFile = new File(QuestBoard.saveDir, "PlayerQuestData.json");

    public static void writePlayerData(Map<UUID, IPlayerQuestData> playerData) {
        try {
            String json = GSON.toJson(playerData.values());
            FileWriter writer = new FileWriter(playerDataFile);
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            QuestBoard.LOGGER.error(e.getLocalizedMessage());
        }
    }

    public static Map<UUID, IPlayerQuestData> readPlayerData() {
        try {
            if (!playerDataFile.exists())
                playerDataFile.createNewFile();

            List<IPlayerQuestData> dataList = GSON.fromJson(new FileReader(playerDataFile), new TypeToken<ArrayList<IPlayerQuestData>>(){}.getType());
            Map<UUID, IPlayerQuestData> uuidMap = new HashMap<UUID, IPlayerQuestData>();
            for (IPlayerQuestData data : dataList)
                uuidMap.put(data.getUUID(), data);

            return uuidMap;
        } catch (Exception e) {
            QuestBoard.LOGGER.error(e.getLocalizedMessage());
            QuestBoard.LOGGER.info("PlayerQuestData.json for this save failed to be read. Creating a new one.");
            writePlayerData(new HashMap<UUID, IPlayerQuestData>());
            return new HashMap<UUID, IPlayerQuestData>();
        }
    }
}
