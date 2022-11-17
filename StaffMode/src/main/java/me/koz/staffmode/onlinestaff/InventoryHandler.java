package me.koz.staffmode.onlinestaff;

import me.koz.staffmode.CC;
import me.koz.staffmode.StaffMode;
import me.koz.staffmode.commands.Mode;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;


public class InventoryHandler {

    private final StaffMode staffMode;
    private final Inventory onlineStaff;

    public InventoryHandler(StaffMode staffMode) {
        this.staffMode = staffMode;
        onlineStaff = Bukkit.createInventory(null, 36, CC.translate("&b&lOnline Staff"));

        ItemStack glasspane = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, (short) 0);
        int slot;
        while ((slot = onlineStaff.firstEmpty()) != -1) onlineStaff.setItem(slot, glasspane);

    }

    public void staffHead(Player player) {
        for (Player playerfor : Bukkit.getServer().getOnlinePlayers()) {
            for (int i = 10; i < 19; i++) {
                if (Mode.STAFF.contains(playerfor.getUniqueId())) {
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
                    SkullMeta metaskull = (SkullMeta) skull.getItemMeta();
                    metaskull.setOwningPlayer(playerfor);
                    metaskull.setDisplayName(CC.translate("&6&l" + playerfor.getName()));
                    skull.setItemMeta(metaskull);
                    if (!onlineStaff.contains(skull)) {
                        onlineStaff.setItem(i, skull);
                    }
                }
            }
            for (int i = 19; i < 25; i++) {
                if (Mode.STAFF.contains(playerfor.getUniqueId())) {
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
                    SkullMeta metaskull = (SkullMeta) skull.getItemMeta();
                    metaskull.setOwningPlayer(playerfor);
                    metaskull.setDisplayName(CC.translate("&6&l" + playerfor.getName()));
                    skull.setItemMeta(metaskull);
                    if (!onlineStaff.contains(skull)) {
                        onlineStaff.setItem(i, skull);
                    }
                }
            }
            player.openInventory(onlineStaff);
        }
    }
}
