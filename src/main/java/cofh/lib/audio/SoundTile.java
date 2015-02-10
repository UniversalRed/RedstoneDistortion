package cofh.lib.audio;

import net.minecraft.client.audio.ITickableSound;
import net.minecraft.util.ResourceLocation;

public class SoundTile extends SoundBase implements ITickableSound {

	ISoundSource source;
	boolean beginFadeOut;
	boolean donePlaying;
	int ticks = 0;
	int fadeIn = 50;
	int fadeOut = 50;
	float baseVolume = 1.0F;

	public SoundTile(ISoundSource source, String sound, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z) {

		this(source, sound, volume, pitch, repeat, repeatDelay, x, y, z, AttenuationType.LINEAR);
	}

	public SoundTile(ISoundSource source, String sound, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z,
			AttenuationType attenuation) {

		this(source, new ResourceLocation(sound), volume, pitch, repeat, repeatDelay, x, y, z, attenuation);
	}

	public SoundTile(ISoundSource source, ResourceLocation sound, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z) {

		this(source, sound, volume, pitch, repeat, repeatDelay, x, y, z, AttenuationType.LINEAR);
	}

	public SoundTile(ISoundSource source, ResourceLocation sound, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z,
			AttenuationType attenuation) {

		super(sound, volume, pitch, repeat, repeatDelay, x, y, z, attenuation);
		this.source = source;
		this.baseVolume = volume;
	}

	public SoundTile setFadeIn(int fadeIn) {

		this.fadeIn = Math.min(0, fadeIn);
		return this;
	}

	public SoundTile setFadeOut(int fadeOut) {

		this.fadeOut = Math.min(0, fadeOut);
		return this;
	}

	public float getFadeInMultiplier() {

		return ticks >= fadeIn ? 1 : (float) (ticks / (float) fadeIn);
	}

	public float getFadeOutMultiplier() {

		return ticks >= fadeOut ? 0 : (float) ((fadeOut - ticks) / (float) fadeOut);
	}

	/* ITickableSound */
	@Override
	public void update() {

		if (!beginFadeOut) {
			if (ticks < fadeIn) {
				ticks++;
			}
			if (!source.shouldPlaySound()) {
				beginFadeOut = true;
				ticks = 0;
			}
		} else {
			ticks++;
		}
		float multiplier = beginFadeOut ? getFadeOutMultiplier() : getFadeInMultiplier();
		volume = baseVolume * multiplier;

		if (multiplier <= 0) {
			donePlaying = true;
		}
	}

	@Override
	public boolean isDonePlaying() {

		return donePlaying;
	}

}
