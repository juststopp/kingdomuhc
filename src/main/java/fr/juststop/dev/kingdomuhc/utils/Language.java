package fr.juststop.dev.kingdomuhc.utils;

import de.leonhard.storage.Yaml;
import fr.juststop.dev.kingdomuhc.KingdomUHC;
import org.bukkit.ChatColor;

import java.io.File;
import java.nio.file.Files;

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
    CMD_WORLD_SUB_LOAD_ENDED("commands.world.subcommands.load.ended");

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
                String newMessage = languageYaml.getOrDefault(message.path, "&7Desription \"&c"+message.path+"&7\"introuvable.");
                newMessage = newMessage.replace("%prefix%", PREFIX.message);
                newMessage = newMessage.replace("%error_prefix%", ERROR_PREFIX.message);

                message.message = ChatColor.translateAlternateColorCodes('&', newMessage);
            }
        } catch(Exception e) {
            e.printStackTrace();
            KingdomUHC.getInstance().getLog().severe("There was an issue while loading the language file.");
        }
    }

    private final String path;
    private String message;

    Language(String path) {
        this.path = path;
        this.message = "not loaded";
    }

    public String getMessage() { return message; }

    public static String[] splitLore(String lore) {
        return lore.split("\n");
    }
}
