package fr.juststop.dev.kingdomuhc.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageBuilder {

    private final TextComponent text;

    public MessageBuilder(String prefix) { text = new TextComponent(ChatColor.translateAlternateColorCodes('&', prefix)); }

    public MessageBuilder addClickMessage(String msg, ClickEvent.Action action, String actionValue) {
        TextComponent add = new TextComponent(ChatColor.translateAlternateColorCodes('&', msg));
        add.setClickEvent(new ClickEvent(action, ChatColor.translateAlternateColorCodes('&', actionValue)));
        text.addExtra(add);
        return this;
    }

    public MessageBuilder addHoverMessage(String msg, HoverEvent.Action action, String actionValue) {
        TextComponent add = new TextComponent(ChatColor.translateAlternateColorCodes('&', msg));
        add.setHoverEvent(new HoverEvent(action, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', actionValue)).create()));
        text.addExtra(add);
        return this;
    }

    public MessageBuilder addClickAndHoverMessage(String msg, ClickEvent.Action clickAction, HoverEvent.Action hoverAction, String clickActionValue, String hoverActionValue) {
        TextComponent add = new TextComponent(ChatColor.translateAlternateColorCodes('&', msg));
        add.setClickEvent(new ClickEvent(clickAction, ChatColor.translateAlternateColorCodes('&', clickActionValue)));
        add.setHoverEvent(new HoverEvent(hoverAction, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', hoverActionValue)).create()));
        text.addExtra(add);
        return this;
    }

    public MessageBuilder addText(String msg) {
        text.addExtra(new TextComponent(ChatColor.translateAlternateColorCodes('&', msg)));
        return this;
    }

    public TextComponent getText() { return text; }

    public void sendMessage(Player player ) { player.spigot().sendMessage(text); }

}
