package jempasam.hexpigmentplus.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ArmorMaterials
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.ItemTags
import net.minecraft.sound.SoundEvents

class HatItem(settings: FabricItemSettings): ArmorItem(Material, Type.HELMET, settings){

    object Material: ArmorMaterial{
        override fun getEnchantability() = ArmorMaterials.GOLD.enchantability

        override fun getEquipSound() = SoundEvents.BLOCK_WOOL_STEP

        override fun getName() = "hat"

        override fun getProtection(slot: Type) = ArmorMaterials.LEATHER.getProtection(slot)

        override fun getToughness() = ArmorMaterials.LEATHER.toughness

        override fun getRepairIngredient() = Ingredient.fromTag(ItemTags.WOOL)

        override fun getDurability(slot: Type) = ArmorMaterials.LEATHER.getDurability(slot)

        override fun getKnockbackResistance() = ArmorMaterials.LEATHER.knockbackResistance
    }
}