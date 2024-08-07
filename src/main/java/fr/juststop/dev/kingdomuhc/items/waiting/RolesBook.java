package fr.juststop.dev.kingdomuhc.items.waiting;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.roles.Role;
import fr.juststop.dev.kingdomuhc.utils.Cooldown;
import fr.juststop.dev.kingdomuhc.utils.Language;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemClickHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.handlers.CustomItemInteractHandler;
import fr.juststop.dev.kingdomuhc.utils.inventory.inv.CustomInventory;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.CustomInventoryItem;
import fr.juststop.dev.kingdomuhc.utils.inventory.item.ItemBuilder;
import fr.juststop.dev.kingdomuhc.utils.items.GameItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RolesBook extends GameItem {

    public RolesBook() {
        super(new Language("items.roles_book.name").getMessage(),
                new Language("items.roles_book.desc").getAsList(),
                new Cooldown(0),
                new ItemStack(Material.BOOK),
                2,
                null);
    }

    @Override
    public void handleClick(CustomItemClickHandler handler) {  }

    @Override
    public void handleInteract(CustomItemInteractHandler handler) {

        List<CustomInventory> inventories = new ArrayList<>();
        CustomInventory inv = new CustomInventory(new Language("gui.list_roles.name").getMessage(), 54);

        int i = 0;
        final int[] currentPage = {0};

        for(Role role : KingdomUHC.getInstance().getGameManager().getRoles()) {

            inv.addItem(
                    new CustomInventoryItem(
                            new ItemBuilder(new ItemStack(Material.PAPER))
                                    .setName(role.getColoredName())
                                    .getItemStack(),
                            i
                    ) {
                        @Override
                        public void handleClick(CustomItemClickHandler handler) { handler.setCancelled(true); }

                        @Override
                        public void handleInteract(CustomItemInteractHandler handler) {}
                    }
            );

            i++;

            if(i == 53) {

                inv.addItem(
                        new CustomInventoryItem(
                                new ItemBuilder(new ItemStack(Material.ARROW))
                                        .setName(new Language("gui.new_page").getMessage())
                                        .getItemStack(),
                                i
                        ) {
                            @Override
                            public void handleClick(CustomItemClickHandler handler) {
                                handler.setCancelled(true);

                                currentPage[0]++;
                                if(currentPage[0] > inventories.size()) currentPage[0] = 0;

                                inventories.get(currentPage[0]).open((Player) handler.getEvent().getWhoClicked());
                            }

                            @Override
                            public void handleInteract(CustomItemInteractHandler handler) { }
                        }
                );

                i = 0;
                inventories.add(inv);
                inv = new CustomInventory(new Language("gui.list_roles.name").getMessage(), 54);

            }

        }

        inventories.add(inv);
        inventories.get(0).open(handler.getEvent().getPlayer());

    }
}
