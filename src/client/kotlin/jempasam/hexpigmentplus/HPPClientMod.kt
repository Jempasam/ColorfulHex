package jempasam.hexpigmentplus

import dev.emi.trinkets.api.client.TrinketRendererRegistry
import jempasam.colorfulhex.armor.MagicianCloakRenderer
import jempasam.colorfulhex.armor.MagicianHatRenderer
import jempasam.hexpigmentplus.armor.TrinketAdapter
import jempasam.hexpigmentplus.item.HPPItems
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item

object HPPClientMod : ClientModInitializer {

	override fun onInitializeClient() {
		register(HPPItems.magicianHat, MagicianHatRenderer(), EquipmentSlot.HEAD)
		register(HPPItems.magicianCloak, MagicianCloakRenderer(), EquipmentSlot.CHEST)
	}

	fun register(item: Item, renderer: ArmorRenderer, slot: EquipmentSlot){
		ArmorRenderer.register(renderer, item)
		if(FabricLoader.getInstance().isModLoaded("trinkets")){
			{{ TrinketRendererRegistry.registerRenderer(item, TrinketAdapter(renderer, slot)) }}()()
		}
	}

}