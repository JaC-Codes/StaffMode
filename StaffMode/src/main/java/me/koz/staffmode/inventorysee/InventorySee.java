package me.koz.staffmode.inventorysee;

import me.koz.staffmode.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

@SuppressWarnings("ALL")
public class InventorySee implements CommandExecutor {

    public static ArrayList<UUID> InvTarget = new ArrayList<>();

    private Inventory inv;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        Player p = (Player) sender;

        if (!(sender.hasPermission("staffmode.staff"))) {
            sender.sendMessage(CC.translate("&cYou do not have permission to use this command."));
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(CC.translate("&aUsage: &b/invsee <player>"));
            return false;
        } else {
            Player target = Bukkit.getServer().getPlayer(args[0]);
            int ping = target.getPing();
            ItemStack carrotm = new ItemStack(Material.GOLDEN_CARROT);
            ItemMeta carrotmeta = carrotm.getItemMeta();
            carrotmeta.setDisplayName(CC.translate("&ePlayer Ping: " + "&d" + ping));
            carrotm.setItemMeta(carrotmeta);

            ItemStack applem = new ItemStack(Material.APPLE);
            ItemMeta applemeta = applem.getItemMeta();
            applemeta.setDisplayName(CC.translate("&cPlayer Health: " + "&6" + (int)target.getHealth() + "&7/" + "&6" + (int)p.getMaxHealth()));
            applem.setItemMeta(applemeta);

            inv = Bukkit.createInventory(target, 54, CC.translate(target.getName() + "'s &6&lInventory"));
            inv.setContents(target.getInventory().getContents());
            inv.setItem(53, applem);
            inv.setItem(52, carrotm);
            p.sendMessage(CC.translate("&aOpening &b" + target.getName() + "&a's inventory..."));
            ItemStack glassp = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta glasspmeta = glassp.getItemMeta();
            glasspmeta.setDisplayName(" ");
            glassp.setItemMeta(glasspmeta);
            for (int i = 36; i < 45; i++) {
                inv.setItem(i, glassp);
            }
        }

        p.openInventory(inv);
        return true;
    }
}