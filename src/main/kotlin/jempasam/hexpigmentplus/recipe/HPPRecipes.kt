package jempasam.hexpigmentplus.recipe

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry


object HPPRecipes {
    init{
        println("Custom Recipe Type"+HatShapedRecipe.IDENTIFIER)
        Registry.register(Registries.RECIPE_TYPE, HatShapedRecipe.IDENTIFIER, HatShapedRecipe.Type)
        Registry.register(Registries.RECIPE_SERIALIZER, HatShapedRecipe.IDENTIFIER, HatShapedRecipe.Serializer)
    }
}