package redstonedistortion.network;

import io.netty.buffer.ByteBuf;

/**
 * Created by UniversalRed on 15-02-16.
 */
public interface ISynchronizedTile
{
    public ByteBuf writeToByteBuff(ByteBuf buf);

    public ByteBuf readFromByteBuff(ByteBuf buf);

    public int getX();

    public int getY();

    public int getZ();
}
