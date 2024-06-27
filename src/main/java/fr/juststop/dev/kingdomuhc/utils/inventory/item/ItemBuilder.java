package fr.juststop.dev.kingdomuhc.utils.inventory.item;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;
    private List<String> lore = new ArrayList<>();
    private List<Enchantment> enchantments = new ArrayList<>();
    private final int amount = 1;

    public ItemBuilder(ItemStack item) {
        this.itemStack = item;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public List<String> getLore() {
        return lore;
    }

    public List<Enchantment> getEnchantments() {
        return enchantments;
    }

    public ItemBuilder addLoreLine(String line) {
        lore.add(line);

        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(lore);
        itemStack.setItemMeta(meta);

        return this;
    }

    public ItemBuilder addLoreLines(String[] lines) {
        for(String line : lines) {
            lore.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(Arrays.asList(lines));
        itemStack.setItemMeta(meta);

        return this;
    }

    public ItemBuilder removeLoreLine(String line) {
        lore.remove(line);

        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(lore);
        itemStack.setItemMeta(meta);

        return this;
    }

    public ItemBuilder removeLoreLine(int index) {
        lore.remove(index);

        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(lore);
        itemStack.setItemMeta(meta);

        return this;
    }

    public ItemBuilder setOwner(Player player) {
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setDisplayName(player.getName());
        meta.setOwner(Bukkit.getOfflinePlayer(player.getUniqueId()).toString());
        itemStack.setItemMeta(meta);

        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta meta = itemStack.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        itemStack.setItemMeta(meta);

        return this;
    }

}
