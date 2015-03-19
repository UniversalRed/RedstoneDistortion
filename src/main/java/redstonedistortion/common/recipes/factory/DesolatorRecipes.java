package redstonedistortion.common.recipes.factory;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by UniversalRed on 15-02-21.
 */
public class DesolatorRecipes
{
    public static final DesolatorRecipes desolatorRecipes = new DesolatorRecipes();
    public Map desolatingList = new HashMap();

    public static DesolatorRecipes desolating()
    {
        return desolatorRecipes;
    }

    public void recipe(Block block, ItemStack stack)
    {
        this.recipe(Item.getItemFromBlock(block), stack);
    }

    public void recipe(Item item, ItemStack stack)
    {
        this.recipe(new ItemStack(item, 1, 32767), stack);
    }

    public void recipe(ItemStack stack, ItemStack iStack)
    {
        this.desolatingList.put(stack, iStack);
    }

    public ItemStack getResult(ItemStack stack)
    {
        Iterator iterator = this.desolatingList.entrySet().iterator();
        Map.Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Map.Entry)iterator.next();
        }
        while (!this.func_151397_a(stack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean func_151397_a(ItemStack stack, ItemStack iStack)
    {
        return iStack.getItem() == stack.getItem() && (iStack.getItemDamage() == 32767 || stack.getItemDamage() == stack.getItemDamage());
    }
}