package jack.net.kplugin.staffmode

import jack.net.kplugin.Core
import jack.net.kplugin.itembuilder.ItemBuild
import jack.net.kplugin.utils.CC
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ModeBuilder(private val core: Core) {

    private val compassKey: NamespacedKey = NamespacedKey(core, "compassKey")
    private val freezeKey: NamespacedKey = NamespacedKey(core, "freezeKey")

    private fun getStaffMode(): Array<ItemStack?> {
        val compass = ItemBuild(Material.COMPASS, 1).setDisplayName(CC.translate("&6&lCompass"))
            .addKey(compassKey, "compass").build()

        val freeze = ItemBuild(Material.COMPASS, 1).setDisplayName(CC.translate("&b&lFreeze"))
            .addKey(freezeKey, "freezeKey").build()
        return arrayOf(compass)
    }

    fun giveStaffMode(player: Player) {
        for (item: ItemStack? in getStaffMode()) {
            player.inventory.setItem(0, getStaffMode()[0])
            player.inventory.setItem(2, getStaffMode()[1])
        }
    }


}