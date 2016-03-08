The primary way to interact with gwt-g2d's canvas is through the Surface class. Surface is a widget, so like any other widget, it can be added to any panel.

```
public class MainDemo implements EntryPoint {
  public void onModuleLoad() {
    // Creates a surface of dimension 200 x 200.
    Surface surface = new Surface(200, 200);

    // Adds the canvas to the root panel.
    // We need to attach the Surface Widget to the Document after its creation 
    // (directly or indirectly) in order for it to work under IE.
    RootPanel.get().add(surface);

    // Draws a green yellow circle in the middle of cornflower blue background.
    surface.fillBackground(KnownColor.CORNFLOWER_BLUE)
        .setFillStyle(KnownColor.GREEN_YELLOW)
	.fillShape(new ShapeBuilder()
           .drawCircle(100, 100, 50).build());    
  }
}
```

The primary way to draw vector graphics is through the ShapeBuilder class. Calling ShapeBuilder.build() creates a Shape that can then be drawn onto the surface.

For more examples, please look at the demos on svn.

Please make sure that you have added gwt-g2d.jar to your build path, and the following line to your .gwt.xml file:
```
<inherits name='gwt.g2d.g2d'/>
```

NOTE (Thanks to johan.piquet):

To make this work for IE8, you must declare the doctype as:
```
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
```

Note that actually the GWT Eclipse plugin template declare it as is (for v2.01):
```
<!doctype html>
```
which doesn't work correctly under IE8.