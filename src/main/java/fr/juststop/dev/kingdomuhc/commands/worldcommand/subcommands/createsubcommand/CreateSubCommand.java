package fr.juststop.dev.kingdomuhc.commands.worldcommand.subcommands.createsubcommand;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.commands.Command;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.entity.Player;

public class CreateSubCommand extends Command {

    public CreateSubCommand(String name) { super(name, Language.CMD_WORLD_SUB_CREATE_DESC.getMessage(), "blastmc.uhc.world.create"); }

    @Override
    public void run(Player player, String[] args) {
        new MessageBuilder(KingdomUHC.getInstance().getPrefix())
                .addText(Language.CMD_WORLD_SUB_CREATE_SURE.getMessage())
                .addClickAndHoverMessage(Language.CMD_WORLD_SUB_CREATE_YES.getMessage(), ClickEvent.Action.RUN_COMMAND, HoverEvent.Action.SHOW_TEXT, "/world create confirm", Language.CMD_WORLD_SUB_CREATE_YES.getMessage())
                .sendMessage(player);
    }
}
