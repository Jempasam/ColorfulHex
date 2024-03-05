package jempasam.hexpigmentplus.item

import at.petrak.hexcasting.xplat.IXplatAbstractions
import jempasam.hexpigmentplus.HPPMod
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

object HPPItems {

    fun <T: Item> register(id: String, item: T): T{
        Registry.register(Registry.ITEM, HPPMod/id, item)
        return item
    }

    val magicianHat= register("magician_hat", HatItem(FabricItemSettings().group(IXplatAbstractions.INSTANCE.tab)))
}