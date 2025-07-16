package jempasam.hexpigmentplus.item

import at.petrak.hexcasting.api.utils.getInt
import jempasam.hexpigmentplus.MultiStackCreative
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.text.Text

class MagicianClothItem(
    settings: FabricItemSettings,
    type: Type,
    val count: Int,
): ArmorItem(ClothMaterial, type, settings), MultiStackCreative{

    override fun getStack() = List(count){ this.defaultStack.copy().apply { this.orCreateNbt.putInt("CustomModelData",it) } }

    override fun getName(stack: ItemStack) = Text.translatable("$translationKey.${stack.nbt.getInt("CustomModelData",0)}")

}