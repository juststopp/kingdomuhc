package fr.juststop.dev.kingdomuhc.commands;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.Language;
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
                    new MessageBuilder(new Language("prefix").getMessage())
                            .addText(new Language("commands.list_subcommands").getMessage().replace("%name%", this.name))
                            .sendMessage(player);
                    for(Map.Entry<String, Command> set : getSubcommands().entrySet()) {
                        if(!Objects.equals(set.getValue().permission, "") && player.hasPermission(set.getValue().permission)) player.sendMessage(new Language("format.list_key_value").getMessage().replace("%key%", set.getKey()).replace("%value%", set.getValue().getDescription()));
                    }
                    return false;
                }

                while(true) {
                    if(command.getSubcommand(a[0]) == null) {
                        if(Objects.equals(this.permission, "") || player.hasPermission(this.permission)) command.run(player, a);
                        else new MessageBuilder(new Language("error_prefix").getMessage())
                                .addText(new Language("commands.no_perm").getMessage())
                                .sendMessage(player);
                        break;
                    }

                    if(Objects.equals(this.permission, "") && !player.hasPermission(command.permission)) {
                        new MessageBuilder(new Language("error_prefix").getMessage())
                                .addText(new Language("commands.no_perm").getMessage())
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
