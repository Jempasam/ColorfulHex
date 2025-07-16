package jempasam.hexpigmentplus

import dev.emi.trinkets.api.TrinketsApi
import dev.emi.trinkets.api.client.TrinketRendererRegistry
import jempasam.colorfulhex.armor.MagicianCloakRenderer
import jempasam.colorfulhex.armor.MagicianHatRenderer
import jempasam.hexpigmentplus.armor.TrinketAdapter
import jempasam.hexpigmentplus.item.HPPItems
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.network.AbstractClientPlayerEntity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import kotlin.jvm.optionals.getOrNull

object HPPClientMod : ClientModInitializer {

	override fun onInitializeClient() {
		register(HPPItems.magicianHat, MagicianHatRenderer(), EquipmentSlot.HEAD)
		register(HPPItems.magicianCloak, MagicianCloakRenderer(), EquipmentSlot.CHEST)

		// Hide cloaks if player wear a cloak
		LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.register(this::doRenderCape)

		if(FabricLoader.getInstance().isModLoaded("trinkets")){
			LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.register(this::doRenderCapeTrinket)
		}
	}

	fun register(item: Item, renderer: ArmorRenderer, slot: EquipmentSlot){
		ArmorRenderer.register(renderer, item)
		if(FabricLoader.getInstance().isModLoaded("trinkets")){
			{{ TrinketRendererRegistry.registerRenderer(item, TrinketAdapter(renderer, slot)) }}()()
		}
	}

	fun doRenderCape(player: AbstractClientPlayerEntity): Boolean{
		return player.getEquippedStack(EquipmentSlot.CHEST).item != HPPItems.magicianCloak
	}

	fun doRenderCapeTrinket(player: AbstractClientPlayerEntity): Boolean {
		val stack = TrinketsApi.getTrinketComponent(player).getOrNull()?.inventory?.get("chest")?.get("cape")?.getStack(0)
		return stack?.item != HPPItems.magicianCloak
	}

}