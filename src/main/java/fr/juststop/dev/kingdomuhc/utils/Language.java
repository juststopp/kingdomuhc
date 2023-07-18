package fr.juststop.dev.kingdomuhc.utils;

import de.leonhard.storage.Yaml;
import fr.juststop.dev.kingdomuhc.KingdomUHC;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public enum Language {

    PREFIX("prefix"),
    ERROR_PREFIX("error_prefix"),

    PLAYER_JOIN("server.events.player.join"),
    PLAYER_QUIT("server.events.player.quit"),

    GAME_NEW_HOST("game.host.you_are_new"),
    GAME_START("game.start"),
    GAME_STOP("game.stop"),

    FORMAT_LIST_KEY_VALUE("format.list_key_value"),
    FORMAT_LIST_VALUE("format.list_key_value"),

    CMD_NOT_IMPLEMENTED("commands.not_implemented"),
    CMD_LIST_SUBCOMMANDS("commands.list_subcommands"),
    CMD_NOPERM("commands.no_perm"),

    CMD_KINGDOM_DESC("commands.kingdom.description"),
    CMD_KINGDOM_SUB_CONFIG_DESC("commands.kingdom.subcommands.config.description"),
    CMD_KINGDOM_SUB_HELP_DESC("commands.kingdom.subcommands.help.description"),
    CMD_KINGDOM_SUB_HELP_MESSAGE("commands.kingdom.subcommands.help.message"),

    CMD_WORLD_DESC("commands.world.description"),
    CMD_WORLD_MSG("commands.world.message"),

    CMD_WORLD_SUB_CREATE_DESC("commands.world.subcommands.create.description"),
    CMD_WORLD_SUB_CREATE_SURE("commands.world.subcommands.create.sure"),
    CMD_WORLD_SUB_CREATE_YES("commands.world.subcommands.create.yes"),

    CMD_WORLD_SUB_CREATE_SUB_CONFIRM_DESC("commands.world.subcommands.create.subcommands.confirm.description"),
    CMD_WORLD_SUB_CREATE_SUB_CONFIRM_CREATING("commands.world.subcommands.create.subcommands.confirm.creating"),
    CMD_WORLD_SUB_CREATE_SUB_CONFIRM_CREATED("commands.world.subcommands.create.subcommands.confirm.created"),
    CMD_WORLD_SUB_CREATE_SUB_CONFIRM_ACTIONS_DELETE_AND_CREATE("commands.world.subcommands.create.subcommands.confirm.actions.delete_and_create"),
    CMD_WORLD_SUB_CREATE_SUB_CONFIRM_ACTIONS_SAVE_AND_LOAD("commands.world.subcommands.create.subcommands.confirm.actions.save_and_load"),

    CMD_WORLD_SUB_LOAD_DESC("commands.world.subcommands.load.description"),
    CMD_WORLD_SUB_LOAD_STARTING("commands.world.subcommands.load.starting"),
    CMD_WORLD_SUB_LOAD_ENDED("commands.world.subcommands.load.ended"),

    CMD_LANGUAGE_DESC("commands.language.description"),
    CMD_LANGUAGE_SUB_RELOAD_DESC("commands.language.subcommands.reload.descrpition"),
    CMD_LANGUAGE_SUB_RELOAD_RELOADED("commands.language.subcommands.reload.reloaded"),

    ITEM_ACTIVATED("items.activated"),
    ITEM_FINISHED("items.finished"),
    ITEM_NOT_YOURS("items.not_yours"),

    ITEM_ROLES_BOOK_NAME("items.roles_book.name"),
    ITEM_ROLES_BOOK_DESC("items.roles_book.desc"),
    ITEM_SHIN_100_HOMMES_NAME("items.roles.ri_shin.100hommes.name"),
    ITEM_SHIN_100_HOMMES_DESC("items.roles.ri_shin.100hommes.desc"),

    GUI_LIST_ROLES_NAME("gui.list_roles.name"),
    GUI_NEXT_PAGE("gui.next_page"),

    ROLES_QIN_SHIN_NAME("roles.qin.shin.name"),
    ROLES_QIN_SHIN_LONG_DESC("roles.qin.shin.long_desc"),
    ROLES_QIN_SHIN_SHORT_DESC("roles.qin.shin.short_desc");

    public static void init() {
        try {
            File languageFile = new File(KingdomUHC.getInstance().getDataFolder().getAbsolutePath() + File.separator + "languages" + File.separator + "messages.yml");
            if(!languageFile.exists()) {
                KingdomUHC.getInstance().getLog().fine(languageFile.getName() + " not found, extracting...");
                languageFile.getParentFile().mkdirs();
                Files.copy(Language.class.getResourceAsStream("/languages/messages.yml"), languageFile.toPath());
            }

            Yaml languageYaml = new Yaml("messages", "plugins/KingdomUHC/languages");
            for(Language message : Language.values()) {
                Object newMessage = languageYaml.getOrDefault(message.path, "&7Desription \"&c"+message.path+"&7\"introuvable.");

                if(newMessage instanceof String) {
                    newMessage = newMessage.toString().replace("%prefix%", PREFIX.message.toString());
                    newMessage = newMessage.toString().replace("%error_prefix%", ERROR_PREFIX.message.toString());

                    message.message = ChatColor.translateAlternateColorCodes('&', newMessage.toString());
                } else {
                    List<String> finalMessage = new ArrayList<>();
                    for(String m : (List<String>) newMessage) {
                        m = m.replace("%prefix%", PREFIX.message.toString());
                        m = m.replace("%error_prefix%", ERROR_PREFIX.message.toString());

                        finalMessage.add(ChatColor.translateAlternateColorCodes('&', m));
                    }

                    message.message = finalMessage;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            KingdomUHC.getInstance().getLog().severe("There was an issue while loading the language file.");
        }
    }

    private final String path;
    private Object message;

    Language(String path) {
        this.path = path;
        this.message = "not loaded";
    }

    public String getMessage() { return message.toString(); }

    public String[] getAsLore() {
        return ((List<String>) message).toArray(new String[0]);
    }

    public static String[] splitLore(String lore) {
        return lore.split("\n");
    }
}
