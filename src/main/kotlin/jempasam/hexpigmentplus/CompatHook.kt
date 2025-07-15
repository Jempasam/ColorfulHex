// This file is redefined for all minecraft versions
package jempasam.hexpigmentplus

import at.petrak.hexcasting.api.pigment.FrozenPigment
import net.minecraft.entity.EquipmentSlot
import net.minecraft.inventory.RecipeInputInventory
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.tag.ItemTags
import net.minecraft.util.Identifier


// Registry Compat Hooks
object CRegistry {

    val ITEM= Registries.ITEM
    val RECIPE_TYPE= Registries.RECIPE_TYPE
    val RECIPE_SERIALIZER= Registries.RECIPE_SERIALIZER

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
typealias RecipeInventory= RecipeInputInventory

//Pigment
typealias CFrozenPigment= FrozenPigment