package jack.net.kplugin.staffmode


import jack.net.kplugin.Core
import jack.net.kplugin.utils.CC
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ModeHandler(private var core: Core) {

    var mode: Mode = Mode()
    var modeBuilder: ModeBuilder = ModeBuilder(core)

    fun enterStaffMode(player: Player) {
        mode.getStaff().add(player.uniqueId)
        mode.getInventorySaver()[player.uniqueId] = player.inventory.contents

        player.inventory.clear()
        clearArmor(player)

        modeBuilder.giveStaffMode(player)

        player.gameMode = GameMode.CREATIVE
        player.isFlying = true
        player.isGlowing = true
        player.sendMessage(CC.translate("&a[&bSC&a] &dStaff Mode Enabled"))
    }

    fun exitStaffMode(player: Player) {
        mode.getStaff().remove(player.uniqueId)
        player.inventory.contents = mode.getInventorySaver()[player.uniqueId] as Array<out ItemStack>

        player.inventory.clear()
        player.gameMode = GameMode.SURVIVAL
        player.isFlying = false
        player.isGlowing = false
        player.sendMessage(CC.translate("&a[&bSC&a] &dStaff Mode Disabled"))
    }

    fun clearArmor(player: Player) {
        player.inventory.helmet = null
        player.inventory.chestplate = null
        player.inventory.leggings = null
        player.inventory.boots = null

    }
}