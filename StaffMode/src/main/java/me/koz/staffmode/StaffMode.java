package me.koz.staffmode;

import me.koz.staffmode.commands.*;
import me.koz.staffmode.inventorysee.InventorySee;
import me.koz.staffmode.inventorysee.InvseeListener;
import me.koz.staffmode.listeners.*;
import me.koz.staffmode.mutechat.MuteChatCommand;
import me.koz.staffmode.mutechat.MuteChatListener;
import me.koz.staffmode.onlinestaff.OnlineStaffCommand;
import me.koz.staffmode.onlinestaff.OnlineStaffListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class StaffMode extends JavaPlugin {

    private static StaffMode instance;

    public void onEnable() {
        this.config();
        instance = this;
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists())
            saveDefaultConfig();

        long duration = System.currentTimeMillis();
        String prefix = "§3[" + getDescription().getName() + " " + getDescription().getVersion() + "] ";
        Bukkit.getConsoleSender().sendMessage(prefix + "§6=== ENABLE START ===");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aLoading §dListeners");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aLoading §dCommands");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aMade by §dKoz");
        Bukkit.getConsoleSender().sendMessage(
                prefix + "§6=== ENABLE §aCOMPLETE §6(Took §d" + (System.currentTimeMillis() - duration) + "ms§6) ===");
        getCommands();
        registerEvents();

    }

    private void config() {
            getConfig().options().copyDefaults();
            saveDefaultConfig();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new StaffChatListener(), this);
        getServer().getPluginManager().registerEvents(new StaffModeListener(), this);
        getServer().getPluginManager().registerEvents(new FreezeListener(), this);
        getServer().getPluginManager().registerEvents(new InvseeListener(), this);
        getServer().getPluginManager().registerEvents(new Mode(this), this);
        getServer().getPluginManager().registerEvents(new OnlineStaffListener(), this);
        getServer().getPluginManager().registerEvents(new MuteChatListener(this), this);
    }

    private void getCommands() {
        getCommand("mod").setExecutor(new Mode(this));
        getCommand("sc").setExecutor(new StaffChat(this));
        getCommand("freeze").setExecutor(new Freeze(this));
        getCommand("clearchat").setExecutor(new ClearChat());
        getCommand("invsee").setExecutor(new InventorySee());
        getCommand("onlinestaff").setExecutor(new OnlineStaffCommand(this));
        getCommand("mutechat").setExecutor(new MuteChatCommand(this));
    }


    public void onDisable() {
        long duration = System.currentTimeMillis();
        String prefix = "§3[" + getDescription().getName() + " " + getDescription().getVersion() + "] ";
        Bukkit.getConsoleSender().sendMessage(prefix + "§6=== DISABLING ===");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aDisabling §dListeners");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aDisabling §dCommands");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aMade by §dKoz");
        Bukkit.getConsoleSender().sendMessage(
                prefix + "§6=== DISABLE §aCOMPLETE §6(Took §d" + (System.currentTimeMillis() - duration) + "ms§6) =");
    }

    static StaffMode getInstance() {
        return instance;
    }
}

