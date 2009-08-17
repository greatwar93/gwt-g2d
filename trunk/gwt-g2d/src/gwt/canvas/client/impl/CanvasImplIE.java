
package gwt.canvas.client.impl;

/**
 * The IE implementation of the canvas widget.
 */
public class CanvasImplIE extends CanvasImpl {
	
	protected native void init() /*-{
		$wnd.G_vmlCanvasManager.initElement(this.@gwt.canvas.client.impl.CanvasImpl::element);
		this.@gwt.canvas.client.impl.CanvasImpl::context = this.@gwt.canvas.client.impl.CanvasImpl::element.getContext("2d");
	}-*/;
	
	@Override
	public native void setWidth(String width) /*-{
		var el = this.@gwt.canvas.client.impl.CanvasImpl::element;
		el.style.width = width;
		el.firstChild.style.width = width;
	}-*/;
	
	@Override
	public native void setHeight(String height) /*-{
		var el = this.@gwt.canvas.client.impl.CanvasImpl::element;
		el.style.height = height;
		el.firstChild.style.height = height;
	}-*/;
}
