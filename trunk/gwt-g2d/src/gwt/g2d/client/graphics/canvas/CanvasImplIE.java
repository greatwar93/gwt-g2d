
package gwt.g2d.client.graphics.canvas;


import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;

/**
 * The IE implementation of the canvas widget.
 */
public class CanvasImplIE extends CanvasImpl {
	
	@Override
	public void init(Element element, int width, int height) {
		this.element = element;
		this.context = ContextImpl.as(getJsContext(element));
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	public void setWidth(int width) {
		element.getStyle().setWidth(width, Unit.PX);
	}
	
	@Override
	public void setHeight(int height) {
		element.getStyle().setHeight(height, Unit.PX);
	}
	
	@Override
	protected native JavaScriptObject getJsContext(Element element) /*-{
		$wnd.G_vmlCanvasManager.initElement(element);
		return element.getContext("2d");
	}-*/;
}
