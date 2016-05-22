package tehnut.quest.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import tehnut.quest.QuestBoard;

public enum GuiWidget {
    
    ABANDON_QUEST(0, 0, 16, 16),
    ACCEPT_QUEST(16, 0, 16, 16),
    ;
    
    public final ResourceLocation texture = new ResourceLocation(QuestBoard.MODID, "textures/gui/widgets.png");
    public final int u;
    public final int v;
    public final int width;
    public final int height;

    GuiWidget(int u, int v, int width, int height) {
        this.u = u;
        this.v = v;
        this.width = width;
        this.height = height;
    }

    public void draw(double zLevel, int x, int y) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos((double) x, (double)(y + height), zLevel).tex((double)((float)(u) * f), (double)((float)(v + height) * f1)).endVertex();
        vertexbuffer.pos((double) (x + width), (double)(y + height), zLevel).tex((double)((float)(u + width) * f), (double)((float)(v + height) * f1)).endVertex();
        vertexbuffer.pos((double) (x + width), (double)(y), zLevel).tex((double)((float)(u + width) * f), (double)((float)(v) * f1)).endVertex();
        vertexbuffer.pos((double) x, (double)(y), zLevel).tex((double)((float)(u) * f), (double)((float)(v) * f1)).endVertex();
        tessellator.draw();
    }
}
