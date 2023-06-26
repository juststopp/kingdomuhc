package fr.juststop.dev.kingdomuhc.commands;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KingdomCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            new MessageBuilder(KingdomUHC.getPrefix()).addText("&7Bienvenue sur le serveur du &7Kingdom UHC&7.")
                    .sendMessage(player);
        }
        return false;
    }

}
