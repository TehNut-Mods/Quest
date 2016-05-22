package tehnut.quest.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import tehnut.quest.api.IQuest;
import tehnut.quest.api.IQuestProvider;
import tehnut.quest.api.QuestAPI;

import java.util.ArrayList;
import java.util.List;

public class TileQuestPost extends TileEntity implements IQuestProvider {

    public static final String AVAILABLE_QUESTS = "availableQuests";
    public static final String QUEST  = "quest";

    private List<IQuest> availableQuests;

    public TileQuestPost() {
        this.availableQuests = new ArrayList<IQuest>();
    }

    public TileQuestPost(List<IQuest> randomDefaults) {
        this.availableQuests = randomDefaults;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.availableQuests = readNBT(compound.getCompoundTag(QUEST));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        compound.setTag(QUEST, writeNBT());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new SPacketUpdateTileEntity(getPos(), -999, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        readFromNBT(pkt.getNbtCompound());
    }

    // IQuestProvider

    @Override
    public List<IQuest> getAvailableQuests() {
        return availableQuests;
    }

    @Override
    public boolean completeQuest(IQuest quest) {
        return availableQuests.remove(quest);
    }

    @Override
    public NBTTagCompound writeNBT() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        NBTTagList tagList = new NBTTagList();
        for (IQuest quest : availableQuests)
            tagList.appendTag(new NBTTagString(quest.getRegistryName().toString()));

        tagCompound.setTag(AVAILABLE_QUESTS, tagList);
        return tagCompound;
    }

    @Override
    public List<IQuest> readNBT(NBTTagCompound tagCompound) {
        NBTTagList tagList = tagCompound.getTagList(AVAILABLE_QUESTS, 9);
        List<IQuest> quests = new ArrayList<IQuest>();

        for (int i = 0; i < tagList.tagCount(); i++)
            quests.add(QuestAPI.questRegistry.get(new ResourceLocation(tagList.getStringTagAt(i))));

        return quests;
    }
}
