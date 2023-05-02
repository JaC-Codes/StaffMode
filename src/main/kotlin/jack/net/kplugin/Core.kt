package jack.net.kplugin

import jack.net.kplugin.staffmode.ModeCommand
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Core : JavaPlugin() {


    override fun onEnable() {
        Bukkit.getConsoleSender().sendMessage("Plugin loaded.")
        registerCommands()
    }

    private fun registerCommands() {
        getCommand("staffmode")?.setExecutor(ModeCommand(this))
    }

    override fun onDisable() {
        Bukkit.getConsoleSender().sendMessage("Plugin unloaded.")
    }


}