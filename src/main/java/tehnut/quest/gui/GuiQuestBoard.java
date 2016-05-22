package tehnut.quest.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import tehnut.quest.api.IPlayerQuestData;
import tehnut.quest.api.IQuest;
import tehnut.quest.api.IQuestProvider;
import tehnut.quest.api.QuestAPI;
import tehnut.quest.gui.button.ButtonAbandon;
import tehnut.quest.gui.button.ButtonAccept;
import tehnut.quest.network.MessageAcceptQuest;
import tehnut.quest.network.QuestPacketHandler;

import java.awt.*;
import java.io.IOException;

public class GuiQuestBoard extends GuiBase {

    private IQuestProvider questProvider;
    private IPlayerQuestData questData;

    public GuiQuestBoard(EntityPlayer player, IQuestProvider questProvider) {
        this.questProvider = questProvider;
        this.questData = QuestAPI.playerHandler.getPlayerQuestData(player);
    }

    @Override
    public void initGui() {
        super.initGui();

        buttonList.clear();

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;

        int off = 0;
        int id = 0;

        for (IQuest quest : questProvider.getAvailableQuests()) {
            if (questData.getActiveQuests().contains(quest))
                continue;
            buttonList.add(new ButtonAccept(++id, guiTop / 2, guiLeft / 2 + off, this, quest));
            off += 20;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int off = 0;
        for (GuiButton button : buttonList) {
            if (button instanceof ButtonAccept) {
                ButtonAccept accept = (ButtonAccept) button;
                fontRendererObj.drawString(accept.getQuest().getTitle().getFormattedText(), guiTop / 2, guiLeft / 2 + off, Color.BLACK.getRGB());
                off += 20;
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button instanceof ButtonAccept) {
            ButtonAccept accept = (ButtonAccept) button;
            QuestPacketHandler.INSTANCE.sendToServer(new MessageAcceptQuest(accept.getQuest()));
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
