package me.koz.staffmode.commands;

import me.koz.staffmode.CC;
import me.koz.staffmode.StaffMode;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


@SuppressWarnings("ALL")
public class Freeze implements CommandExecutor {

    private final StaffMode staffMode;


    public static ArrayList<UUID> FROZEN = new ArrayList<UUID>();
    public HashMap<Player, ItemStack[]> invsave = new HashMap<Player, ItemStack[]>();

    public Freeze(StaffMode staffMode) {
        this.staffMode = staffMode;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("staffmode.staff")) {
            if (args.length == 0) {
                sender.sendMessage(CC.translate("&cUsage: /freeze <player>"));
                return false;
            } else {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target != null) {
                    if (!target.isOnline()) {
                        sender.sendMessage(CC.translate("&cThis player isn't online."));
                    } else {
                        if (!FROZEN.contains(target.getUniqueId())) {
                            FROZEN.add(target.getUniqueId());
                            target.sendMessage(CC.translate("&f█████████"));
                            target.sendMessage(CC.translate("&f████&c█&f████"));
                            target.sendMessage(CC.translate("&f███&c█&0█&c█&f███"));
                            target.sendMessage(CC.translate("&f██&c█&6█&0█&6█&c█&f██"));
                            target.sendMessage(CC.translate("&f██&c█&6█&0█&6█&c█&f██ &eYou have been frozen by a staff member."));
                            target.sendMessage(CC.translate("&f██&c█&6█&0█&6█&c█&f██ &eIf you disconnect you will be &4&lBANNED&e."));
                            target.sendMessage(CC.translate("&f█&c█&6█████&c█&f█ &ePlease connect to our TeamSpeak."));
                            target.sendMessage(CC.translate(this.staffMode.getConfig().getString("Freezeip")));
                            target.sendMessage(CC.translate("&c█████████"));
                            target.sendMessage(CC.translate("&f█████████"));

                            sender.sendMessage(CC.translate("&aYou have frozen " + target.getName() + "&2."));
                            return true;
                        } else {
                            FROZEN.remove(target.getUniqueId());
                            target.sendMessage(CC.translate("&a&lYou have been unfrozen."));
                            sender.sendMessage(CC.translate("&aYou have unfrozen " + target.getName() + "&2."));
                        }


                    }
                }
            }
        }

        return false;
    }
}

