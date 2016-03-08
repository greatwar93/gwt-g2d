# Introduction #

This page shows the steps to build the gwt-g2d library from source on svn using Eclipse with the GWT plugin.

# Details #

  1. Check out the trunk from svn: svn checkout http://gwt-g2d.googlecode.com/svn/trunk/ gwt-g2d-read-only
  1. Create a new java project at /gwt-g2d directory (the directory named gwt-g2d that has been checked out from the trunk.
  1. Go to the project Properties, go to the Google -> Web Toolkit. Make sure that "Use Google Web Toolkit" is checked. Click on "Configure SDKs..." and points to the gwt directory if necessary.
  1. When you click "OK", the project should be refreshed with no Compile errors (there may be some warnings though). If you see any Compile errors, make sure that you have Java 1.6 and GWT installed. If you still cannot figure out the error, please post your problem on the group forum.
  1. If you simply want the jar file, you can right click on the project and select "Export", then select Java -> JAR file, select "Export Java source files and resources", and follow the steps as indicated on the screen.
  1. If you want to build the demo project, create a new project at /gwt-g2d-demo directory.
  1. Again, open the project Properties, and go to Google -> Web Toolkit and make sure that everything is correct. Also go to Google -> App Engine and make sure that the path is correct.
  1. If you have the gwt-g2d-demo project in the same workspace as the gwt-g2d project, then you can simply add a project reference to gwt-g2d in your gwt-g2d-demo project (Properties -> Java Build Path -> Projects tab). If they live in different workspaces, you can add the gwt-g2d jar file to your gwt-g2d-demo project (Properties -> Java Build Path -> Libraries).
  1. Go to Properties -> Java Build Path -> Source tab, set your Default output folder to "gwt-g2d-demo/war/WEB-INF/classes" (no quote).
  1. When you click "OK", the project should be refreshed with no Compile errors. If you have made sure that you have Java 1.6, GWT, and AppEngine installed correctly and the problem still persists, feel free to post it on the group forum.
  1. Compile and run it like a normal GWT project <sup> </sup>