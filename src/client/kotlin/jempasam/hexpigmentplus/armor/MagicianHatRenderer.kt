package jempasam.colorfulhex.armor

import at.petrak.hexcasting.api.misc.FrozenColorizer
import at.petrak.hexcasting.xplat.IXplatAbstractions
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
import net.minecraft.util.math.Quaternion
import net.minecraft.util.math.random.Random


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
            if(player is PlayerEntity)IXplatAbstractions.INSTANCE.getColorizer(player)
            else FrozenColorizer.DEFAULT.get()

        val color=colorizer.getColor(entity.age.toFloat()*3,entity.pos.multiply(0.1))
        val color2=colorizer.getColor((entity.age.toFloat()+15)*3,entity.pos.multiply(0.1))
        val color3=colorizer.getColor((entity.age.toFloat()+30)*3,entity.pos.multiply(0.1))
        val color4=colorizer.getColor((entity.age.toFloat()+45)*3,entity.pos.multiply(0.1))
        val color5=colorizer.getColor((entity.age.toFloat()+60)*3,entity.pos.multiply(0.1))

        val headpos=contextModel.head.transform
        val pi= Math.PI.toFloat()

        // Vertexs
        val vertexs = ItemRenderer.getArmorGlintConsumer(
            buffer, RenderLayer.getArmorCutoutNoCull(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE), false, stack.hasGlint()
        )

        matrices.push()

        // Headpos
        matrices.multiply(Quaternion.fromEulerXyz(headpos.roll, headpos.yaw+pi/2, headpos.pitch))
        matrices.translate(headpos.pivotX.toDouble()/10, headpos.pivotY.toDouble()/10, headpos.pivotZ.toDouble()/10)


        // Position correction
        matrices.translate(0.0, -0.4*0.625f, 0.0)
        matrices.scale(0.625f,0.625f,0.625f)
        matrices.multiply(Quaternion.fromEulerXyz(0f,-pi/2,pi))
        matrices.translate(-0.5, -0.5, -0.5)

        // Model Pos
        matrices.translate(translation.x.toDouble(), translation.y.toDouble(), translation.z.toDouble())
        matrices.translate(0.5,0.5,0.5)
        matrices.scale(scale.x, scale.y, scale.z)
        matrices.multiply(Quaternion.fromEulerXyz(rotation.x*pi*2/360, rotation.y*pi*2/360, rotation.z*pi*2/360))
        matrices.translate(-0.5,-0.5,-0.5)

        /*
        matrices.translate(0.5, 0.5, 0.5)
        matrices.multiply(Quaternion.fromEulerXyz(rotation.x-Math.PI.toFloat()/2f, rotation.y, rotation.z))
        matrices.translate(-0.5, -0.5, -0.5)*/
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