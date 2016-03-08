## Announcement ##

**2012-01-10** This project is now revived, and will go into a different direction from on. Originally, this project was supposed to be a comprehensive library for 2D manipulation in GWT, based on the excanvas.js project, which was later integrated in GWT directly.

Because it is no longer necessary to provide an implementation for canvas manipulation yourself,  gwt-g2d will now focus on providing a more convenient interface to access this built-in functionality, providing a higher-level API for canvas manipulation that makes it easier to build large projects on.

The current SVN version already removes the excanvas.js dependency. Expect an extensive upgrade shortly.

**2011-03-31** Small fixes to version 0.08 demo codes.  Reuploaded to the download section.

**2011-03-29** Version 0.08 is released.  This version is GWT 2.2 compatible and will probably be the last release for gwt-g2d since the canvas element is now officially supported by GWT.

## What g2d is ##

The famous HTML canvas has made its way into GWT, most notably the gwt-canvas project and GWTCanvas in GWT incubator. While neither of them is ready for production, they are solid enough for enthusiast to get started.

However, there are not a lot of support in 2D graphics manipulation. For example, if you want to draw a cubic curve, would you rather be passing 3 points or 6 double parameters? When you want to fill something with a color, would you rather be passing a type-safe color object than a string? What if you want to interpolate between two values, two points, or two colors?

This project seeks to simplify 2D graphics manipulation by providing some utility class that makes it easier and type-safer to use canvas.

If you are looking into WebGL/3D rendering support, please visit my new library [gwt-g3d](http://code.google.com/p/gwt-g3d). It is still fairly unstable, but some feedbacks would be greatly appreciated.

## Who should not use g2d ##

You should not use g2d if you want to find something stable that runs on the older browsers. Most recent browsers now support canvas to a good extent. IE9 support is work in progress.  The older IE versions are supported via excanvas.

### Demo ###
http://gwt-g2d.appspot.com/

The source is available on svn: http://code.google.com/p/gwt-g2d/source/browse/#svn/trunk/gwt-g2d-demo/src/gwt/g2d/client/demo

### How to use the library ###
Adds gwt-g2d.jar to your build path, and add the following line to your **.gwt.xml file:
```
<inherits name='gwt.g2d.g2d'/>
```**

### GWTTestCase issue ###

If you need to write unit tests for gwt-g2d, the default way of running GWTTestCase may fail as some functionalities are not yet implemented in gecko1\_8, the default environment where GWTTestCase runs in. In this case, you may want to run the test case manually by using the VM Arguments:
```
-Dgwt.args="-runStyle Manual:1"
```
and run the test on your browser.

(Thanks to DamianRodziewicz for providing the information)

## What g2d needs to do ##

### Done ###
  * Type-safe Color class.
  * Provides a set of known colors (KnownColor class).
  * Vector2 class for 2D points manipulation.
  * Rectangle, Arc, and Circle.
  * Wrapper class for canvas (Surface class) that accept wrapper objects such as Vector2, Color, Rectangle, etc.
  * Some basic demo.
  * A shape builder class for path drawing.
  * Many type-safe enums, such as LineCap, LineJoin, TextAlign, etc.
  * Support for Shadow.
  * Support for image data manipulation.
  * Matrix manipulation class.
  * Video and audio element

### To Do ###
  * Type-safe Font class.
  * Unit test for each class.

### To look into ###
  * More controls over the keyboard.
## Contacts ##

Any feedback/question/request please visits [discussion group](http://groups.google.com/group/gwt-g2d)

If you are using the gwt-g2d library in your project, I would greatly appreciate it if you can send me a link to your project so I can get an idea on how it is being used and what kind of features/changes would be good to have in the future.

### Showcase ###

  * GoChild: http://gochild2009.appspot.com
  * GWT Equation/Formula Editor: [Project page](http://code.google.com/p/gwt-equation-editor/) [Demo](http://formula-editor.appspot.com/)
  * GWT port for java.awt.geom: http://github.com/ustramooner/gwt-geom/
  * Tile based game engine: http://canvasworldtest.appspot.com/