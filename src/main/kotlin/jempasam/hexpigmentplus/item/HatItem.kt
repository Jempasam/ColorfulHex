package jempasam.hexpigmentplus.item

import at.petrak.hexcasting.api.utils.getInt
import jempasam.hexpigmentplus.CItemTags
import jempasam.hexpigmentplus.MultiStackCreative
import jempasam.hexpigmentplus.item.HPPItems.magicianHat
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ArmorMaterials
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text

class HatItem(settings: FabricItemSettings): ArmorItem(Material, Type.HELMET, settings), MultiStackCreative{

    object Material: ArmorMaterial{
        override fun getEnchantability() = ArmorMaterials.GOLD.enchantability

        override fun getEquipSound() = SoundEvents.BLOCK_WOOL_STEP

        override fun getName() = "hat"

        override fun getProtection(type: Type?): Int = ArmorMaterials.LEATHER.getProtection(type)

        override fun getDurability(type: Type?): Int = ArmorMaterials.LEATHER.getDurability(type)

        override fun getToughness() = ArmorMaterials.LEATHER.toughness

        override fun getRepairIngredient() = Ingredient.fromTag(CItemTags.WOOL)

        override fun getKnockbackResistance() = ArmorMaterials.LEATHER.knockbackResistance
    }

    override fun getStack() = List(11){ magicianHat.defaultStack.copy().apply { this.orCreateNbt.putInt("CustomModelData",it) } }

    override fun getName(stack: ItemStack) = Text.translatable("$translationKey.${stack.nbt.getInt("CustomModelData",0)}")

}