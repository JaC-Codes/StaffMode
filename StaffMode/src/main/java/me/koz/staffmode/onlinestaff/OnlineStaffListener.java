package me.koz.staffmode.onlinestaff;

import me.koz.staffmode.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OnlineStaffListener implements Listener {

    @EventHandler
    public void playerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
            if (item == null || item.getType() == Material.AIR) return;
            if (!item.hasItemMeta()) return;
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (item.getItemMeta().getDisplayName().equals(CC.translate("&b&lOnline Staff"))) {
                    p.performCommand("onlinestaff");
                    e.setCancelled(true);
                }
            }
        }


    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(CC.translate("&b&lOnline Staff"))) {
            event.setCancelled(true);
        }
    }
}
