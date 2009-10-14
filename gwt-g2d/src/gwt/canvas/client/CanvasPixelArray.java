package gwt.canvas.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Interface that provides ordered, indexed access to the color components of 
 * each pixel of the image data. The data is represented in left-to-right order, 
 * row by row top to bottom, starting with the top left, with each pixel's red, 
 * green, blue, and alpha components being given in that order for each pixel.
 * 
 * @author hao1300@gmail.com
 */
public class CanvasPixelArray {
	private JavaScriptObject pixelArray;
	
	CanvasPixelArray(JavaScriptObject pixelArray) {
		this.pixelArray = pixelArray;
	}
	
	/**
	 * Gets the JavaScriptObject representation of the CanvasPixelArray.
	 * 
	 * @return
	 */
	public JavaScriptObject getPixelArrayJsObject() {
		return pixelArray;
	}
	
	/**
	 * Gets the length the pixel array, which is height * width * 4.
	 * 
	 * @return
	 */
	public native int getLength() /*-{
		return this.@gwt.canvas.client.CanvasPixelArray::pixelArray.length;
	}-*/;
	
	/**
	 * Gets the pixel color value at the given index. 
	 * 
	 * @param index 0 based index. index 0 to 3 represents the R, G, B, A values 
	 * of the top left pixel, and index 4 to 7 represents the color values of the
	 * next pixel to the right of the top left pixel.
	 * @return the color value at the given index.
	 */
	public native byte getData(int index) /*-{
		return this.@gwt.canvas.client.CanvasPixelArray::pixelArray[index];
	}-*/;
	
	/**
	 * Sets the pixel color value at the given index. 
	 * 
	 * @param index 0 based index. index 0 to 3 represents the R, G, B, A values 
	 * of the top left pixel, and index 4 to 7 represents the color values of the
	 * next pixel to the right of the top left pixel.
	 * @param data the color value at the given index.
	 */
	public native void setData(int index, int data) /*-{
		this.@gwt.canvas.client.CanvasPixelArray::pixelArray[index] = data;
	}-*/;
}
