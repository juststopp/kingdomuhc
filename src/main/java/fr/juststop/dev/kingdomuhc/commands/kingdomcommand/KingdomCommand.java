package fr.juststop.dev.kingdomuhc.commands.kingdomcommand;

import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import org.bukkit.entity.Player;

public class KingdomCommand extends Command {

    public KingdomCommand(String name) { super(name, new Language("commands.kingdom.description").getMessage(), ""); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(new Language("prefix").getMessage())
                .addText(new Language("commands.kingdom.description").getMessage())
                .sendMessage(player);
    }

}
