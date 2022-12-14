package me.koz.staffmode.listeners;

import me.koz.staffmode.CC;
import me.koz.staffmode.StaffMode;
import me.koz.staffmode.commands.StaffChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class StaffChatListener implements Listener {

    public StaffMode plugin;
    public StaffChatListener(StaffMode pl) {
        this.plugin = pl;
    }

    public StaffChatListener() {
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String prefix = CC.translate("&b[&aSC&b]");
        String msg = e.getMessage();

        if (StaffChat.Insc.contains(p)) {
            e.setCancelled(true);
            for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
                if (staff.hasPermission("beach.staffchat")) {
                    staff.sendMessage(prefix + " " + p.getDisplayName() + CC.translate("&b: " + CC.translate("&a" + msg)));
                }
            }
        }
    }
}
