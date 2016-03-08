## Method Chaining ##

gwt-g2d makes extensive use of chaining. For instance, the majority of methods in the Surface class return the Surface instance itself. This allows developers to write codes like:

```
// Draws a rectangle with shadow.
surface.setShadowOffset(new Vector2(5, 5))
  .setShadowBlur(4)
  .setShadowColor(new Color(0, 0, 0, .5))
  .fillRectangle(5, 5, 200, 100);
```


## Using Context ##

While the Surface class provides pretty all methods that Context provide plus some helper methods to make development with Canvas easier and type-safer, some of these methods may not be the most efficient for certain performance critical application. The surface.fillShape() method, for instance, may take in a Shape that is built using ShapeBuilder. If the Shape is created using n operations, then ShapeBuilder will be storing all n operations in memory, then redoing those n operations when fillShape() is called. Performance/memory should not suffer too badly if the shapes are not too complex and/or highly reusable, but if a new complex shape is to be drawn very frequently, it may be more efficient to directly draw it using Context or ShapeRenderer (see next section).

Context for a Surface can be obtained via surface.getContext() method. Any method from Surface will leave the context in a consistent state, but some methods from Context may not leave Surface in a consistent state (for example, if you call beginPath(), it is not safe to call surface.fillShape() or surface.strokeShape() without calling closePath()). Unless your profiler indicates that the surface method is the bottleneck, you should not consider using Context at all.

## Using DirectShapeRenderer ##

As of version 0.06, if you need to higher controls over shape rendering or needs to draw new complex shape in high frequency, then you should also consider using DirectShapeRenderer. DirectShapeRenderer exposes a similar API as ShapeBuilder while allowing deveopers to make calls like beginPath() and closePath(). Like Context, some methods from DirectShapeRenderer may leave the Surface in an inconsistent state. However, as long as you are familiar with the canvas specification, you should have no problem using it.