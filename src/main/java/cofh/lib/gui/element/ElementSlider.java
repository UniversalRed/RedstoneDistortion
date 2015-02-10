package cofh.lib.gui.element;

import static cofh.lib.gui.element.ElementButtonManaged.*;

import cofh.lib.gui.GuiBase;
import cofh.lib.gui.GuiColor;

import org.lwjgl.opengl.GL11;

public abstract class ElementSlider extends ElementBase {

	protected int _value;
	protected int _valueMin;
	protected int _valueMax;
	protected int _sliderWidth;
	protected int _sliderHeight;

	protected boolean _isDragging;

	public int borderColor = new GuiColor(120, 120, 120, 255).getColor();
	public int backgroundColor = new GuiColor(0, 0, 0, 255).getColor();

	protected ElementSlider(GuiBase containerScreen, int x, int y, int width, int height, int maxValue) {

		this(containerScreen, x, y, width, height, maxValue, 0);
	}

	protected ElementSlider(GuiBase containerScreen, int x, int y, int width, int height, int maxValue, int minValue) {

		super(containerScreen, x, y, width, height);
		_valueMax = maxValue;
		_valueMin = minValue;
	}

	public ElementSlider setColor(int backgroundColor, int borderColor) {

		this.borderColor = borderColor;
		this.backgroundColor = backgroundColor;
		return this;
	}

	public ElementSlider setSliderSize(int width, int height) {

		_sliderWidth = width;
		_sliderHeight = height;
		return this;
	}

	public ElementSlider setValue(int value) {

		value = Math.max(_valueMin, Math.min(_valueMax, value));
		if (value != _value) {
			_value = value;
			onValueChanged(_value);
		}
		return this;
	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float gameTicks) {

		drawModalRect(posX - 1, posY - 1, posX + sizeX + 1, posY + sizeY + 1, borderColor);
		drawModalRect(posX, posY, posX + sizeX, posY + sizeY, backgroundColor);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	protected void drawSlider(int mouseX, int mouseY, int sliderX, int sliderY) {

		int sliderWidth = _sliderWidth;
		int sliderHeight = _sliderHeight;

		if (!isEnabled()) {
			gui.bindTexture(DISABLED);
		} else if (isHovering(mouseX, mouseY)) {
			gui.bindTexture(HOVER);
		} else {
			gui.bindTexture(ENABLED);
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(sliderX, sliderY, 0, 0, sliderWidth / 2, sliderHeight / 2);
		drawTexturedModalRect(sliderX, sliderY + sliderHeight / 2, 0, 256 - sliderHeight / 2, sliderWidth / 2, sliderHeight / 2);
		drawTexturedModalRect(sliderX + sliderWidth / 2, sliderY, 256 - sliderWidth / 2, 0, sliderWidth / 2, sliderHeight / 2);
		drawTexturedModalRect(sliderX + sliderWidth / 2, sliderY + sliderHeight / 2, 256 - sliderWidth / 2, 256 - sliderHeight / 2, sliderWidth / 2,
				sliderHeight / 2);
	}

	@Override
	public void drawForeground(int mouseX, int mouseY) {

		int sliderX = posX + getSliderX();
		int sliderY = posY + getSliderY();

		drawSlider(mouseX, mouseY, sliderX, sliderY);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	protected boolean isHovering(int x, int y) {

		return intersectsWith(x, y);
	}

	public int getSliderX() {

		return 0;
	}

	public int getSliderY() {

		return 0;
	}

	@Override
	public boolean onMousePressed(int mouseX, int mouseY, int mouseButton) {

		_isDragging = mouseButton == 0;
		update(mouseX, mouseY);
		return true;
	}

	@Override
	public void onMouseReleased(int mouseX, int mouseY) {

		if (_isDragging)
			onStopDragging();
		_isDragging = false;
	}

	@Override
	public void update(int mouseX, int mouseY) {

		if (_isDragging) {
			dragSlider(mouseX - posX, mouseY - posY);
		}
	}

	protected abstract void dragSlider(int x, int y);

	@Override
	public boolean onMouseWheel(int mouseX, int mouseY, int movement) {

		if (movement > 0) {
			setValue(_value - 1);
		} else if (movement < 0) {
			setValue(_value + 1);
		}
		return true;
	}

	public void onValueChanged(int value) {

		return;
	}

	public void onStopDragging() {

		return;
	}
}
