package jempasam.hexpigmentplus.item

import jempasam.hexpigmentplus.CRegistry
import jempasam.hexpigmentplus.HPPMod
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item

object HPPItems {

    fun <T: Item> register(id: String, item: T): T{
        CRegistry.register(CRegistry.ITEM, HPPMod/id, item)
        return item
    }

    val magicianHat= register("magician_hat", MagicianClothItem(FabricItemSettings(), ArmorItem.Type.HELMET, 14))

    val magicianCloak= register("magician_cloak", MagicianClothItem(FabricItemSettings(), ArmorItem.Type.CHESTPLATE, 3))
}