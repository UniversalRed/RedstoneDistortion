package buildcraftAdditions.api.networking;

import io.netty.buffer.ByteBuf;
/**
 * Copyright (c) 2014, AEnterprise
 * http://buildcraftadditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of GNU GPL v3.0
 * Please check the contents of the license located in
 * http://buildcraftadditions.wordpress.com/wiki/licensing-stuff/
 */
public interface ISyncronizedTile {

	public ByteBuf writeToByteBuff(ByteBuf buf);

	public ByteBuf readFromByteBuff(ByteBuf buf);

	public int getX();

	public int getY();

	public int getZ();
}
