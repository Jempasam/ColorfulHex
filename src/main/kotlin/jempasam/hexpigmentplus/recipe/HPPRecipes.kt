package jempasam.hexpigmentplus.recipe

import jempasam.hexpigmentplus.CRegistry


object HPPRecipes {
    init{
        println("Custom Recipe Type"+HatShapedRecipe.IDENTIFIER)
        CRegistry.register(CRegistry.RECIPE_TYPE, HatShapedRecipe.IDENTIFIER, HatShapedRecipe.Type)
        CRegistry.register(CRegistry.RECIPE_SERIALIZER, HatShapedRecipe.IDENTIFIER, HatShapedRecipe.Serializer)
    }
}