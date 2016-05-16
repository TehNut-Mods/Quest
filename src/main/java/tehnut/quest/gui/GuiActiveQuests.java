package tehnut.quest.gui;

import net.minecraft.entity.player.EntityPlayer;
import tehnut.quest.api.IPlayerQuestData;
import tehnut.quest.api.QuestAPI;

public class GuiActiveQuests extends GuiBase {

    private IPlayerQuestData questData;

    public GuiActiveQuests(EntityPlayer player) {
        questData = QuestAPI.playerHandler.getPlayerQuestData(player);
    }

    @Override
    public void initGui() {
        super.initGui();

        buttonList.clear();

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {



        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
