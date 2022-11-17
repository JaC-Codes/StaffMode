package me.koz.staffmode.mutechat;

import me.koz.staffmode.CC;
import me.koz.staffmode.StaffMode;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class MuteChatCommand implements CommandExecutor, Listener {


    private final StaffMode staffMode;

    public MuteChatCommand(StaffMode staffMode) {
        this.staffMode = staffMode;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender.hasPermission("staffmode.staff"))) {
            sender.sendMessage(CC.translate("&cYou do not have permission to use this command."));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&CYou cannot perform this command."));
            return true;
        }
        if (!(MuteChat.getMuted())) {
            for (Player playerfor : Bukkit.getServer().getOnlinePlayers()) {
                MuteChat.setIsmuted(true);
                playerfor.sendMessage(CC.translate(this.staffMode.getConfig().getString("MuteChat.muted-message")).replace("%player%", sender.getName()));
            }
        } else {
            for (Player playerfor : Bukkit.getServer().getOnlinePlayers()) {
                MuteChat.setIsmuted(false);
                playerfor.sendMessage(CC.translate(this.staffMode.getConfig().getString("MuteChat.un-muted-message")).replace("%player%", sender.getName()));
            }
        }
        return true;
    }
}
