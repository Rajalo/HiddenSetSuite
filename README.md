# HiddenSetSuite
 A Processing-based Java program for demonstrating three versions of the hidden set problem in histogram polygons

# How to run

If you have the .jar file, you can simply run it (usually just double clicking).

Elsewise, add all the src classes to a new IntelliJ Idea project. additionally place the Ti86PcBold.tff file just outside src.
Download processing from here: https://processing.org/download
Extract the zip file, and then go back into your IntelliJ Idea project.
In IntelliJ, go to File -> Project Structure -> Libraries. Click the plus and select "Java". 
Navigate to where you extracted the processing folder. Go to core/library/core.jar and select core.jar.

Now go to Project Structure -> Artifacts, click the plus and select "JAR" -> "From modules with dependencies"
Select "Main" as the main class and hit OK
Now make sure to click the plus under "Output Layout" and add the Ti86PcBold.tff file.

Select OK in the bottom right and then go to Build -> Build Artifacts and then select Build.
This will create a new JAR which you can then use to run the program

Note: The font does not belong to me.

# How to use

The program itself is relatively intuitive. Pencil in your histogram by adding in new points via a Ctrl+Click, move around existing points by dragging and press delete when selecting a point to delete it.

Click any of the three labelled buttons to run one of the three algorithms:

1) Hidden Set (Browne and Chiu)
2) Hidden Vertex Set (Variant of Browne and Chiu using theorems from Bajuelos et al.)
3) r-Hidden Guard Set (Hoorfar and Bagheri) 