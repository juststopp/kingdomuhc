package fr.juststop.dev.kingdomuhc.commands.worldcommand;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import org.bukkit.entity.Player;

import java.util.Map;

public class WorldCommand extends Command {

    public WorldCommand(String name) { super(name, Language.CMD_WORLD_DESC.getMessage(), "blastmc.uhc.world"); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(Language.PREFIX.getMessage())
                .addText(Language.CMD_WORLD_MSG.getMessage())
                .sendMessage(player);
        for(Map.Entry<String, Command> set : KingdomUHC.getInstance().getCommands().get("world").getSubcommands().entrySet()) {
            if(player.hasPermission(set.getValue().getPermission())) player.sendMessage(Language.FORMAT_LIST_KEY_VALUE.getMessage().replace("%key%", set.getKey()).replace("%value%", set.getValue().getDescription()));
        }
    }
}
