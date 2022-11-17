package me.koz.staffmode.listeners;

import me.koz.staffmode.CC;
import me.koz.staffmode.commands.Freeze;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class FreezeListener implements Listener {


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (Freeze.FROZEN.contains(p.getUniqueId())) {
            e.setTo(e.getFrom());
        }
    }

    @EventHandler
    public void onPlayerOpenInv(InventoryOpenEvent e) {
        if (e.getPlayer() instanceof Player) {
            Player p = (Player) e.getPlayer();
            if (Freeze.FROZEN.contains(p.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void whenImmortal(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (Freeze.FROZEN.contains(p.getUniqueId())) {
                e.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void playerDisconnect(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if (Freeze.FROZEN.contains(p.getUniqueId())) {
            for (Player playerfor : Bukkit.getServer().getOnlinePlayers()) {
                if (playerfor.hasPermission("staffmode.staff")) {
                    TextComponent startof = new TextComponent(CC.translate( "&e&l" + event.getPlayer().getDisplayName() + " &cHas &c&lLOGGED &cout while &c&l&nFROZEN "));
                    TextComponent clickable = new TextComponent(CC.translate("&4&l[CLICK TO BAN]"));
                    clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ban " + event.getPlayer().getDisplayName()));
                    clickable.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(CC.translate("&c&lClick to ban &e&l" + event.getPlayer().getDisplayName()))));
                    startof.addExtra(clickable);
                    playerfor.spigot().sendMessage(startof);
                        }
                    }
                }
            }
        }


