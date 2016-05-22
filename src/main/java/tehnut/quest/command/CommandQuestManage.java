package tehnut.quest.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import tehnut.quest.api.IPlayerQuestData;
import tehnut.quest.api.IQuest;
import tehnut.quest.api.QuestAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandQuestManage extends CommandBase {

    private List<String> registeredQuests = new ArrayList<String>();

    public CommandQuestManage() {
        for (ResourceLocation key : QuestAPI.questRegistry.getRegistry().keySet())
            registeredQuests.add(key.toString());
    }

    @Override
    public String getCommandName() {
        return "questmanage";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<String>();
        aliases.add("qm");
        aliases.add("questManage");
        return aliases;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/questmanage <add|complete> <player> <questId>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
        List<String> tabCompletion = new ArrayList<String>();
        switch (args.length) {
            case 1:
                tabCompletion.add("add");
                tabCompletion.add("complete");
                break;
            case 2:
                tabCompletion.addAll(Arrays.asList(server.getPlayerList().getAllUsernames()));
                break;
            case 3:
                tabCompletion.addAll(registeredQuests);
        }
        return tabCompletion;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP player = getPlayer(server, sender, args[1]);
        IPlayerQuestData playerData = QuestAPI.playerHandler.getPlayerQuestData(player);

        if (args.length == 3) {
            ResourceLocation questKey = new ResourceLocation(args[2]);
            IQuest quest = QuestAPI.questRegistry.get(questKey);
            if (quest == null)
                return;

            if (args[0].equals("add")) {
                if (!playerData.getActiveQuests().contains(quest))
                    playerData.getActiveQuests().add(quest);
            } else if (args[0].equals("complete")) {
                if (!playerData.getCompletedQuests().contains(questKey))
                    QuestAPI.playerHandler.completeQuest(player, quest);
            }
        }
    }
}
