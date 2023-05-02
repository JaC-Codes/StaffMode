package jack.net.kplugin.staffmode

import jack.net.kplugin.Core
import jack.net.kplugin.utils.CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ModeCommand(var core: Core) : CommandExecutor {

    var mode: Mode = Mode()
    var modeHandler: ModeHandler = ModeHandler(core)

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player: Player = sender as Player

        if (!player.hasPermission("")) {
            player.sendMessage(CC.translate("&cYou do not have permission to use this command."))
        }

        if (!mode.getStaff().contains(player.uniqueId)) {
            modeHandler.enterStaffMode(player)
            return true
        } else {
            modeHandler.exitStaffMode(player)
        }

        return false
    }
}