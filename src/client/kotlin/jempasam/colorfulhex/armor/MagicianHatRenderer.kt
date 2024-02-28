package jempasam.colorfulhex.armor

import at.petrak.hexcasting.xplat.IXplatAbstractions
import jempasam.colorfulhex.ColorfulHexMod
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Quaternion


class MagicianHatRenderer: ArmorRenderer {
    override fun render(
        matrices: MatrixStack, buffer: VertexConsumerProvider,
        stack: ItemStack, entity: LivingEntity, slot: EquipmentSlot, light: Int,
        contextModel: BipedEntityModel<LivingEntity>
    ) {
        if(slot!=EquipmentSlot.HEAD)return

        val player=entity
        if(player !is PlayerEntity)return

        val colorizer=IXplatAbstractions.INSTANCE.getColorizer(player)
        val color=colorizer.getColor(entity.age.toFloat()*3,entity.pos.multiply(0.1))
        val color2=colorizer.getColor((entity.age.toFloat()+15)*3,entity.pos.multiply(0.1))
        val color3=colorizer.getColor((entity.age.toFloat()+30)*3,entity.pos.multiply(0.1))
        val color4=colorizer.getColor((entity.age.toFloat()+45)*3,entity.pos.multiply(0.1))


        // Texture
        val location = ColorfulHexMod/"textures/models/armor/magician_hat.png"

        // Vertexs
        val vertexs = ItemRenderer.getArmorGlintConsumer(
            buffer, RenderLayer.getArmorCutoutNoCull(location), false, stack.hasGlint()
        )

        matrices.push()
        matrices.multiply(Quaternion.fromEulerXyz(contextModel.head.roll, contextModel.head.yaw+Math.PI.toFloat()/2f, contextModel.head.pitch-Math.PI.toFloat()/8f))
        matrices.translate(0.1, -0.4, 0.0)
        val position=matrices.peek().positionMatrix
        val normal=matrices.peek().normalMatrix
        matrices.pop()

        // Get Color
        fun face(color:Int, x:Float,y:Float,z:Float, lx:Float,ly:Float,lz:Float, ux:Float,uy:Float,uz:Float){
            val nx=1-(lx+ux)
            val ny=1-(ly+uy)
            val nz=1-(lz+uz)
            vertexs.vertex(position, x, y, z)
                .color(color)
                .texture(0.0f, 0.0f)
                .overlay(0, 0)
                .light(light)
                .normal(normal,nx,ny,nz)
                .next()
            vertexs.vertex(position, x+lx, y+ly, z+lz)
                .color(color)
                .texture(1.0f, 0.0f)
                .overlay(0, 0)
                .light(light)
                .normal(normal,nx,ny,nz)
                .next()
            vertexs.vertex(position, x+lx+ux, y+ly+uy, z+lz+uz)
                .color(color)
                .texture(1.0f, 1.0f)
                .overlay(0, 0)
                .light(light)
                .normal(normal,nx,ny,nz)
                .next()
            vertexs.vertex(position, x+ux, y+uy, z+uz)
                .color(color)
                .texture(0.0f, 1.0f)
                .overlay(0, 0)
                .light(light)
                .normal(normal,nx,ny,nz)
                .next()
        }

        fun cube(color:Int, x:Float,y:Float,z:Float, lx:Float,ly:Float,lz:Float){
            // Around axis z
            face(color, x,y,z, lx,0.0f,0.0f, 0.0f,ly,0.0f)
            face(color, x,y,z+lz, 0.0f,ly,0.0f, lx,0.0f,0.0f)

            // Around axis y
            face(color, x,y,z, 0.0f,0.0f,lz, lx,0.0f,0.0f)
            face(color, x,y+ly,z, lx,0.0f,0.0f, 0.0f,0.0f,lz)

            // Around axis x
            face(color, x,y,z, 0.0f,ly,0.0f, 0.0f,0.0f,lz)
            face(color, x+lx,y,z, 0.0f,0.0f,lz, 0.0f,ly,0.0f)
        }

        cube(color, -.5f, 0f, -.5f, 1f, 0.1f, 1f)
        cube(color2, -.3f, -0.2f, -.3f, 0.6f, 0.2f, 0.6f)
        cube(color3, -.2f, -0.5f, -.2f, 0.4f, 0.3f, 0.4f)
        cube(color4, -.1f, -0.8f, -.1f, 0.2f, 0.3f, 0.2f)


    }
}