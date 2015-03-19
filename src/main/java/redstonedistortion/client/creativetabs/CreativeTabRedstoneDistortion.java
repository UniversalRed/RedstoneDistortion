package redstonedistortion.client.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabRedstoneDistortion
{
    public static CreativeTabs RDItemTab = new CreativeTabs("RDItemTab")
    {

        @Override
        public Item getTabIconItem()
        {
            return new ItemStack(Items.stick).getItem();
        }
    };

    public static CreativeTabs RDBlockTab = new CreativeTabs("RDBlockTab")
    {

        @Override
        public Item getTabIconItem()
        {
            return new ItemStack(Blocks.furnace).getItem();
        }
    };

    public static void CreativeTabInit()
    {
        //Creative Tab-Items
        CreativeTabs RDItemTab = new CreativeTabs("RDItemTab")
        {

            @Override
            public Item getTabIconItem()
            {
                return Items.stick;
            }
        };

        //Creative Tab-Block
        CreativeTabs RDBlockTab = new CreativeTabs("RDBlockTab")
        {

            @Override
            public Item getTabIconItem()
            {
                return null;
            }
        };
    }
}
