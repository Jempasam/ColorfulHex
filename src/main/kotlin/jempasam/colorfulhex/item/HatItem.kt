package jempasam.colorfulhex.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ArmorMaterials
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvents
import net.minecraft.tag.ItemTags

class HatItem(settings: FabricItemSettings): ArmorItem(Material, EquipmentSlot.HEAD, settings){

    object Material: ArmorMaterial{
        override fun getEnchantability() = ArmorMaterials.GOLD.enchantability

        override fun getEquipSound() = SoundEvents.BLOCK_WOOL_STEP

        override fun getName() = "hat"

        override fun getProtectionAmount(slot: EquipmentSlot) = ArmorMaterials.LEATHER.getProtectionAmount(slot)

        override fun getToughness() = ArmorMaterials.LEATHER.toughness

        override fun getRepairIngredient() = Ingredient.fromTag(ItemTags.WOOL)

        override fun getDurability(slot: EquipmentSlot) = ArmorMaterials.LEATHER.getDurability(slot)

        override fun getKnockbackResistance() = ArmorMaterials.LEATHER.knockbackResistance
    }
}