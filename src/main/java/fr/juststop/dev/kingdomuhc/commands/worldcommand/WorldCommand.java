package fr.juststop.dev.kingdomuhc.commands.worldcommand;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import org.bukkit.entity.Player;

import java.util.Map;

public class WorldCommand extends Command {

    public WorldCommand(String name) { super(name, new Language("commands.world.description").getMessage(), "blastmc.uhc.world"); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(new Language("prefix").getMessage())
                .addText(new Language("commands.world.message").getMessage())
                .sendMessage(player);
        for(Map.Entry<String, Command> set : KingdomUHC.getInstance().getCommands().get("world").getSubcommands().entrySet()) {
            if(player.hasPermission(set.getValue().getPermission())) player.sendMessage(new Language("format.list_key_value").getMessage().replace("%key%", set.getKey()).replace("%value%", set.getValue().getDescription()));
        }
    }
}
