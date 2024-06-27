package fr.juststop.dev.kingdomuhc.utils;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import org.bukkit.ChatColor;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Language {

    private static Map<String, Object> messages;
    private final String path;

    public Language(String path) {
        this.path = path;
    }

    @SuppressWarnings("unchecked")
    public static void init() {
        try {
            File languageFile = new File(KingdomUHC.getInstance().getDataFolder().getAbsolutePath() + File.separator + "languages" + File.separator + "messages.yml");
            if (!languageFile.exists()) {
                KingdomUHC.getInstance().getLog().fine(languageFile.getName() + " not found, extracting...");
                languageFile.getParentFile().mkdirs();
                Files.copy(Objects.requireNonNull(Language.class.getResourceAsStream("/languages/messages.yml")), languageFile.toPath());
            }

            Yaml yaml = new Yaml();
            InputStream inputStream = Files.newInputStream(Paths.get(languageFile.getAbsolutePath()));
            messages = (Map<String, Object>) yaml.load(inputStream);

            // Placeholder replacement can be done here if necessary
            replacePlaceholders(messages);

        } catch (Exception e) {
            KingdomUHC.getInstance().getLog().severe("There was an issue while loading the language file.");
        }
    }

    @SuppressWarnings("unchecked")
    private static void replacePlaceholders(Map<String, Object> messages) {
        for (Map.Entry<String, Object> entry : messages.entrySet()) {
            if (entry.getValue() instanceof String) {
                String message = (String) entry.getValue();
                message = message.replace("%prefix%", getPrefix());
                message = message.replace("%error_prefix%", getErrorPrefix());

                message = ChatColor.translateAlternateColorCodes('&', message);
                messages.put(entry.getKey(), message);
            } else if (entry.getValue() instanceof List) {
                List<String> messageList = (List<String>) entry.getValue();
                for (int i = 0; i < messageList.size(); i++) {
                    String message = messageList.get(i);
                    message = message.replace("%prefix%", getPrefix());
                    message = message.replace("%error_prefix%", getErrorPrefix());

                    message = ChatColor.translateAlternateColorCodes('&', message);
                    messageList.set(i, message);
                }
                messages.put(entry.getKey(), messageList);
            } else if (entry.getValue() instanceof Map) {
                replacePlaceholders((Map<String, Object>) entry.getValue());
            }
        }
    }

    private static String getPrefix() {
        return (String) messages.getOrDefault("prefix", "&7");
    }

    private static String getErrorPrefix() {
        return (String) messages.getOrDefault("error_prefix", "&c");
    }

    @SuppressWarnings("unchecked")
    public Object get(String path) {
        String[] keys = path.split("\\.");
        Map<String, Object> currentMap = messages;
        Object value = null;

        for (int i = 0; i < keys.length; i++) {
            value = currentMap.get(keys[i]);
            if (i < keys.length - 1) {
                if (value instanceof Map) {
                    currentMap = (Map<String, Object>) value;
                } else {
                    return null;
                }
            }
        }

        return value;
    }

    public String getMessage() {
        Object value = get(path);
        return value instanceof String ? (String) value : "&7Message \"&c" + path + "&7\" introuvable.";
    }

    @SuppressWarnings("unchecked")
    public String[] getAsList() {
        Object value = get(path);
        return value instanceof List ? ((List<String>) value).toArray(new String[0]) : null;
    }

    public static String[] splitLore(String lore) {
        return lore.split("\n");
    }

}
