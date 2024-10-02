package fr.juststop.dev.kingdomuhc.items.waiting.host.Inventories;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.game.GameConfig;
import fr.juststop.dev.kingdomuhc.managers.game.GameManager;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.MessageBuilder;
import fr.juststop.dev.kingdomuhc.utils.enums.GameStatus;
import fr.juststop.dev.kingdomuhc.utils.enums.MapStatus;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.inv.CustomInventory;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.CustomInventoryItem;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.InventoryItem;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MainInventory {

    private final GameManager gameManager;
    private final GameConfig config;

    public MainInventory(GameManager gameManager, GameConfig config) {
        this.gameManager = gameManager;
        this.config = config;
    }

    public CustomInventory createInventory() {
        CustomInventory Inventory = new CustomInventory(new Language("gui.config.main.name").getMessage(), 54);
        Inventory.addItem(
                new CustomInventoryItem(
                    new ItemBuilder(new ItemStack(Material.STAINED_GLASS, 1, (short) 5))
                            .setName(new Language("gui.config.main.items.start").getMessage())
                            .getItemStack(),
                    49
            ) {
                @Override
                public void handleClick(CustomItemClickHandler handler) {
                    if(handler.isCancelled()) return;
                    handler.setCancelled(true);

                    if(config.GAME_STATUS != GameStatus.WAITING) {
                        new MessageBuilder(new Language("error_prefix").getMessage())
                                .addText(new Language("game.errors.started").getMessage())
                                .sendMessage((Player) handler.getEvent().getWhoClicked());
                        return;
                    }

                    if(config.MAP_STATUS != MapStatus.LOADED) {
                        new MessageBuilder(new Language("error_prefix").getMessage())
                                .addText(new Language("game.errors.map_unloaded").getMessage())
                                .sendMessage((Player) handler.getEvent().getWhoClicked());

                        return;
                    }

                    gameManager.start();
                }

                @Override
                public void handleInteract(CustomItemInteractHandler handler) {}
            });

        return Inventory;
    }

}
