package fr.juststop.dev.kingdomuhc.commands.kingdomcommand;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import org.bukkit.entity.Player;

public class KingdomCommand extends Command {

    public KingdomCommand(String name) { super(name, "&7Ceci est la commande &6principale &7du mode de jeu. Faîtes &6/kingdom help §7pour voir la liste des commandes.", ""); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(KingdomUHC.getInstance().getPrefix())
                .addText(this.getDescription())
                .sendMessage(player);
    }

}
