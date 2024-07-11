package fr.juststop.dev.kingdomuhc.items.waiting.host;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.game.GameConfig;
import fr.juststop.dev.kingdomuhc.managers.game.GameManager;
import fr.juststop.dev.kingdomuhc.utils.Cooldown;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import fr.juststop.dev.kingdomuhc.utils.items.GameItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Config extends GameItem {

    public Config() {
        super(new Language("items.config.name").getMessage(),
                new Language("items.config.desc").getAsList(),
                new Cooldown(0),
                new ItemStack(Material.DIODE),
                2,
                null
        );
    }

    @Override
    public void handleInteract(CustomItemInteractHandler handler) {
        super.handleInteract(handler);

        GameManager manager = KingdomUHC.getInstance().getGameManager();
        GameConfig config = manager.getConfig();


    }
}
