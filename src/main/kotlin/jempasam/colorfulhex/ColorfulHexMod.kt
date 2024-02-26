package jempasam.colorfulhex

import jempasam.colorfulhex.item.ColorfulHexItems
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

object ColorfulHexMod : ModInitializer {

	const val MODID = "colorfulhex"
	val logger = LoggerFactory.getLogger(MODID)
	operator fun div(s: String) = Identifier(MODID, s)

	override fun onInitialize() {
		logger.info("Colorful hex start!")
		ColorfulHexItems
	}
}