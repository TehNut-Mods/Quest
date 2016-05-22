package tehnut.quest.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import tehnut.quest.QuestBoard;

public class QuestPacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper(QuestBoard.MODID);

    public static void init() {
        int id = 0;
        INSTANCE.registerMessage(MessageAbandonQuest.Handler.class, MessageAbandonQuest.class, ++id, Side.SERVER);
        INSTANCE.registerMessage(MessageAcceptQuest.Handler.class, MessageAcceptQuest.class, ++id, Side.SERVER);
    }

    public static void sendToAllAround(IMessage message, TileEntity te, int range) {
        INSTANCE.sendToAllAround(message, new NetworkRegistry.TargetPoint(te.getWorld().provider.getDimension(), te.getPos().getX(), te.getPos().getY(), te.getPos().getZ(), range));
    }

    public static void sendToAllAround(IMessage message, TileEntity te) {
        sendToAllAround(message, te, 64);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player) {
        INSTANCE.sendTo(message, player);
    }
}
