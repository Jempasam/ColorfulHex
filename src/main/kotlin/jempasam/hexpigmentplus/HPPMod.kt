package jempasam.hexpigmentplus

import jempasam.hexpigmentplus.item.HPPItems
import jempasam.hexpigmentplus.recipe.HPPRecipes
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

object HPPMod : ModInitializer {

	const val MODID = "hexpigmentplus"
	val logger = LoggerFactory.getLogger(MODID)
	operator fun div(s: String) = Identifier(MODID, s)

	override fun onInitialize() {
		logger.info("Colorful hex start!")
		HPPItems
		HPPRecipes
	}

}