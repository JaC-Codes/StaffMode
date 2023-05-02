package jack.net.kplugin.staffmode

import org.bukkit.inventory.ItemStack
import java.util.ArrayList
import java.util.HashMap
import java.util.UUID

class Mode {

    private var STAFF : MutableList<UUID> = ArrayList<UUID>()
    private var inventorySaver = HashMap<UUID, Array<ItemStack>>()

    fun getStaff(): MutableList<UUID> {
        return STAFF
    }

    fun getInventorySaver(): HashMap<UUID, Array<ItemStack>> {
        return inventorySaver
    }
}