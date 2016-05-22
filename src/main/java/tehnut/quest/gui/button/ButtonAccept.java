package tehnut.quest.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import tehnut.quest.api.IQuest;
import tehnut.quest.gui.GuiBase;
import tehnut.quest.gui.GuiWidget;
import tehnut.quest.util.GuiHelper;

import java.util.Collections;

public class ButtonAccept extends GuiButton {

    private final GuiBase parent;
    private final IQuest quest;

    public ButtonAccept(int id, int xPos, int yPos, GuiBase parent, IQuest quest) {
        super(id, xPos, yPos, "");

        this.parent = parent;
        this.quest = quest;

        width = 16;
        height = 16;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.enableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            if (GuiHelper.isMouseBetween(mouseX, mouseY, xPosition, yPosition, width, height)) {
                GuiWidget.ACCEPT_QUEST.draw(zLevel, xPosition, yPosition + 1);
                parent.drawHoveringText(Collections.singletonList("button.quest.accept"), mouseX, mouseY);
            } else {
                GuiWidget.ACCEPT_QUEST.draw(zLevel, xPosition, yPosition);
            }
        }
    }

    public IQuest getQuest() {
        return quest;
    }
}
