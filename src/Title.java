import processing.core.PApplet;
import processing.core.PFont;

public class Title {
    String title;
    String instructions;
    public Title()
    {
        title = "Hiding in Histograms";
        instructions = "Ctrl-Click to 'carve out' points, select and drag points to move them";
    }
    public void draw(PApplet g)
    {
        int fillColor = g.color(150,150,250);
        g.fill(fillColor);
        g.rect(0,2,797,150);
        fillColor = g.color(50,10,10);
        g.fill(fillColor);
        g.textSize(50);
        g.text(title, 10,10,790,140);
        g.textSize(20);
        g.text(instructions, 10,90,790,140);
    }
}
