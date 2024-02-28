package jempasam.colorfulhex

import jempasam.colorfulhex.armor.MagicianHatRenderer
import jempasam.colorfulhex.item.ColorfulHexItems
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer

object ColorfulHexClientMod : ClientModInitializer {
	override fun onInitializeClient() {
		ArmorRenderer.register(MagicianHatRenderer(), ColorfulHexItems.magicianHat)
	}

}