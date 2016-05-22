package tehnut.quest.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import tehnut.quest.api.IPlayerQuestData;
import tehnut.quest.api.QuestAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandQuestClear extends CommandBase {

    @Override
    public String getCommandName() {
        return "questclear";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "questclear <player>";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<String>();
        aliases.add("qc");
        aliases.add("questClear");
        return aliases;
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
        List<String> tabCompletion = new ArrayList<String>();
        if (args.length == 2)
            tabCompletion.addAll(Arrays.asList(server.getPlayerList().getAllUsernames()));
        return tabCompletion;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP player = getPlayer(server, sender, args[0]);
        IPlayerQuestData playerData = QuestAPI.playerHandler.getPlayerQuestData(player);

        playerData.getActiveQuests().clear();
        playerData.getCompletedQuests().clear();
    }
}
