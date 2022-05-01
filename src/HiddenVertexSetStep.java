import java.util.List;

public class HiddenVertexSetStep extends AlgorithmStep{
    public HiddenVertexSetStep(String stepName) {
        super(stepName);
    }
    @Override
    public void run(AlgorithmController controller)
    {
        switch (stepName)
        {
            case "null":
                if (!controller.vertexStack.isEmpty())
                {
                    controller.vertexStack.pop();
                    controller.steps.add(new HiddenVertexSetStep("null"));
                }
                return;
            case "initialize":
                controller.vars.put("vertexIndex",2);
                controller.steps.add(new HiddenVertexSetStep("loop"));
                break;
            case "loop":
                int vertexIndex = controller.vars.get("vertexIndex");
                if (vertexIndex >= Main.histogram.vertices.size())
                {
                    controller.steps.add(new HiddenSetStep("null"));
                    return;
                }
                int stackY;
                if (controller.vertexStack.isEmpty())
                {
                    stackY = 1000;
                }
                else
                {
                    stackY = controller.vertexStack.peek().getY();
                }
                int currY = Main.histogram.vertices.get(vertexIndex).getY();
                if (stackY == currY)
                {
                    controller.vars.put("vertexIndex",vertexIndex+2);
                }
                else if (stackY > currY)
                {
                    Vertex newVertex = null;
                    List<Vertex> vertexList = Main.histogram.vertices;
                    if (vertexList.get(vertexIndex).getY() < vertexList.get(vertexIndex-1).getY())
                        newVertex = new Vertex(
                                vertexList.get(vertexIndex).getX(),
                            currY
                    );
                    else if (vertexList.get(vertexIndex+1).getY() < vertexList.get((vertexIndex+2)%vertexList.size()).getY())
                        newVertex = new Vertex(
                                vertexList.get(vertexIndex+1).getX(),
                                currY
                        );
                    if (newVertex != null) {
                        controller.vertexStack.push(newVertex);
                        controller.vertexSet.add(newVertex);
                    }
                    controller.vars.put("vertexIndex",vertexIndex+2);
                }
                else
                {
                    controller.vertexStack.pop();
                }
                controller.steps.add(new HiddenVertexSetStep("loop"));
                break;
        }
    }
}
