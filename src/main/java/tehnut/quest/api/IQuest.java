package tehnut.quest.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

public interface IQuest {

    ResourceLocation getRegistryName();

    ITextComponent getTitle();

    ITextComponent getBody();

    ItemStack[] getRewards();

    QuestType getType();

    double getProgress(EntityPlayer player, BlockPos pos);

    NBTTagCompound seralizeNBT();

    IQuest deserializeNBT(NBTTagCompound tagCompound);
}
