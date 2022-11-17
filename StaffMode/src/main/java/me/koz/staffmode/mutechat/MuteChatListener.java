package me.koz.staffmode.mutechat;

import me.koz.staffmode.CC;
import me.koz.staffmode.StaffMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MuteChatListener implements Listener {

    private final StaffMode staffMode;

    public MuteChatListener(StaffMode staffMode) {
        this.staffMode = staffMode;

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!(player.hasPermission("staffmode.bypass"))) {
            if (MuteChat.getMuted()) {
                player.sendMessage(CC.translate(this.staffMode.getConfig().getString("MuteChat.chat-is-muted-message")));
                event.setCancelled(true);
            }
        }
    }
}
