package tehnut.quest.api.cap;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import tehnut.quest.api.IPlayerQuestData;

public interface IQuestReciever extends INBTSerializable<NBTTagCompound> {

    IPlayerQuestData getQuestData();
}
