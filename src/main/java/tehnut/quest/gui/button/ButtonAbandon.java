package tehnut.quest.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import tehnut.quest.QuestBoard;
import tehnut.quest.gui.GuiBase;
import tehnut.quest.util.GuiHelper;

import java.util.Collections;

public class ButtonAbandon extends GuiButtonExt {

    private GuiBase parent;

    public ButtonAbandon(int id, int xPos, int yPos, GuiBase parent) {
        super(id, xPos, yPos, "");

        this.parent = parent;

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
            mc.getTextureManager().bindTexture(new ResourceLocation(QuestBoard.MODID, "gui/button_abandon"));
            if (GuiHelper.isMouseBetween(mouseX, mouseY, xPosition, yPosition, width, height)) {
                drawTexturedModalRect(xPosition, yPosition + 1, 47, 201, 18, 10);
                parent.drawHoveringText(Collections.singletonList("button.quest.abandon"), mouseX, mouseY);
            } else {
                drawTexturedModalRect(xPosition, yPosition, 47, 201, 18, 10);
            }
        }
    }
}
