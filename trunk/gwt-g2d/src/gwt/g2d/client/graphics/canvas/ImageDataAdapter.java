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

import gwt.g2d.client.graphics.Color;
import gwt.g2d.client.math.Vector2;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Adapter for accessing the image data object.
 * 
 * @author hao1300@gmail.com
 */
public class ImageDataAdapter {
	private final JavaScriptObject imageData;
	private final CanvasPixelArrayAdapter pixelData;
	private final int width, height;
	
	ImageDataAdapter(JavaScriptObject imageData) {
		this.imageData = imageData;
		this.width = getJsImageWidth();
		this.height = getJsImageHeight();
		this.pixelData = new CanvasPixelArrayAdapter(getPixelArray(imageData));
	}
	
	/**
	 * Gets the color at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return color
	 */
	public final Color getColor(int x, int y) {
		int index = getIndex(x, y);
		return new Color(pixelData.getData(index), 
				pixelData.getData(index + 1), 
				pixelData.getData(index + 2),
				pixelData.getData(index + 3) / 255.0);
	}
	
	/**
	 * Gets the color at the given position.
	 * 
	 * @param position
	 * @return color
	 */
	public final Color getColor(Vector2 position) {
		return getColor(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Gets the red value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return red value
	 */
	public final int getRed(int x, int y) {
		return pixelData.getData(getIndex(x, y));
	}
	
	/**
	 * Gets the red value at the given position.
	 * 
	 * @param position
	 * @return red value
	 */
	public final int getRed(Vector2 position) {
		return getRed(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Gets the green value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return green value
	 */
	public final int getGreen(int x, int y) {
		return pixelData.getData(getIndex(x, y) + 1);
	}
	
	/**
	 * Gets the green value at the given position.
	 * 
	 * @param position
	 * @return green value
	 */
	public final int getGreen(Vector2 position) {
		return getGreen(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Gets the blue value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return blue value
	 */
	public final int getBlue(int x, int y) {
		return pixelData.getData(getIndex(x, y) + 2);
	}
	
	/**
	 * Gets the blue value at the given position.
	 * 
	 * @param position
	 * @return blue value
	 */
	public final int getBlue(Vector2 position) {
		return getBlue(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Gets the alpha value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @return alpha value
	 */
	public final int getAlpha(int x, int y) {
		return pixelData.getData(getIndex(x, y) + 3);
	}
	
	/**
	 * Gets the alpha value at the given position.
	 * 
	 * @param position
	 * @return alpha value
	 */
	public final int getAlpha(Vector2 position) {
		return getAlpha(position.getIntX(), position.getIntY());
	}
	
	/**
	 * Sets the color at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	public final void setColor(int x, int y, Color color) {
		int index = getIndex(x, y);
		pixelData.setData(index, color.getR());
		pixelData.setData(index + 1, color.getG());
		pixelData.setData(index + 2, color.getB());
		pixelData.setData(index + 3, (int) (color.getAlpha() * 255));
	}
	
	/**
	 * Sets the color at the given position.
	 * 
	 * @param position
	 * @param color
	 */
	public final void setColor(Vector2 position, Color color) {
		setColor(position.getIntX(), position.getIntY(), color);
	}
	
	/**
	 * Sets the red value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 */
	public final void setRed(int x, int y, int value) {
		pixelData.setData(getIndex(x, y), value);
	}
	
	/**
	 * Sets the red value at the given position.
	 * 
	 * @param position
	 * @param value
	 */
	public final void setRed(Vector2 position, int value) {
		setRed(position.getIntX(), position.getIntY(), value);
	}
	
	/**
	 * Sets the green value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 */
	public final void setGreen(int x, int y, int value) {
		pixelData.setData(getIndex(x, y) + 1, value);
	}
	
	/**
	 * Sets the green value at the given position.
	 * 
	 * @param position
	 * @param value
	 */
	public final void setGreen(Vector2 position, int value) {
		setGreen(position.getIntX(), position.getIntY(), value);
	}
	
	/**
	 * Sets the blue value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 */
	public final void setBlue(int x, int y, int value) {
		pixelData.setData(getIndex(x, y) + 2, value);
	}
	
	/**
	 * Sets the blue value at the given position.
	 * 
	 * @param position
	 * @param value
	 */
	public final void setBlue(Vector2 position, int value) {
		setBlue(position.getIntX(), position.getIntY(), value);
	}
	
	/**
	 * Sets the alpha value at the given position.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 */
	public final void setAlpha(int x, int y, int value) {
		pixelData.setData(getIndex(x, y) + 3, value);
	}
	
	/**
	 * Sets the alpha value at the given position.
	 * 
	 * @param position
	 * @param value
	 */
	public final void setAlpha(Vector2 position, int value) {
		setAlpha(position.getIntX(), position.getIntY(), value);
	}
	
	/**
	 * Gets the image data representation.
	 * 
	 * @return pixel array
	 */
	public final CanvasPixelArrayAdapter getpixelData() {
		return pixelData;
	}
	
	/**
	 * Gets the underlying image data implementation.
	 */
	final JavaScriptObject getNativeImageData() {
		return imageData;
	}
	
	/**
	 * Gets the index based on the given (x, y) position.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private final int getIndex(int x, int y) {
		return 4 * (y * getWidth() + x);
	}
	
	/**
	 * Gets the image data representation.
	 * 
	 * @return
	 */
	public final CanvasPixelArrayAdapter getData() {
		return pixelData;
	}
	
	/**
	 * Gets the width of the image data.
	 * 
	 * @return
	 */
	public final int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height of the image data.
	 * 
	 * @return
	 */
	public final int getHeight() {
		return height;
	}
	
	/**
	 * Gets the pixel array JavaScriptObject from an image data JavaScriptObject.
	 * 
	 * @param imageData
	 * @return
	 */
	private static final native JavaScriptObject getPixelArray(JavaScriptObject imageData) /*-{
		return imageData.data;
	}-*/;
	
	/**
	 * Gets the width of the image data.
	 */
	private final native int getJsImageWidth() /*-{
		return this.@gwt.g2d.client.graphics.canvas.ImageDataAdapter::imageData.width;
	}-*/;	
	
	/**
	 * Gets the width of the image data.
	 */
	private final native int getJsImageHeight() /*-{
		return this.@gwt.g2d.client.graphics.canvas.ImageDataAdapter::imageData.height;
	}-*/;
}
