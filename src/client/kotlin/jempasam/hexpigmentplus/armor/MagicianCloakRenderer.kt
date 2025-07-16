package jempasam.colorfulhex.armor

import jempasam.hexpigmentplus.HPPRenderHelper
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import org.joml.Quaternionf
import kotlin.math.max
import kotlin.math.min


class MagicianCloakRenderer: ArmorRenderer {
    override fun render(
        matrices: MatrixStack, buffer: VertexConsumerProvider,
        stack: ItemStack, entity: LivingEntity, slot: EquipmentSlot, light: Int,
        contextModel: BipedEntityModel<LivingEntity>
    ) {
        val pi = Math.PI.toFloat()

        matrices.push()

        //// MOVEMENT ANIMATION ////
        // Pitch depends on legs pose / Is pushed by legs
        var pitch = max(0f, max(contextModel.rightLeg.pitch, contextModel.leftLeg.pitch)) * 0.1f
        // Falling add pitch / Is pushed by wind
        pitch += min(1f,entity.fallDistance/5f) *0.2f
        // Running add pitch / Is pushed by air
        if(entity is ClientPlayerEntity){
            pitch += entity.strideDistance*2f
        }
        matrices.multiply(Quaternionf().rotateZYX(0f,0f,pitch))


        //// HEAD TRANSFORMATION ////
        matrices.multiply(Quaternionf().rotateZYX(contextModel.body.roll, contextModel.body.yaw, contextModel.body.pitch))
        matrices.translate(-.5f, -.75f, -.5f)
        matrices.translate(.5f,.5f,.5f)
        matrices.scale(0.65f, 0.65f, 0.65f)
        matrices.multiply(Quaternionf().rotateZYX(pi,0f,0f))
        matrices.translate(-.5f,-.5f,-.5f)

        HPPRenderHelper.renderModel(buffer, stack, matrices, entity, light)

        matrices.pop()
    }
}