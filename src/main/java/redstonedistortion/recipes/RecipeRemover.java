package redstonedistortion.recipes;

/**
 * Created by UniversalRed on 15-02-21.
 */

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;

public class RecipeRemover {

    public static void removeRecipes(ItemStack resultItem) {
        ArrayList recipes = (ArrayList) CraftingManager.getInstance().getRecipeList();

        for (int scan = 0; scan < recipes.size(); scan++) {
            IRecipe tmpRecipe = (IRecipe) recipes.get(scan);
            ItemStack recipeResult = tmpRecipe.getRecipeOutput();
            if (ItemStack.areItemStacksEqual(resultItem, recipeResult)) {
                if (tmpRecipe instanceof ShapedOreRecipe) {
                    ShapedOreRecipe recipe = (ShapedOreRecipe) tmpRecipe;
                    recipeResult = recipe.getRecipeOutput();
                }
                if (tmpRecipe instanceof ShapelessRecipes) {
                    ShapelessRecipes recipe = (ShapelessRecipes) tmpRecipe;
                    recipeResult = recipe.getRecipeOutput();
                }
                recipes.remove(scan);
            }
        }
    }
}
