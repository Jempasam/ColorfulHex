package jempasam.colorfulhex.item

import jempasam.colorfulhex.ColorfulHexMod
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterials
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

object ColorfulHexItems {

    fun <T: Item> register(id: String, item: T): T{
        Registry.register(Registry.ITEM, ColorfulHexMod/id, item)
        return item
    }

    val magicianHat= register("magician_hat", ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, Item.Settings().group(ItemGroup.COMBAT)))
}