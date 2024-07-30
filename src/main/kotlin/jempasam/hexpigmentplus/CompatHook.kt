// This file is redefined for all minecraft versions
package jempasam.hexpigmentplus

import at.petrak.hexcasting.api.misc.FrozenColorizer
import net.minecraft.entity.EquipmentSlot
import net.minecraft.inventory.CraftingInventory
import net.minecraft.item.ItemStack
import net.minecraft.tag.ItemTags
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


// Registry Compat Hooks
object CRegistry {

    val ITEM= Registry.ITEM
    val RECIPE_TYPE= Registry.RECIPE_TYPE
    val RECIPE_SERIALIZER= Registry.RECIPE_SERIALIZER

    fun<T> register(reg: Registry<T>, id: Identifier, value: T) = Registry.register(reg,id,value)
}

// Armor Compat Hooks
typealias CSlotType=EquipmentSlot
object CSlotTypes{
    val HELMET=CSlotType.HEAD
}

// Tags Compat Hooks
typealias CItemTags=ItemTags

// Creative Compat Hooks
interface MultiStackCreative{
    fun getStack(): List<ItemStack>
}

// Recipe
typealias RecipeInventory= CraftingInventory

//Pigment
typealias CFrozenPigment= FrozenColorizer