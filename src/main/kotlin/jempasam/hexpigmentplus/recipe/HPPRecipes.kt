package jempasam.hexpigmentplus.recipe

import net.minecraft.util.registry.Registry

object HPPRecipes {
    init{
        println("Custom Recipe Type"+HatShapedRecipe.IDENTIFIER)
        Registry.register(Registry.RECIPE_TYPE, HatShapedRecipe.IDENTIFIER, HatShapedRecipe.Type)
        Registry.register(Registry.RECIPE_SERIALIZER, HatShapedRecipe.IDENTIFIER, HatShapedRecipe.Serializer)
    }
}