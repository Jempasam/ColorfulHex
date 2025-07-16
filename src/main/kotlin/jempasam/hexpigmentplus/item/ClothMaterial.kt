package jempasam.hexpigmentplus.item

import jempasam.hexpigmentplus.CItemTags
import net.minecraft.item.ArmorItem.Type
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ArmorMaterials
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvents

object ClothMaterial: ArmorMaterial {
    override fun getEnchantability() = ArmorMaterials.GOLD.enchantability

    override fun getEquipSound() = SoundEvents.BLOCK_WOOL_STEP

    override fun getName() = "hat"

    override fun getProtection(type: Type?): Int = ArmorMaterials.LEATHER.getProtection(type)

    override fun getDurability(type: Type?): Int = ArmorMaterials.LEATHER.getDurability(type)

    override fun getToughness() = ArmorMaterials.LEATHER.toughness

    override fun getRepairIngredient() = Ingredient.fromTag(CItemTags.WOOL)

    override fun getKnockbackResistance() = ArmorMaterials.LEATHER.knockbackResistance
}