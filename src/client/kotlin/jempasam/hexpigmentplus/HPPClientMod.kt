package jempasam.hexpigmentplus

import jempasam.hexpigmentplus.armor.MagicianHatRenderer
import jempasam.hexpigmentplus.item.HPPItems
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer

object HPPClientMod : ClientModInitializer {
	override fun onInitializeClient() {
		ArmorRenderer.register(MagicianHatRenderer(), HPPItems.magicianHat)
	}

}