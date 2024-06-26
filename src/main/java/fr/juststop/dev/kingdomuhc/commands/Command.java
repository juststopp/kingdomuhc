package fr.juststop.dev.kingdomuhc.commands;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class Command implements CommandExecutor, TabCompleter {

    private final String name;
    private final String description;
    private final String permission;
    private final HashMap<String, Command> subcommands;

    public Command(String name, String description, String permission) {
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.subcommands = new HashMap<>();
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPermission() { return permission; }
    public HashMap<String, Command> getSubcommands() { return subcommands; }

    public Command addSubcommand(Command command) {
        subcommands.put(command.name, command);
        return this;
    }

    public Command removeSubcommand(String name) {
        subcommands.remove(name);
        return this;
    }

    public void register() {
        KingdomUHC.getInstance().getCommand(this.name).setExecutor(this);
        KingdomUHC.getInstance().getCommand(this.name).setTabCompleter(this);
        KingdomUHC.getInstance().getCommands().put(this.name, this);
    }

    public Command getSubcommand(String name) { return subcommands.get(name); }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String arg, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length > 0) {
                Command command = getSubcommand(args[0]);
                String[] a = Arrays.copyOfRange(args, 1, arg.length());

                if(command == null) {
                    new MessageBuilder(KingdomUHC.getInstance().getPrefix())
                            .addText(Language.CMD_LIST_SUBCOMMANDS.getMessage().replace("%name%", this.name))
                            .sendMessage(player);
                    for(Map.Entry<String, Command> set : getSubcommands().entrySet()) {
                        if(player.hasPermission(set.getValue().permission)) player.sendMessage(Language.FORMAT_LIST_KEY_VALUE.getMessage().replace("%key%", set.getKey()).replace("%value%", set.getValue().getDescription()));
                    }
                    return false;
                }

                while(true) {
                    if(command.getSubcommand(a[0]) == null) {
                        if(player.hasPermission(this.permission)) command.run(player, a);
                        else new MessageBuilder(Language.ERROR_PREFIX.getMessage())
                                .addText(Language.CMD_NOPERM.getMessage())
                                .sendMessage(player);
                        break;
                    }

                    if(!player.hasPermission(command.permission)) {
                        new MessageBuilder(Language.ERROR_PREFIX.getMessage())
                                .addText(Language.CMD_NOPERM.getMessage())
                                .sendMessage(player);
                        break;
                    }

                    command = command.getSubcommand(a[0]);
                    a = Arrays.copyOfRange(a, 1, a.length);
                }
            } else {
                this.run(player, args);
            }
        }
        return false;
    }

    public void run(Player player, String[] args) {}

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command cmd, String arg, String[] args) {

        if(args[0].isEmpty() || args.length < 2) return new ArrayList<>(getSubcommands().keySet());

        return new ArrayList<>(
                getSubcommand(args[args.length - 2]).getSubcommands().values().isEmpty()
                        ? getSubcommands().keySet()
                        : getSubcommand(args[args.length - 2]).getSubcommands().keySet()
        );
    }
}
