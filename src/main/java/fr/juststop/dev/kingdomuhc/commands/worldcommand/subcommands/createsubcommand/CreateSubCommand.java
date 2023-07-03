package fr.juststop.dev.kingdomuhc.commands.worldcommand.subcommands.createsubcommand;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.entity.Player;

public class CreateSubCommand extends Command {

    public CreateSubCommand(String name) { super(name, "&7Permet de générer un nouveau monde.", "blastmc.uhc.world.create"); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(KingdomUHC.getInstance().getPrefix())
                .addText("&7Etes vous sûr de vouloir régénérer le monde ?")
                .sendMessage(player);

        player.sendMessage("§e");

        new MessageBuilder("")
                .addText("&8︲ ")
                .addClickAndHoverMessage("&aOUI", ClickEvent.Action.RUN_COMMAND, HoverEvent.Action.SHOW_TEXT, "/world create confirm", "&aOui&7, régénérer le monde.")
                .sendMessage(player);
    }
}
