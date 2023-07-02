package fr.juststop.dev.kingdomuhc.utils.inventory.handlers;

import fr.juststop.dev.kingdomuhc.utils.inventory.inv.CustomInventory;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public abstract class InventoryClickHandler {

    public abstract void handle(ClickHandler clickHandler);

    public static class ClickHandler {
        private final CustomInventory customInventory;
        private final InventoryClickEvent event;

        public ClickHandler(CustomInventory customInventory, InventoryClickEvent event) {
            this.customInventory = customInventory;
            this.event = event;
        }

        public InventoryAction getAction() { return event.getAction(); }
        public ClickType getClick() { return event.getClick(); }
        public ItemStack getCurrentItem() { return event.getCurrentItem(); }
        public ItemStack getCursor() { return event.getCursor(); }
        public int getHotbarButton() { return event.getHotbarButton(); }
        public int getRawSlot() { return event.getRawSlot(); }
        public int getSlot() { return event.getSlot(); }

        public InventoryClickEvent getEvent() { return event; }

        public boolean isLeftClick() {
            return event.isLeftClick();
        }

        public boolean isRightClick() {
            return event.isRightClick();
        }

        public boolean isShiftClick() {
            return event.isShiftClick();
        }

        public boolean isCancelled() { return event.isCancelled();}

        public void setCurrentItem(ItemStack itemStack) {
            event.setCurrentItem(itemStack);
        }

        public void setCancelled(boolean b) {
            event.setCancelled(b);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (!(obj instanceof ClickHandler))
                return false;

            ClickHandler clickHandler = (ClickHandler) obj;
            return super.equals(obj) && event.equals(clickHandler.event);
        }
    }

    public static class InteractHandler {
        private final PlayerInteractEvent event;

        public InteractHandler(PlayerInteractEvent event) {
            this.event = event;
        }

        public Action getAction() { return event.getAction(); }

        public ItemStack getItem() { return event.getItem(); }

        public Block getClickedBlock() { return event.getClickedBlock(); }

        public boolean isCancelled() { return event.isCancelled(); }

        public void setCancelled(boolean b) { event.setCancelled(b); }

        public PlayerInteractEvent getEvent() { return event; }

        @Override
        public boolean equals(Object obj) {
            if(obj == this)
                return true;
            if (!(obj instanceof InteractHandler))
                return false;

            InteractHandler interactHandler = (InteractHandler) obj;
            return super.equals(obj) && event.equals(interactHandler.event);
        }
    }

}
