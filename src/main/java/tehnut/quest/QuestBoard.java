package tehnut.quest;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tehnut.quest.api.QuestAPI;
import tehnut.quest.impl.PlayerHandler;
import tehnut.quest.impl.QuestRegistry;
import tehnut.quest.network.QuestPacketHandler;
import tehnut.quest.proxy.CommonProxy;
import tehnut.quest.register.ModObjects;

import java.io.File;

@Mod(modid = QuestBoard.MODID, name = QuestBoard.NAME, version = "@VERSION@")
public class QuestBoard {

    public static final String MODID = "questboard";
    public static final String NAME = "Quest Board";

    @Mod.Instance(MODID)
    public static QuestBoard instance;

    @SidedProxy(clientSide = "tehnut.quest.proxy.ClientProxy", serverSide = "tehnut.quest.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static File configDir;

    public QuestBoard() {
        QuestAPI.questRegistry = new QuestRegistry();
        QuestAPI.playerHandler = new PlayerHandler();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configDir = new File(event.getModConfigurationDirectory(), "QuestBoard");

        QuestPacketHandler.init();
        ModObjects.initBlocks();
        ModObjects.initItems();

        proxy.preInit(event);
    }
}
