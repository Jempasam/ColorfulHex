package jempasam.colorfulhex.item

import at.petrak.hexcasting.xplat.IXplatAbstractions
import jempasam.colorfulhex.ColorfulHexMod
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

object ColorfulHexItems {

    fun <T: Item> register(id: String, item: T): T{
        Registry.register(Registry.ITEM, ColorfulHexMod/id, item)
        return item
    }

    val magicianHat= register("magician_hat", HatItem(FabricItemSettings().group(IXplatAbstractions.INSTANCE.tab)))
}