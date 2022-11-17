package me.koz.staffmode.commands;


import de.myzelyam.api.vanish.VanishAPI;
import me.koz.staffmode.CC;
import me.koz.staffmode.TeamAction;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Mode implements CommandExecutor, Listener {

    public HashMap<Player, ItemStack[]> invsave = new HashMap<>();
    public static ArrayList<UUID> STAFF = new ArrayList<>();
    public static ArrayList<UUID> VANISH = new ArrayList<>();

    String prefix = CC.translate("&b[&aS&b]");

    private final me.koz.staffmode.StaffMode staffMode;

    public Mode(me.koz.staffmode.StaffMode staffMode) {
        this.staffMode = staffMode;
    }

    class vanishon {
        public static ItemStack getVanishOn() {
            ItemStack vanon = new ItemStack(Material.LIME_DYE);
            ItemMeta vanonmeta = vanon.getItemMeta();
            vanonmeta.setDisplayName(CC.translate("&8&lVanish &7&l(On)"));
            vanon.setItemMeta(vanonmeta);
            return vanon;
        }
    }

    class vanishoff {
        public static ItemStack getVanishOff() {
            ItemStack vanoff = new ItemStack(Material.GRAY_DYE);
            ItemMeta vanoffmeta = vanoff.getItemMeta();
            vanoffmeta.setDisplayName(CC.translate("&8&lVanish &7&l(Off)"));
            vanoff.setItemMeta(vanoffmeta);
            return vanoff;
        }
    }


    @EventHandler
    public void playerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        if (item == null || item.getType() == Material.AIR) return;
        if (!item.hasItemMeta()) return;
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (item.getItemMeta().getDisplayName().equals(CC.translate("&8&lVanish &7&l(On)"))) {
                p.performCommand("vanish");
                p.getInventory().remove(new ItemStack(vanishon.getVanishOn()));
                p.getInventory().setItem(7, new ItemStack(vanishoff.getVanishOff()));
                VANISH.remove(p.getUniqueId());
            } else {
                if (item.getType() == Material.AIR) return;
                if (!item.hasItemMeta()) return;
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (item.getItemMeta().getDisplayName().equals(CC.translate("&8&lVanish &7&l(Off)"))) {
                        p.performCommand("vanish");
                        p.getInventory().remove(new ItemStack(vanishoff.getVanishOff()));
                        p.getInventory().setItem(7, new ItemStack(vanishon.getVanishOn()));
                        VANISH.add(p.getUniqueId());
                    }
                }
            }
        }
    }
    @EventHandler
    public void playerDisconnect(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        VANISH.remove(p.getUniqueId());
        STAFF.remove(p.getUniqueId());
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


            ItemStack compassm = new ItemStack(Material.COMPASS);
            ItemMeta compassmeta = compassm.getItemMeta();
            compassmeta.setDisplayName(CC.translate("&a&lCompass"));
            compassm.setItemMeta(compassmeta);

            ItemStack freezem = new ItemStack(Material.PACKED_ICE);
            ItemMeta icemeta = freezem.getItemMeta();
            icemeta.setDisplayName(CC.translate("&e&lFreeze"));
            freezem.setItemMeta(icemeta);

            ItemStack endereyem = new ItemStack(Material.ENDER_EYE);
            ItemMeta endereyemeta = endereyem.getItemMeta();
            endereyemeta.setDisplayName(CC.translate("&b&lOnline Staff"));
            endereyem.setItemMeta(endereyemeta);

            ItemStack bookm = new ItemStack(Material.BOOK);
            ItemMeta bookmeta = bookm.getItemMeta();
            bookmeta.setDisplayName(CC.translate("&d&lInventory Viewer"));
            bookm.setItemMeta(bookmeta);

        Player p = (Player) sender;

        if (!(p.hasPermission("staffmode.staff"))) {
            p.sendMessage(CC.translate("&cYou do not have permission to use this command."));
            return true;
        }

        if (!STAFF.contains(p.getUniqueId())) {
            invsave.put(p, p.getInventory().getContents());
            STAFF.add(p.getUniqueId());
            p.getInventory().clear();
            p.getInventory().setHelmet(null);
            p.getInventory().setChestplate(null);
            p.getInventory().setLeggings(null);
            VanishAPI.hidePlayer(p);
            VANISH.add(p.getUniqueId());
            p.getInventory().setBoots(null);
            p.getInventory().addItem(compassm);
            p.getInventory().setItem(1, new ItemStack(freezem));
            p.getInventory().setItem(2, new ItemStack(bookm));
            p.getInventory().setItem(8, new ItemStack(endereyem));
            p.setGlowing(true);
            if (VANISH.contains(p.getUniqueId())) {
                p.getInventory().setItem(7, new ItemStack(vanishon.getVanishOn()));
            }
            NametagChanger.changePlayerName(p, this.staffMode.getConfig().getString("Prefix"), TeamAction.CREATE);
            p.setGameMode(GameMode.CREATIVE);
            p.setFlying(true);
            p.sendMessage(CC.translate(prefix + " &bStaff mode enabled."));
            return true;
        } else {
            p.getInventory().setContents(invsave.get(p));
            STAFF.remove(p.getUniqueId());
            p.setGlowing(false);
            VANISH.remove(p.getUniqueId());
            VanishAPI.showPlayer(p);
            NametagChanger.changePlayerName(p, "", TeamAction.UPDATE);
            p.setGameMode(GameMode.SURVIVAL);
            p.setFlying(false);
            p.sendMessage(CC.translate(prefix + " &bStaff mode disabled."));
        }
        return false;
    }
}