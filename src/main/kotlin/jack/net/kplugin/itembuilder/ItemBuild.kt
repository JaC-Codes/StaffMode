package jack.net.kplugin.itembuilder

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import java.util.*

class ItemBuild(material: Material?, amount: Int) {
    private val itemStack: ItemStack
    private val itemMeta: ItemMeta?

    init {
        itemStack = ItemStack(material!!, amount)
        itemMeta = itemStack.itemMeta
    }

    private fun updateItemMeta() {
        itemStack.itemMeta = itemMeta
    }

    fun setDisplayName(name: String?): ItemBuild {
        itemMeta!!.setDisplayName(name)
        return this
    }

    fun setLore(vararg lines: String?): ItemBuild {
        itemMeta!!.lore = Arrays.asList(*lines)
        return this
    }

    fun setItemGlowing(): ItemBuild {
        itemMeta!!.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        return this
    }

    fun build(): ItemStack {
        updateItemMeta()
        return itemStack
    }

    fun setEnchant(enchant: Enchantment?, level: Int): ItemBuild {
        itemMeta!!.addEnchant(enchant!!, level, true)
        return this
    }

    fun setUnsafeEnchant(enchant: Enchantment?, level: Int): ItemBuild {
        itemStack.addUnsafeEnchantment(enchant!!, level)
        return this
    }

    fun addKey(key: NamespacedKey?, value: String): ItemBuild {
        itemMeta!!.persistentDataContainer.set(key!!, PersistentDataType.STRING, value)
        return this
    }
}