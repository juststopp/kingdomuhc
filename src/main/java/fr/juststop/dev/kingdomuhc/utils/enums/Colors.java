package fr.juststop.dev.kingdomuhc.utils.enums;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public enum Colors {

    RED(ChatColor.RED, DyeColor.RED),
    DARK_RED(ChatColor.DARK_RED, DyeColor.RED),
    LIGHT_BLUE(ChatColor.AQUA, DyeColor.LIGHT_BLUE),
    BLUE(ChatColor.BLUE, DyeColor.CYAN),
    DARK_BLUE(ChatColor.DARK_BLUE, DyeColor.BLUE),
    YELLOW(ChatColor.YELLOW, DyeColor.YELLOW),
    ORANGE(ChatColor.GOLD, DyeColor.ORANGE),
    GREEN(ChatColor.GREEN, DyeColor.GREEN),
    DARK_GREEN(ChatColor.DARK_GREEN, DyeColor.GREEN),
    PURPLE(ChatColor.DARK_PURPLE, DyeColor.PURPLE),
    PINK(ChatColor.LIGHT_PURPLE, DyeColor.PINK),
    BLACK(ChatColor.BLACK, DyeColor.BLACK),
    GRAY(ChatColor.GRAY, DyeColor.GRAY),
    DARK_GRAY(ChatColor.DARK_GRAY, DyeColor.SILVER);

    private final ChatColor chatColor;
    private final DyeColor dyeColor;

    Colors(ChatColor chatColor, DyeColor dyeColor) {
        this.chatColor = chatColor;
        this.dyeColor = dyeColor;
    }

    public ChatColor getChatColor() { return chatColor; }
    public DyeColor getDyeColor() { return dyeColor; }
}
