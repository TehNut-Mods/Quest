package tehnut.quest.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tehnut.quest.api.IQuest;
import tehnut.quest.api.QuestAPI;

public class MessageAbandonQuest implements IMessage, IMessageHandler<MessageAbandonQuest, IMessage> {

    private ResourceLocation questKey;

    public MessageAbandonQuest() {

    }

    public MessageAbandonQuest(ResourceLocation questKey) {
        this.questKey = questKey;
    }

    public MessageAbandonQuest(IQuest quest) {
        this(quest.getRegistryName());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        questKey = new ResourceLocation(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, questKey.toString());
    }

    @Override
    public IMessage onMessage(MessageAbandonQuest message, MessageContext ctx) {
        IQuest quest = QuestAPI.questRegistry.get(questKey);
        QuestAPI.playerHandler.getActiveQuests(ctx.getServerHandler().playerEntity).remove(quest);
        return null;
    }
}
