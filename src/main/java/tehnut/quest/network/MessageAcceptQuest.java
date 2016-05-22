package tehnut.quest.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tehnut.quest.api.IQuest;
import tehnut.quest.api.QuestAPI;

public class MessageAcceptQuest implements IMessage {

    private ResourceLocation questKey;

    public MessageAcceptQuest() {

    }

    public MessageAcceptQuest(ResourceLocation questKey) {
        this.questKey = questKey;
    }

    public MessageAcceptQuest(IQuest quest) {
        this.questKey = quest.getRegistryName();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        questKey = new ResourceLocation(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, questKey.toString());
    }

    public static class Handler implements IMessageHandler<MessageAcceptQuest, IMessage> {

        @Override
        public IMessage onMessage(MessageAcceptQuest message, MessageContext ctx) {
            IQuest quest = QuestAPI.questRegistry.get(message.questKey);
            QuestAPI.playerHandler.getActiveQuests(ctx.getServerHandler().playerEntity).add(quest);
            return null;
        }
    }
}
