package tehnut.quest.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import tehnut.quest.api.IQuestProvider;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        switch (ID) {
            case 0: {
                if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof IQuestProvider)
                    return new GuiQuestBoard(player, (IQuestProvider) world.getTileEntity(pos));
            }
            case 1: {
                return new GuiActiveQuests(player);
            }
        }

        return null;
    }
}
