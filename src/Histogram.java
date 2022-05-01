import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Collections;

public class Histogram {
    public ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    public Vertex selected;
    public Histogram()
    {
        Vertex bottomLeft = new Vertex(100,550);
        Vertex bottomRight = new Vertex(700,550);
        Vertex topRight = new Vertex(700, 200);
        Vertex topLeft = new Vertex(100,200);
        Collections.addAll(vertices, new Vertex[]{bottomLeft,bottomRight,topRight,topLeft});
        selected = null;
    }

    /**
     * Returns the closest vertex to the specified point
     * @param x x-coord
     * @param y y-coord
     * @return the index (nonnegative integer)
     */
    public Vertex closestVertex(int x, int y)
    {
        Vertex answer = vertices.get(2); //topRight
        for (int i = 3; i < vertices.size(); i++)
        {
            if (vertices.get(i).distanceSquare(x,y)<(answer).distanceSquare(x,y))
            {
                answer = vertices.get(i);
            }
        }
        return answer;
    }

    /**
     * Returns the index of the vertex preceding the point (for insertion)
     * @param x
     * @param y
     * @return
     */
    private int previousVertex(int x, int y)
    {
        int i = 2; //topRight
        for (; i < vertices.size()-1; i+= 2) //restricted to starts of horizontal edges
        {
            if (vertices.get(i).getX()-x < 0)
            {
                return i-2;
            }
        }
        return i-2;
    }

    /**
     * Inserts a vertex into the histogram polygon
     * @param x
     * @param y
     */
    public void insertVertex(int x, int y)
    {
        if (!clickInBounds(x,y))
        {
            return;
        }
        int insertIndex = previousVertex(x,y);
        int previousEdgeHeight = vertices.get(insertIndex).getY();
        int nextEdgeHeight = vertices.get(insertIndex+1).getY();
        int newEdgeEndX = (vertices.get(insertIndex+1).getX()+x)/2;
        Vertex previousEdgeVertex = new Vertex(x, previousEdgeHeight);
        Vertex specified = new Vertex(x,y);
        Vertex nextImmediate = new Vertex(newEdgeEndX, y);
        Vertex reconnection = new Vertex(newEdgeEndX, nextEdgeHeight);
        vertices.add(insertIndex+1, reconnection);
        vertices.add(insertIndex+1, nextImmediate);
        vertices.add(insertIndex+1, specified);
        vertices.add(insertIndex+1, previousEdgeVertex);
    }

    /**
     * Whether a point is "in Bounds", or would create overhangs
     * @param x
     * @param y
     * @return
     */
    public boolean clickInBounds(int x,int y)
    {
        if (vertices.get(0).getX()<x)
        {
            if (vertices.get(1).getX() > x)
            {
                return vertices.get(0).getY()>y;
            }
        }
        return false;
    }
    /**
     * draw the histogram
     * @param g
     */
    public void draw(PApplet g)
    {
        int strokeColor = g.color(10,10,10);
        int fillColor = g.color(179, 59, 95);
        g.stroke(strokeColor);
        g.fill(fillColor);
        g.beginShape();
        for (Vertex vertex : vertices)
        {
            g.vertex(vertex.getX(),vertex.getY());
        }
        g.vertex(vertices.get(0).getX(),vertices.get(0).getY());
        g.endShape();
        for (Vertex vertex : vertices)
        {
            vertex.draw(g);
        }
    }
    /**
     * moves the selected vertex by the specified margin
     * @param xMove how much to move in the x
     * @param yMove how much to move in the y
     */
    public void moveSelected(int xMove, int yMove) {
        int i = 0;
        for (Vertex vertex : vertices)
        {
            if (vertex.selected)
                break;
            i++;
        }
        if (vertices.get(i).getY()+yMove > vertices.get(0).getY()-5)
            return;
        if (i%2 ==0)
        {
            if (i > vertices.size()
                    ||(vertices.get(i).getX()+xMove > vertices.get(i-2).getX()-5&&i>2)
                    ||vertices.get(i).getX()+xMove < vertices.get(i+1).getX()+5
            )
                return;
            vertices.get(i-1).shiftX(xMove);
            vertices.get(i+1).shiftY(yMove);
        }
        else
        {
            if (i > vertices.size()
                    ||vertices.get(i).getX()+xMove > vertices.get(i-1).getX()-5
                    ||(i<vertices.size()-1&&vertices.get(i).getX()+xMove < vertices.get(i+2).getX()+5)
            )
                return;
            if (i!= vertices.size()-1)
            {
                vertices.get(i+1).shiftX(xMove);
            }
            else
            {
                vertices.get(0).shiftX(xMove);
            }
            vertices.get(i-1).shiftY(yMove);
        }
        vertices.get(i).shiftX(xMove);
        vertices.get(i).shiftY(yMove);
    }

    /**
     * Deletes the selected point and the horizontal edge it forms
     */
    public void deleteSelectedPoint()
    {
        int i = 0;
        for (Vertex vertex : vertices)
        {
            if (vertex.selected)
                break;
            i++;
        }
        if (i == 2 || i >= vertices.size()-2)
            return;
        if (i%2 == 1)
        {
            i -= 1;
        }
        int comparison = vertices.get(i+2).getY()-vertices.get(i-1).getY();
        if (comparison < 0)
        {
            vertices.get(i-1).setX(vertices.get(i+1).getX());
        }
        else if (comparison > 0)
        {
            vertices.get(i+2).setX(vertices.get(i).getX());
        }
        vertices.remove(i);
        vertices.remove(i);
        if (comparison == 0)
        {
            vertices.remove(i-1);
            vertices.remove(i-1);
        }
    }
    public void unselect()
    {
        for (Vertex v : vertices)
        {
            v.selected = false;
        }
    }
}
