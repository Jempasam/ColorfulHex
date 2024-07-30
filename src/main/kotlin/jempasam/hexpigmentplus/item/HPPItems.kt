package jempasam.hexpigmentplus.item

import at.petrak.hexcasting.xplat.IXplatAbstractions
import jempasam.hexpigmentplus.CRegistry
import jempasam.hexpigmentplus.HPPMod
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item

object HPPItems {

    fun <T: Item> register(id: String, item: T): T{
        CRegistry.register(CRegistry.ITEM, HPPMod/id, item)
        return item
    }

    val magicianHat= register("magician_hat", HatItem(FabricItemSettings().group(IXplatAbstractions.INSTANCE.tab)))
}