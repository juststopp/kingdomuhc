package fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import org.bukkit.entity.Player;

public class ConfigSubCommand extends Command {


    public ConfigSubCommand(String name) { super(name, "&7Permet de voir la &6configuraion &7de la partie.", ""); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(KingdomUHC.getInstance().getPrefix())
                .addText("&cCette commande n'a pas encore été implémentée.")
                .sendMessage(player);
    }
}
