package tehnut.quest.api;

import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public interface IQuestProvider {

    List<IQuest> getAvailableQuests();

    boolean completeQuest(IQuest quest);

    NBTTagCompound writeNBT();

    List<IQuest> readNBT(NBTTagCompound tagCompound);
}
