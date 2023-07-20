package fr.juststop.dev.kingdomuhc.utils.enums;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

import java.awt.*;

public enum Colors {

    RED(ChatColor.RED, DyeColor.RED, Color.RED),
    DARK_RED(ChatColor.DARK_RED, DyeColor.RED, Color.RED),
    LIGHT_BLUE(ChatColor.AQUA, DyeColor.LIGHT_BLUE, Color.BLUE),
    BLUE(ChatColor.BLUE, DyeColor.CYAN, Color.BLUE),
    DARK_BLUE(ChatColor.DARK_BLUE, DyeColor.BLUE, Color.BLUE),
    YELLOW(ChatColor.YELLOW, DyeColor.YELLOW, Color.YELLOW),
    ORANGE(ChatColor.GOLD, DyeColor.ORANGE, Color.ORANGE),
    GREEN(ChatColor.GREEN, DyeColor.GREEN, Color.GREEN),
    DARK_GREEN(ChatColor.DARK_GREEN, DyeColor.GREEN, Color.GREEN),
    PURPLE(ChatColor.DARK_PURPLE, DyeColor.PURPLE, Color.PINK),
    PINK(ChatColor.LIGHT_PURPLE, DyeColor.PINK, Color.PINK),
    BLACK(ChatColor.BLACK, DyeColor.BLACK, Color.BLACK),
    GRAY(ChatColor.GRAY, DyeColor.GRAY, Color.GRAY),
    DARK_GRAY(ChatColor.DARK_GRAY, DyeColor.SILVER, Color.DARK_GRAY);

    private final ChatColor chatColor;
    private final DyeColor dyeColor;
    private final Color bukkitColor;

    Colors(ChatColor chatColor, DyeColor dyeColor, Color bukkitColor) {
        this.chatColor = chatColor;
        this.dyeColor = dyeColor;
        this.bukkitColor = bukkitColor;
    }

    public ChatColor getChatColor() { return chatColor; }
    public DyeColor getDyeColor() { return dyeColor; }
    public Color getBukkitColor() { return bukkitColor; }
}
