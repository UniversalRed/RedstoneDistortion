package cofh.lib.audio;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.audio.ISound;
import net.minecraft.util.ResourceLocation;

/**
 * Generic ISound class with lots of constructor functionality. Required because - of course - Mojang has no generic that lets you specify *any* arguments for
 * this.
 * 
 * @author skyboy
 * 
 */
@SideOnly(Side.CLIENT)
public class SoundBase implements ISound {

	protected AttenuationType attenuation;
	protected final ResourceLocation sound;
	protected float volume;
	protected float pitch;
	protected float x;
	protected float y;
	protected float z;
	protected boolean repeat;
	protected int repeatDelay;

	public SoundBase(String sound) {

		this(sound, 0);
	}

	public SoundBase(String sound, float volume) {

		this(sound, volume, 0);
	}

	public SoundBase(String sound, float volume, float pitch) {

		this(sound, volume, pitch, false, 0);
	}

	public SoundBase(String sound, float volume, float pitch, boolean repeat, int repeatDelay) {

		this(sound, volume, pitch, repeat, repeatDelay, 0, 0, 0, AttenuationType.NONE);
	}

	public SoundBase(String sound, float volume, float pitch, double x, double y, double z) {

		this(sound, volume, pitch, false, 0, x, y, z);
	}

	public SoundBase(String sound, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z) {

		this(sound, volume, pitch, repeat, repeatDelay, x, y, z, AttenuationType.LINEAR);
	}

	public SoundBase(String sound, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z, AttenuationType attenuation) {

		this(new ResourceLocation(sound), volume, pitch, repeat, repeatDelay, x, y, z, attenuation);
	}

	public SoundBase(ResourceLocation sound) {

		this(sound, 0);
	}

	public SoundBase(ResourceLocation sound, float volume) {

		this(sound, volume, 0);
	}

	public SoundBase(ResourceLocation sound, float volume, float pitch) {

		this(sound, volume, pitch, false, 0);
	}

	public SoundBase(ResourceLocation sound, float volume, float pitch, boolean repeat, int repeatDelay) {

		this(sound, volume, pitch, repeat, repeatDelay, 0, 0, 0, AttenuationType.NONE);
	}

	public SoundBase(ResourceLocation sound, float volume, float pitch, double x, double y, double z) {

		this(sound, volume, pitch, false, 0, x, y, z);
	}

	public SoundBase(ResourceLocation sound, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z) {

		this(sound, volume, pitch, repeat, repeatDelay, x, y, z, AttenuationType.LINEAR);
	}

	public SoundBase(ResourceLocation sound, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z,
			AttenuationType attenuation) {

		this.attenuation = attenuation;
		this.sound = sound;
		this.volume = volume;
		this.pitch = pitch;
		this.x = (float) x;
		this.y = (float) y;
		this.z = (float) z;
		this.repeat = repeat;
		this.repeatDelay = repeatDelay;
	}

	public SoundBase(SoundBase other) {

		this.attenuation = other.attenuation;
		this.sound = other.sound;
		this.volume = other.volume;
		this.pitch = other.pitch;
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
		this.repeat = other.repeat;
		this.repeatDelay = other.repeatDelay;
	}

	@Override
	public AttenuationType getAttenuationType() {

		return attenuation;
	}

	@Override
	public ResourceLocation getPositionedSoundLocation() {

		return sound;
	}

	@Override
	public float getVolume() {

		return volume;
	}

	@Override
	public float getPitch() {

		return pitch;
	}

	@Override
	public float getXPosF() {

		return x;
	}

	@Override
	public float getYPosF() {

		return y;
	}

	@Override
	public float getZPosF() {

		return z;
	}

	@Override
	public boolean canRepeat() {

		return repeat;
	}

	@Override
	public int getRepeatDelay() {

		return repeatDelay;
	}

}
