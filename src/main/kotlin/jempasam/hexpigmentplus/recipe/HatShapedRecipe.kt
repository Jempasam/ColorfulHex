package jempasam.hexpigmentplus.recipe

import com.google.gson.JsonObject
import jempasam.hexpigmentplus.HPPMod
import jempasam.hexpigmentplus.RecipeInventory
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.CraftingRecipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.recipe.ShapedRecipe
import net.minecraft.util.Identifier
import net.minecraft.util.JsonHelper
import net.minecraft.world.World

class HatShapedRecipe(val model: Int, val recipe: ShapedRecipe): CraftingRecipe{
    override fun matches(inventory: RecipeInventory, world: World) = recipe.matches(inventory,world)

    override fun craft(inventory: RecipeInventory/*, registries: DynamicRegistryManager*/): ItemStack {
        val ret=recipe.craft(inventory/*,registries*/)
        ret.orCreateNbt.putInt("CustomModelData",model)
        return ret
    }

    override fun fits(width: Int, height: Int) = recipe.fits(width,height)

    override fun getOutput(/*registries: DynamicRegistryManager*/): ItemStack {
        val ret=recipe.getOutput(/*registries*/)
        ret.orCreateNbt.putInt("CustomModelData",model)
        return ret
    }

    override fun getType() = recipe.type

    override fun getGroup() = recipe.group

    override fun getIngredients() = recipe.ingredients

    override fun getRemainder(inventory: RecipeInventory) = recipe.getRemainder(inventory)

    override fun createIcon() = recipe.createIcon()

    override fun isEmpty() = recipe.isEmpty

    override fun toString() = recipe.toString()

    //override fun getCategory() = recipe.category

    override fun isIgnoredInRecipeBook() = recipe.isIgnoredInRecipeBook

    override fun getId() = recipe.id

    override fun getSerializer(): RecipeSerializer<*> = Serializer

    object Serializer : RecipeSerializer<HatShapedRecipe> {
        override fun read(identifier: Identifier, jsonObject: JsonObject): HatShapedRecipe {
            val modelId=JsonHelper.getInt(jsonObject, "model", 0)
            return HatShapedRecipe(modelId,ShapedRecipe.Serializer.SHAPED.read(identifier,jsonObject))
        }

        override fun read(identifier: Identifier, packetByteBuf: PacketByteBuf): HatShapedRecipe {
            val modelId=packetByteBuf.readInt()
            return HatShapedRecipe(modelId,ShapedRecipe.Serializer.SHAPED.read(identifier,packetByteBuf))
        }

        override fun write(packetByteBuf: PacketByteBuf, shapedRecipe: HatShapedRecipe) {
            packetByteBuf.writeInt(shapedRecipe.model)
            ShapedRecipe.Serializer.SHAPED.write(packetByteBuf,shapedRecipe.recipe)
        }
    }

    object Type: RecipeType<HatShapedRecipe> {
        override fun toString() = HatShapedRecipe.IDENTIFIER.toString()
    }

    companion object {
        val IDENTIFIER=HPPMod/"hat_shaped"
    }
}