package jempasam.hexpigmentplus.armor

import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.client.TrinketRenderer
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack

class TrinketAdapter(val armorRenderer: ArmorRenderer, val slot: EquipmentSlot): TrinketRenderer {
    override fun render(
        p0: ItemStack,
        p1: SlotReference,
        p2: EntityModel<out LivingEntity>,
        p3: MatrixStack,
        p4: VertexConsumerProvider,
        p5: Int,
        p6: LivingEntity,
        p7: Float,
        p8: Float,
        p9: Float,
        p10: Float,
        p11: Float,
        p12: Float
    ) {
        if(p2 is BipedEntityModel)armorRenderer.render(p3,p4,p0,p6,slot,p5,p2 as BipedEntityModel<LivingEntity>)
    }
}