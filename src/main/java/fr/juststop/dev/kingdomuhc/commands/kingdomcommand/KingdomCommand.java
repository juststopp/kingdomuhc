package fr.juststop.dev.kingdomuhc.commands.kingdomcommand;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class KingdomCommand extends Command {

    public KingdomCommand(String name) { super(name, Language.CMD_KINGDOM_DESC.getMessage(), ""); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(Language.PREFIX.getMessage())
                .addText(Language.CMD_KINGDOM_DESC.getMessage())
                .sendMessage(player);
    }

}
