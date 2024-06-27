package fr.juststop.dev.kingdomuhc.commands.kingdomcommand.subcommands;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
import org.bukkit.entity.Player;

public class RoleSubCommand extends Command {

    public RoleSubCommand(String name) { super(name, new Language("commands.kingdom.subcommands.role.description").getMessage(), ""); }

    @Override
    public void run(Player player, String[] args) {
        UhcPlayer uhcPlayer = KingdomUHC.getInstance().getGameManager().getPlayers().get(player);
        if(uhcPlayer.getRole() == null) {
            new MessageBuilder(new Language("prefix").getMessage())
                    .addText(new Language("roles.no_role").getMessage())
                    .sendMessage(player);
        } else {
            uhcPlayer.getRole().init(!uhcPlayer.getActionBar().isEmpty());
        }
    }
}
