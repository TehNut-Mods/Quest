package tehnut.quest.util;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import tehnut.quest.QuestBoard;
import tehnut.quest.api.IQuestProvider;

public class EventHandler {

    public void onBlockInteract(PlayerInteractEvent.RightClickBlock event) {
        if (event.getWorld().getTileEntity(event.getPos()) != null && event.getWorld().getTileEntity(event.getPos()) instanceof IQuestProvider) {
            IQuestProvider questProvider = (IQuestProvider) event.getWorld().getTileEntity(event.getPos());
            event.getEntityPlayer().openGui(QuestBoard.instance, 0, event.getWorld(), 0, 0, 0);
        }
    }
}
