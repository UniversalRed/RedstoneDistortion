package redstonedistortion.network;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import redstonedistortion.libs.ModLibs;
import redstonedistortion.packets.RDMessage;
import redstonedistortion.packets.RDMessageByteBuf;
import redstonedistortion.packets.RDTileMessage;

public class NetWorkHandler
{
    public static SimpleNetworkWrapper INSTANCE;

    public static void init()
    {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModLibs.CHANNEL);

        INSTANCE.registerMessage(RDMessageByteBuf.Handler.class, RDMessageByteBuf.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(RDMessage.Handler.class, RDMessage.class, 1, Side.SERVER);
        INSTANCE.registerMessage(RDTileMessage.Handler.class, RDTileMessage.class, 2, Side.SERVER);

    }

    public static void sendToServer(IMessage message){
        INSTANCE.sendToServer(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player){
        INSTANCE.sendTo(message, player);
    }

    public static void sendToAllAround(IMessage message, TargetPoint point){
        INSTANCE.sendToAllAround(message, point);
    }

    public static void sendToAll(IMessage message){
        INSTANCE.sendToAll(message);
    }

    public static void sendToDimension(IMessage message, int dimensionId){
        INSTANCE.sendToDimension(message, dimensionId);
    }

}