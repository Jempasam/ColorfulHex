package jempasam.hexpigmentplus.item

import at.petrak.hexcasting.common.lib.HexCreativeTabs
import jempasam.hexpigmentplus.HPPMod
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object HPPItems {

    fun <T: Item> register(id: String, item: T): T{
        Registry.register(Registries.ITEM, HPPMod/id, item)
        return item
    }

    val magicianHat= register("magician_hat", HatItem(FabricItemSettings()))

    init{
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register{ group, items ->
            if(group==HexCreativeTabs.HEX){
                fun hat(model: Int) = items.add(magicianHat.defaultStack.copy().apply { this.orCreateNbt.putInt("CustomModelData",model) })
                hat(0)
                hat(1)
                hat(2)
                hat(3)
                hat(4)
            }
        }
    }
}