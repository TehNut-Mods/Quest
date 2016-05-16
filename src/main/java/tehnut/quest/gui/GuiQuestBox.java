package tehnut.quest.gui;

import tehnut.quest.api.IQuest;

public class GuiQuestBox {

    private IQuest quest;
    private GuiBase parent;

    public GuiQuestBox(IQuest quest, GuiBase guiBase) {
        this.quest = quest;
        this.parent = guiBase;
    }
}
