package tehnut.quest.util;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tehnut.quest.QuestBoard;
import tehnut.quest.api.IQuestProvider;
import tehnut.quest.api.QuestAPI;
import tehnut.quest.impl.PlayerHandler;
import tehnut.quest.save.JsonSaveHandler;

public class EventHandler {

    @SubscribeEvent
    public void onBlockInteract(PlayerInteractEvent.RightClickBlock event) {
        if (event.getWorld().getTileEntity(event.getPos()) != null && event.getWorld().getTileEntity(event.getPos()) instanceof IQuestProvider) {
            if (event.getEntityPlayer().isSneaking()) {
                event.getEntityPlayer().openGui(QuestBoard.instance, 1, event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
            } else {
                IQuestProvider questProvider = (IQuestProvider) event.getWorld().getTileEntity(event.getPos());
                event.getEntityPlayer().openGui(QuestBoard.instance, 0, event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
            }
        }
    }

    @SubscribeEvent
    public void onSave(WorldEvent.Save event) {
        PlayerHandler playerHandler = (PlayerHandler) QuestAPI.playerHandler;
        if (!event.getWorld().isRemote && playerHandler != null)
            JsonSaveHandler.writePlayerData(playerHandler.getPlayerQuestData());
    }
}
