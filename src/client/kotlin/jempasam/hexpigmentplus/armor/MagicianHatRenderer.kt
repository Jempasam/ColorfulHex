package jempasam.colorfulhex.armor

import at.petrak.hexcasting.xplat.IXplatAbstractions
import jempasam.hexpigmentplus.CFrozenPigment
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.texture.SpriteAtlasTexture
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.DyeColor
import net.minecraft.util.math.ColorHelper
import net.minecraft.util.math.random.Random
import org.joml.Quaternionf


class MagicianHatRenderer: ArmorRenderer {
    override fun render(
        matrices: MatrixStack, buffer: VertexConsumerProvider,
        stack: ItemStack, entity: LivingEntity, slot: EquipmentSlot, light: Int,
        contextModel: BipedEntityModel<LivingEntity>
    ) {
        val mc=MinecraftClient.getInstance()
        val root_model=mc.itemRenderer.models.getModel(stack)
        val model=root_model.overrides.apply(root_model,stack,entity.world as? ClientWorld, entity, 0) ?: root_model
        val scale=model.transformation.head.scale
        val translation=model.transformation.head.translation
        val rotation=model.transformation.head.rotation
        if(slot!=EquipmentSlot.HEAD)return

        val player=entity
        val colorizer=
            if(player is PlayerEntity)IXplatAbstractions.INSTANCE.getPigment(player)
            else CFrozenPigment.DEFAULT.get()

        val color=colorizer.colorProvider.getColor(entity.age.toFloat()*3,entity.pos.multiply(0.1))
        val color2=colorizer.colorProvider.getColor((entity.age.toFloat()+15)*3,entity.pos.multiply(0.1))
        val color3=colorizer.colorProvider.getColor((entity.age.toFloat()+30)*3,entity.pos.multiply(0.1))
        val color4=colorizer.colorProvider.getColor((entity.age.toFloat()+45)*3,entity.pos.multiply(0.1))
        val color5=colorizer.colorProvider.getColor((entity.age.toFloat()+60)*3,entity.pos.multiply(0.1))

        // Vertexs
        val vertexs = ItemRenderer.getArmorGlintConsumer(
            buffer, RenderLayer.getArmorCutoutNoCull(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE), false, stack.hasGlint()
        )

        val head = contextModel.head
        val pi = Math.PI.toFloat()

        matrices.push()
        matrices.multiply(Quaternionf().rotateZYX(contextModel.head.roll, contextModel.head.yaw, contextModel.head.pitch))
        matrices.multiply(Quaternionf().rotateZYX(Math.PI.toFloat(), -Math.PI.toFloat()/2f, 0f))
        matrices.scale(0.65f, 0.65f, 0.65f)
        matrices.translate(-.8, -.6, -.5)

        matrices.translate(0.5, 0.5, 0.5)
        matrices.scale(scale.x, scale.y, scale.z)
        matrices.multiply(Quaternionf().rotateZYX(-rotation.x+Math.PI.toFloat()/2, rotation.y+Math.PI.toFloat()/2, rotation.z))
        matrices.translate(-0.5, -0.5, -0.5)
        matrices.translate(translation.x.toDouble(), translation.y.toDouble(), translation.z.toDouble())
        val matrix=matrices.peek()
        matrices.pop()

        val quads=model.getQuads(null, null, Random.create())
        for(quad in quads){
            val quad_color=when(quad.colorIndex){
                0 -> color
                1 -> color2
                2 -> color3
                3 -> color4
                4 -> color5
                else->DyeColor.WHITE.signColor
            }
            vertexs.quad(
                matrix,
                quad,
                ColorHelper.Argb.getRed(quad_color)/255f,
                ColorHelper.Argb.getGreen(quad_color)/255f,
                ColorHelper.Argb.getBlue(quad_color)/255f,
                light,
                0
            )
        }

    }
}