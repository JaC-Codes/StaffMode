package me.koz.staffmode.inventorysee;

import me.koz.staffmode.CC;
import me.koz.staffmode.commands.Mode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class InvseeListener implements Listener {

    @EventHandler
    public void onRightClickPlayer(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof Player) {
            Player invsee = ((Player) event.getRightClicked()).getPlayer();
            if (event.getHand() == EquipmentSlot.OFF_HAND) return;
            event.getPlayer().getInventory().getItemInMainHand();
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) return;
            if (event.getPlayer().getInventory().getItemInMainHand()
                    .getItemMeta()
                    .getDisplayName()
                    .equalsIgnoreCase(CC.translate("&d&lInventory Viewer"))
                    && Mode.STAFF.contains(player.getUniqueId())) {
                assert invsee != null;
                player.performCommand("invsee " + invsee.getName());
            }
        }
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e) {
        if (Mode.STAFF.contains(e.getWhoClicked().getUniqueId())) {
            e.setCancelled(true);
        }
    }
}





