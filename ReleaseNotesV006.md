# What's new #

  * Adds ExternalImageResource as part of the ClientBundle to help Asynchronous image loading.
  * Adds DirectShapeRenderer that gives more controls over the canvas context 2D API.
  * Fixed the unexpected behavior in drawing Circle and other shapes using ShapeBuilder.

## ExternalImageResource vs. ImageLoader ##

In general, please use ExternalImageResource over ImageLoader. It will help ensure that your resources actually exist at compile time and warn you about image loading errors when an image fails to load. Like ImageLoader, you can also specify multiple source values in ExternalImageResource to load multiple images. ImageLoader may be deprecated in the future, so please refrain from using it if possible.

## DirectShapeRenderer vs. ShapeBuilder ##

DirectShapeRenderer exposes a very similar API as ShapeBuilder. The most notable difference is that DirectShapeRenderer requires developers to call beginPath() and closePath() manually. DirectShapeRenderer also uses less memory and less indirection than ShapeBuilder. In general, you may want to consider using DirectShapeRenderer if you need higher controls over shape rendering, or need to draw highly complex shapes in high frequency.