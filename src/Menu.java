import processing.core.PApplet;
import processing.core.PConstants;

public class Menu {
    public enum algorithm {
        NONE,
        HIDDENSET,
        HIDDENVERTEXSET,
        HIDDENGUARDSET,
        INFO
    };
    public algorithm algorithmMode;
    public String[] buttonNames = {
            "Hidden Set",
            "Hidden Vertex Set",
            "r-Hidden Guard Set"
    };
    public int[] buttonColors = new int[3];
    public Menu()
    {
        buttonColors[0] = Main.applet.color(200,200,100);
        buttonColors[1] = Main.applet.color(100,200,200);
        buttonColors[2] = Main.applet.color(200,100,200);
        algorithmMode = algorithm.NONE;
    }
    /**
     * Draws a button at the bottom
     * @param g
     * @param name
     * @param color
     * @param startX
     */
    public void drawButton(PApplet g, String name, int color, int startX)
    {
        g.fill(color);
        g.rect(startX,600, 200, 80);
        g.fill(0);
        g.textAlign(PConstants.CENTER);
        g.textSize(26);
        g.text(name, startX+1,605, 200, 80);
    }
    public void draw(PApplet g)
    {
        g.fill(g.color(150));
        g.rect(0,580,797,198);
        for (int i = 0; i < 3; i++)
        {
            drawButton(g, buttonNames[i],buttonColors[i],(260*i)+40);
        }
    }
    /**
     * Checks if a button has been pressed
     * @param x
     * @param y
     * @return
     */
    public boolean checkButtonPress(int x, int y)
    {
        if (y < 600 || y > 680)
            return false;
        int i;
        for ( i = 0; i < 3; i++)
        {
            if (x>=(260*i)+40 && x <= (260*i)+240)
            {
                break;
            }
        }
        if (i >= 3)
        {
            return false;
        }
        algorithmMode = algorithm.values()[i+1];
        for (int j = 0; j < 3; j++)
        {
            if (j != i)
                buttonColors[j] = 60;
        }
        return true;
    }
    public void backToNormal()
    {
        buttonColors[0] = Main.applet.color(200,200,100);
        buttonColors[1] = Main.applet.color(100,200,200);
        buttonColors[2] = Main.applet.color(200,100,200);
        algorithmMode = algorithm.NONE;
        Main.algorithmController = null;
    }
}
