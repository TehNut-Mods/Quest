package tehnut.quest;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tehnut.quest.api.*;
import tehnut.quest.command.CommandQuestClear;
import tehnut.quest.command.CommandQuestManage;
import tehnut.quest.gui.GuiHandler;
import tehnut.quest.impl.PlayerHandler;
import tehnut.quest.impl.QuestRegistry;
import tehnut.quest.network.QuestPacketHandler;
import tehnut.quest.proxy.CommonProxy;
import tehnut.quest.register.ModObjects;
import tehnut.quest.save.JsonSaveHandler;
import tehnut.quest.util.EventHandler;

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
    public static File saveDir;

    public static final Logger LOGGER = LogManager.getLogger(NAME);

    private final IQuestRegistry questRegistry = new QuestRegistry();
    private final IPlayerHandler playerHandler = new PlayerHandler();

    public QuestBoard() {
        QuestAPI.questRegistry = this.questRegistry;
        QuestAPI.playerHandler = this.playerHandler;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configDir = new File(event.getModConfigurationDirectory(), "QuestBoard");

        QuestPacketHandler.init();
        ModObjects.initBlocks();
        ModObjects.initItems();
        ModObjects.initQuests();

        MinecraftForge.EVENT_BUS.register(new EventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (QuestAPI.questRegistry != this.questRegistry || QuestAPI.playerHandler != this.playerHandler)
            throw new RuntimeException("Sanity check failed! Somebody replaced our API implementation!");
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        saveDir = event.getServer().getActiveAnvilConverter().getFile(event.getServer().getFolderName(), "data");
        ((PlayerHandler) QuestAPI.playerHandler).playerQuestData = JsonSaveHandler.readPlayerData();
        event.registerServerCommand(new CommandQuestManage());
        event.registerServerCommand(new CommandQuestClear());
    }
}
