package gwt.canvas.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Represents a rectangular image for pixel manipulation.
 * 
 * @author hao1300@gmail.com
 */
public class ImageData {
	private JavaScriptObject imageData;
	private CanvasPixelArray data;
	
	ImageData(JavaScriptObject imageData) {
		this.imageData = imageData;
		this.data = new CanvasPixelArray(getPixelArray(imageData));
	}
	
	/**
	 * Gets the JavaScript Object for image data.
	 * 
	 * @return
	 */
	public JavaScriptObject getImageDataJsObject() {
		return imageData;
	}
	
	/**
	 * Gets the image data representation.
	 * 
	 * @return
	 */
	public CanvasPixelArray getData() {
		return data;
	}
	
	/**
	 * Gets the width of the image data.
	 * 
	 * @return
	 */
	public native int getWidth() /*-{
		return this.@gwt.canvas.client.ImageData::imageData.width;
	}-*/;
	
	/**
	 * Gets the height of the image data.
	 * 
	 * @return
	 */
	public native int getHeight() /*-{
		return this.@gwt.canvas.client.ImageData::imageData.height;
	}-*/;
	
	/**
	 * Gets the pixel array JavaScriptObject from an image data JavaScriptObject.
	 * 
	 * @param imageData
	 * @return
	 */
	private static native JavaScriptObject getPixelArray(JavaScriptObject imageData) /*-{
		return imageData.data;
	}-*/;
}
