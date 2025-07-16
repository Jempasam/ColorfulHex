package jempasam.hexpigmentplus

import at.petrak.hexcasting.api.HexAPI
import jempasam.hexpigmentplus.item.HPPItems
import jempasam.hexpigmentplus.recipe.HPPRecipes
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

object HPPMod : ModInitializer {

	const val MODID = "hexpigmentplus"
	val logger = LoggerFactory.getLogger(MODID)
	operator fun div(s: String) = Identifier(MODID, s)

	override fun onInitialize() {
		logger.info("Hex pigment plus start!")
		HPPItems
		HPPRecipes
		ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier(HexAPI.MOD_ID, "hexcasting"))).register{ event ->
			HPPItems.magicianHat.getStack().forEach { event.add(it) }
			HPPItems.magicianCloak.getStack().forEach { event.add(it) }
		}
	}

}