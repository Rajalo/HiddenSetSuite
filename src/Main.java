import processing.core.PApplet;
import processing.core.PFont;

import java.io.IOException;
import java.io.OutputStream;

public class Main extends PApplet {
    public static Histogram histogram;
    public static Title title;
    public static PApplet applet;
    public static Menu menu;
    public static AlgorithmController algorithmController;
    public static void main(String[] args)
    {
        PApplet.main("Main",args);
        histogram = new Histogram();
        title = new Title();
        applet = new PApplet();
        menu = new Menu();
        algorithmController = null;
    }
    public void settings()
    {
        size(800,700, JAVA2D);
        noSmooth();
    }
    public void setup()
    {
        PFont font = createFont("Ti86PcBold.ttf",10);
        textFont(font);
    }
    public void draw()
    {
        background(100,100,100);
        g.strokeWeight(3);
        histogram.draw(this);
        title.draw(this);
        menu.draw(this);
        if (algorithmController != null)
        {
            algorithmController.draw(this);
        }
    }
    @Override
    public void mouseClicked()
    {
        if (menu.algorithmMode== Menu.algorithm.NONE) {
            if (keyPressed && keyCode == CONTROL)
            {
                if (Math.abs(histogram.selected.getX()-mouseX) > 5 && Math.abs(histogram.selected.getY()-mouseY) > 5)
                    histogram.insertVertex(mouseX, mouseY);
            }
            if(menu.checkButtonPress(mouseX, mouseY))
            {
                histogram.unselect();
                algorithmController = new AlgorithmController(menu.algorithmMode);
            }
        }
        else
        {
            if (algorithmController.steps.isEmpty())
            {
                menu.backToNormal();
            }
        }
    }
    @Override
    public void mousePressed()
    {
        if (menu.algorithmMode== Menu.algorithm.NONE)
        {
            if (histogram.selected != null)
                histogram.selected.selected=false;
            histogram.selected = histogram.closestVertex(mouseX,mouseY);
            histogram.selected.selected=true;
        }
    }
    @Override
    public void mouseDragged()
    {
        if (menu.algorithmMode== Menu.algorithm.NONE&&histogram.selected != null)
            histogram.moveSelected(mouseX-pmouseX,mouseY-pmouseY);
    }
    @Override
    public void keyReleased()
    {
        if (menu.algorithmMode== Menu.algorithm.NONE&&(keyCode == DELETE || keyCode == BACKSPACE))
        {
            histogram.deleteSelectedPoint();
        }
    }
}
