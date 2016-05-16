package tehnut.quest.impl;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.Loader;
import tehnut.quest.api.IQuest;
import tehnut.quest.api.QuestType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Quest implements IQuest {

    public static final String REGISTRY_NAME = "registryName";
    public static final String TITLE = "title";
    public static final String DISPLAY = "displayText";
    public static final String REWARDS = "rewards";
    public static final String QUEST_TYPE = "type";

    private ResourceLocation registryName;
    private ITextComponent title;
    private ITextComponent displayText;
    private QuestType type;
    private Set<ItemStack> rewards = new HashSet<ItemStack>();

    public Quest(ResourceLocation registryName, QuestType type) {
        this.registryName = registryName;
        this.type = type;
    }

    public Quest(String id, QuestType type) {
        this(new ResourceLocation(Loader.instance().activeModContainer().getModId(), id), type);
    }

    private Quest() {
        // No-op. Only for building from NBT.
    }

    @Override
    public ResourceLocation getRegistryName() {
        return registryName;
    }

    @Override
    public ITextComponent getTitle() {
        return title;
    }

    public IQuest setTitle(ITextComponent textComponent) {
        this.title = textComponent;
        return this;
    }

    @Override
    public ITextComponent getDisplayText() {
        return displayText;
    }

    public IQuest setDisplayText(ITextComponent textComponent) {
        this.displayText = textComponent;
        return this;
    }

    @Override
    public ItemStack[] getRewards() {
        return (ItemStack[]) rewards.toArray();
    }

    public IQuest addRewards(ItemStack ... stacks) {
        this.rewards.addAll(Arrays.asList(stacks));
        return this;
    }

    @Override
    public QuestType getType() {
        return type;
    }

    @Override
    public double getProgress(EntityPlayer player, BlockPos pos) {
        return 0;
    }

    @Override
    public NBTTagCompound seralizeNBT() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setString(REGISTRY_NAME, getRegistryName().toString());
        tagCompound.setString(TITLE, ITextComponent.Serializer.componentToJson(getTitle()));
        tagCompound.setString(DISPLAY, ITextComponent.Serializer.componentToJson(getDisplayText()));
        tagCompound.setString(QUEST_TYPE, getType().name());
        NBTTagList rewardList = new NBTTagList();
        for (ItemStack reward : rewards)
            rewardList.appendTag(reward.writeToNBT(new NBTTagCompound()));
        tagCompound.setTag(REWARDS, rewardList);
        return tagCompound;
    }

    @Override
    public IQuest deserializeNBT(NBTTagCompound tagCompound) {
        this.registryName = new ResourceLocation(tagCompound.getString(REGISTRY_NAME));
        this.title = ITextComponent.Serializer.jsonToComponent(tagCompound.getString(TITLE));
        this.displayText = ITextComponent.Serializer.jsonToComponent(tagCompound.getString(DISPLAY));
        this.type = QuestType.valueOf(tagCompound.getString(QUEST_TYPE));
        NBTTagList tagList = tagCompound.getTagList(REWARDS, 9);
        for (int i = 0; i < tagList.tagCount(); i++)
            rewards.add(ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(i)));
        return this;
    }

    public static IQuest buildFromNBT(NBTTagCompound tagCompound) {
        return new Quest().deserializeNBT(tagCompound);
    }
}
