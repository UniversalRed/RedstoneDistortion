package redstonedistortion.common.packets;

import buildcraftAdditions.api.networking.MessageByteBuff;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import redstonedistortion.bases.libs.ModLibs;

/**
 * Created by UniversalRed on 15-02-16.
 */
public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModLibs.CHANNEL);

    public static void init()
    {
        //Handler class		Message class	      ID   Target side
        INSTANCE.registerMessage(MessageTileCell.class, MessageTileCell.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileMachine.class, MessageTileMachine.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(MessageConfiguration.class, MessageConfiguration.class, 2, Side.SERVER);
        INSTANCE.registerMessage(MessageByteBuff.class, MessageByteBuff.class, 3, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileProvider.class, MessageTileProvider.class, 4, Side.CLIENT);
    }
}