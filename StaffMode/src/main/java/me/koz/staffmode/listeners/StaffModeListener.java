package me.koz.staffmode.listeners;

import me.koz.staffmode.CC;
import me.koz.staffmode.commands.Mode;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class StaffModeListener implements Listener {


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (Mode.STAFF.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(CC.translate("&cYou can not break blocks in staff mode."));
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (Mode.STAFF.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(CC.translate("&cYou can not place blocks in staff mode."));

        }
    }

    @EventHandler
    public void DropItemEvent(PlayerDropItemEvent e) {
        if (Mode.STAFF.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e) {
        if (Mode.STAFF.contains(e.getWhoClicked().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(final PlayerInteractEntityEvent e) {
        final Player p = e.getPlayer();
        if (!Mode.STAFF.contains(e.getPlayer().getUniqueId())) return;
        if (!(e.getRightClicked() instanceof HumanEntity)) return;
        ItemStack item = (p.getEquipment()).getItemInMainHand();
        if (!(item.getItemMeta()).hasDisplayName()) return;
        if (!item.hasItemMeta()) return;
        if (!e.getHand().equals(EquipmentSlot.OFF_HAND)) return;
        Player target = (Player) e.getRightClicked();
        if (item.getItemMeta().getDisplayName().equals(CC.translate("&e&lFreeze"))) {
            e.getPlayer().performCommand("freeze " + target.getDisplayName());
        }


    }

    @EventHandler
    public void PickupItemEvent(PlayerPickupItemEvent e) {
        if (Mode.STAFF.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

}






