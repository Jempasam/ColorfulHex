package jempasam.hexpigmentplus.item

import jempasam.hexpigmentplus.CItemTags
import jempasam.hexpigmentplus.CSlotType
import jempasam.hexpigmentplus.CSlotTypes
import jempasam.hexpigmentplus.MultiStackCreative
import jempasam.hexpigmentplus.item.HPPItems.magicianHat
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.*
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvents
import net.minecraft.util.collection.DefaultedList

class HatItem(settings: FabricItemSettings): ArmorItem(Material, CSlotTypes.HELMET, settings), MultiStackCreative{

    object Material: ArmorMaterial{
        override fun getEnchantability() = ArmorMaterials.GOLD.enchantability

        override fun getEquipSound() = SoundEvents.BLOCK_WOOL_STEP

        override fun getName() = "hat"

        override fun getProtectionAmount(slot: CSlotType) = ArmorMaterials.LEATHER.getProtectionAmount(slot)

        override fun getToughness() = ArmorMaterials.LEATHER.toughness

        override fun getRepairIngredient() = Ingredient.fromTag(CItemTags.WOOL)

        override fun getDurability(slot: CSlotType) = ArmorMaterials.LEATHER.getDurability(slot)

        override fun getKnockbackResistance() = ArmorMaterials.LEATHER.knockbackResistance
    }

    override fun getStack() = List(7){ magicianHat.defaultStack.copy().apply { this.orCreateNbt.putInt("CustomModelData",it) } }

    override fun appendStacks(group: ItemGroup, stacks: DefaultedList<ItemStack>) {
        if(isIn(group))getStack().forEach { stacks.add(it) }
    }
}