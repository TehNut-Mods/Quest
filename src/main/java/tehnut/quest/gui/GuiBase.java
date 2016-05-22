package tehnut.quest.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import tehnut.quest.util.TextHelper;

import java.util.List;

public class GuiBase extends GuiScreen {

    protected int guiLeft;
    protected int guiTop;
    protected int xSize;
    protected int ySize;

    @Override
    public void drawHoveringText(List<String> textLines, int x, int y) {
        super.drawHoveringText(TextHelper.localizeAll(textLines), x, y);
    }

    @Override
    public void drawHoveringText(List<String> textLines, int x, int y, FontRenderer font) {
        super.drawHoveringText(TextHelper.localizeAll(textLines), x, y, font);
    }
}
