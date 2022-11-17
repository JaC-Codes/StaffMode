package me.koz.staffmode.onlinestaff;

import me.koz.staffmode.CC;
import me.koz.staffmode.StaffMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OnlineStaffCommand implements CommandExecutor {

    private final StaffMode staffMode;
    private final InventoryHandler inventoryHandler;

    public OnlineStaffCommand(StaffMode staffMode) {
        this.staffMode = staffMode;
        this.inventoryHandler = new InventoryHandler(this.staffMode);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cYou must be a player to use this command."));

        }
        if (!sender.hasPermission("staffmode.staff")) {
            sender.sendMessage(CC.translate("&cYou do not have permission to use this command."));
        }else {
            Player player = (Player) sender;
            this.inventoryHandler.staffHead(player);
        }


        return false;
    }
}
