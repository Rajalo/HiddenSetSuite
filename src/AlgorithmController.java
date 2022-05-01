import processing.core.PApplet;

import java.util.*;

public class AlgorithmController {
    Queue<AlgorithmStep> steps;
    Map<String,Integer> vars;
    Stack<Vertex> vertexStack;
    Set<Vertex> vertexSet;
    Menu.algorithm version;
    int time;
    public AlgorithmController(Menu.algorithm version)
    {
        this.version = version;
        time = 0;
        vars = new HashMap<>();
        vertexStack = new Stack<>();
        vertexSet = new HashSet<>();
        steps = new ArrayDeque<>();
        switch (version)
        {
            case HIDDENSET:
                steps.add(new HiddenSetStep("initialize"));
                break;
            case HIDDENVERTEXSET:
                steps.add(new HiddenVertexSetStep("initialize"));
                break;
            case HIDDENGUARDSET:
                steps.add(new HiddenGuardSetStep("initialize"));
                break;
        }
    }
    public void draw(PApplet g)
    {
        time++;
        if (time %20 == 0 && !steps.isEmpty())
        {
            steps.poll().run(this);
        }
        int setColor = g.color(200,200,250);
        int stackColor = g.color(200,250,200);
        g.fill(setColor);
        for (Vertex v : vertexSet)
        {
            g.circle(v.getX(),v.getY(),15);
        }
        g.fill(stackColor);
        for (Vertex v: vertexStack)
        {
            g.circle(v.getX(), v.getY(), 15);
        }
    }
}
