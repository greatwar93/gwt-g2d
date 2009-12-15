
package gwt.canvas.client.impl;

import com.google.gwt.user.client.Element;

/**
 * The IE implementation of the canvas widget.
 */
public class CanvasImplIE extends CanvasImpl {
	
	protected native void init(Element element) /*-{
		$wnd.G_vmlCanvasManager.initElement(element);
		this.@gwt.canvas.client.impl.CanvasImpl::context = element.getContext("2d");
	}-*/;
	
//	@Override
//	public native void setWidth(String width) /*-{
//		var el = this.@gwt.canvas.client.impl.CanvasImpl::element;
//		el.style.width = width;
//		el.firstChild.style.width = width;
//	}-*/;
//	
//	@Override
//	public native void setHeight(String height) /*-{
//		var el = this.@gwt.canvas.client.impl.CanvasImpl::element;
//		el.style.height = height;
//		el.firstChild.style.height = height;
//	}-*/;
}
