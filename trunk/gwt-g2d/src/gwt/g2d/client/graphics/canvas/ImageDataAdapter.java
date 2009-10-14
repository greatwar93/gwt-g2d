/*
 * Copyright 2009 Hao Nguyen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package gwt.g2d.client.graphics.canvas;

import gwt.canvas.client.ImageData;
import gwt.g2d.client.graphics.Color;
import gwt.g2d.client.math.Vector2;

/**
 * Adapter for accessing the image data object.
 * 
 * @author hao1300@gmail.com
 */
public class ImageDataAdapter {
	private ImageData nativeImageData;
	private CanvasPixelArrayAdapter canvasPixelArrayAdapter;
	
	ImageDataAdapter(ImageData nativeImageData) {
		this.nativeImageData = nativeImageData;
		this.canvasPixelArrayAdapter = new CanvasPixelArrayAdapter(nativeImageData.getData());
	}
	
	/**
	 * Gets the width of the image data.
	 * 
	 * @return.
	 */
	public int getWidth() {
		return nativeImageData.getWidth();
	}
	
	/**
	 * Gets the height of the image data.
	 * 
	 * @return.
	 */
	public int getHeight() {
		return nativeImageData.getHeight();
	}
	
	/**
	 * Gets the color at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Color getColor(int x, int y) {
		int index = getIndex(x, y);
		return new Color(canvasPixelArrayAdapter.getData(index), 
				canvasPixelArrayAdapter.getData(index + 1), 
				canvasPixelArrayAdapter.getData(index + 2),
				canvasPixelArrayAdapter.getData(index + 3) / 255.0);
	}
	
	/**
	 * Gets the color at the given position.
	 * 
	 * @param position
	 * @return
	 */
	public Color getColor(Vector2 position) {
		return getColor(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Gets the red value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public byte getRed(int x, int y) {
		return canvasPixelArrayAdapter.getData(getIndex(x, y));
	}
	
	/**
	 * Gets the red value at the given position.
	 * 
	 * @param position
	 * @return
	 */
	public byte getRed(Vector2 position) {
		return getRed(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Gets the green value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public byte getGreen(int x, int y) {
		return canvasPixelArrayAdapter.getData(getIndex(x, y) + 1);
	}
	
	/**
	 * Gets the green value at the given position.
	 * 
	 * @param position
	 * @return
	 */
	public byte getGreen(Vector2 position) {
		return getGreen(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Gets the blue value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public byte getBlue(int x, int y) {
		return canvasPixelArrayAdapter.getData(getIndex(x, y) + 2);
	}
	
	/**
	 * Gets the blue value at the given position.
	 * 
	 * @param position
	 * @return
	 */
	public byte getBlue(Vector2 position) {
		return getBlue(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Gets the alpha value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public byte getAlpha(int x, int y) {
		return canvasPixelArrayAdapter.getData(getIndex(x, y) + 3);
	}
	
	/**
	 * Gets the alpha value at the given position.
	 * 
	 * @param position
	 * @return
	 */
	public byte getAlpha(Vector2 position) {
		return getAlpha(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Sets the color at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param color
	 * @return
	 */
	public void setColor(int x, int y, Color color) {
		int index = getIndex(x, y);
		canvasPixelArrayAdapter.setData(index, color.getR());
		canvasPixelArrayAdapter.setData(index + 1, color.getG());
		canvasPixelArrayAdapter.setData(index + 2, color.getB());
		canvasPixelArrayAdapter.setData(index + 3, (int) (color.getAlpha() * 255));
	}
	
	/**
	 * Sets the color at the given position.
	 * 
	 * @param position
	 * @param color
	 * @return
	 */
	public void setColor(Vector2 position, Color color) {
		setColor(position.getIntX(), position.getIntY(), color);
	}
	
	/**
	 * Sets the red value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 * @return
	 */
	public void setRed(int x, int y, int value) {
		canvasPixelArrayAdapter.setData(getIndex(x, y), value);
	}
	
	/**
	 * Sets the red value at the given position.
	 * 
	 * @param position
	 * @param value
	 * @return
	 */
	public void setRed(Vector2 position, int value) {
		setRed(position.getIntX(), position.getIntY(), value);
	}
	
	/**
	 * Sets the green value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 * @return
	 */
	public void setGreen(int x, int y, int value) {
		canvasPixelArrayAdapter.setData(getIndex(x, y) + 1, value);
	}
	
	/**
	 * Sets the green value at the given position.
	 * 
	 * @param position
	 * @param value
	 * @return
	 */
	public void setGreen(Vector2 position, int value) {
		setGreen(position.getIntX(), position.getIntY(), value);
	}
	
	/**
	 * Sets the blue value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 * @return
	 */
	public void setBlue(int x, int y, int value) {
		canvasPixelArrayAdapter.setData(getIndex(x, y) + 2, value);
	}
	
	/**
	 * Sets the blue value at the given position.
	 * 
	 * @param position
	 * @param value
	 * @return
	 */
	public void setBlue(Vector2 position, int value) {
		setBlue(position.getIntX(), position.getIntY(), value);
	}
	
	/**
	 * Sets the alpha value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 * @return
	 */
	public void setAlpha(int x, int y, int value) {
		canvasPixelArrayAdapter.setData(getIndex(x, y) + 3, value);
	}
	
	/**
	 * Sets the alpha value at the given position.
	 * 
	 * @param position
	 * @param value
	 * @return
	 */
	public void setAlpha(Vector2 position, int value) {
		setAlpha(position.getIntX(), position.getIntY(), value);
	}
	
	/**
	 * Gets the image data representation.
	 * 
	 * @return
	 */
	public CanvasPixelArrayAdapter getCanvasPixelArrayAdapter() {
		return canvasPixelArrayAdapter;
	}
	
	/**
	 * Gets the underlying image data implementation.
	 */
	ImageData getNativeImageData() {
		return nativeImageData;
	}
	
	/**
	 * Gets the index based on the given (x, y) position.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private int getIndex(int x, int y) {
		return 4 * (y * getWidth() + x);
	}
}
