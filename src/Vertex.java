import processing.core.PApplet;

public class Vertex {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Shift the x-coordinate by a certain amount
     * @param x the amount
     */
    public void shiftX(int x) {
        this.x += x;
    }

    /**
     * Shift the y-coordinate by a certain amount
     * @param y the amount
     */
    public void shiftY(int y) {
        this.y += y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int x;
    private int y;
    boolean selected;
    public Vertex(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public void draw(PApplet g)
    {
        int strokeColor = g.color(10,10,10);
        int fillColor = g.color(15,15,15);
        if (selected)
        {
            fillColor = g.color(15,200,15);
        }
        g.stroke(strokeColor);
        g.fill(fillColor);
        g.circle(x,y,10);
    }

    /**
     * Returns the square of the distance between this vertex and the specified coords
     * @param x
     * @param y
     * @return
     */
    public int distanceSquare(int x, int y) {
        return (this.x-x)*(this.x-x)+(this.y-y)*(this.y-y);
    }

    /**
     * Returns the square of the distance between this vertex and the specified vertex
     * @param other
     * @return
     */
    public int distanceSquare(Vertex other)
    {
        return (this.x-other.x)*(this.x-other.x)+(this.y-other.y)*(this.y-other.y);
    }
}
