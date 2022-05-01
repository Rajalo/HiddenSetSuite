import java.util.List;

public class HiddenGuardSetStep extends AlgorithmStep {
    public static int EPSILON = -2;
    public HiddenGuardSetStep(String stepName) {
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
                    controller.steps.add(new HiddenGuardSetStep("null"));
                }
                return;
            case "initialize":
                controller.vars.put("vertexIndex",2);
                controller.vars.put("pyramidBottom",Main.histogram.vertices.get(0).getY());
                controller.steps.add(new HiddenGuardSetStep("loop"));
                break;
            case "loop":
                int vertexIndex = controller.vars.get("vertexIndex");
                int pyramidBottom = controller.vars.get("pyramidBottom");
                List<Vertex> vertexList = Main.histogram.vertices;
                if (!controller.vertexStack.isEmpty())
                {
                    controller.vertexStack.pop();
                }
                if (vertexIndex >= vertexList.size()-1) {
                    controller.steps.add(new HiddenGuardSetStep("null"));
                    return;
                }
                Vertex cursor = new Vertex(
                        (vertexList.get(vertexIndex).getX() + vertexList.get(vertexIndex+1).getX())/2,
                        pyramidBottom+EPSILON
                );
                controller.vertexStack.push(cursor);
                boolean rightConvex = (vertexIndex==2)||vertexList.get(vertexIndex).getY() < vertexList.get(vertexIndex-1).getY();
                boolean leftConvex = (vertexIndex>=vertexList.size()-2)||vertexList.get(vertexIndex+1).getY() < vertexList.get(vertexIndex+2).getY();
                if (rightConvex && leftConvex)
                {
                    controller.vertexSet.add(cursor);
                }
                if (!(rightConvex||leftConvex))
                {
                    controller.vars.put("pyramidBottom",vertexList.get(vertexIndex).getY());
                }
                controller.steps.add(new HiddenGuardSetStep("loop"));
                controller.vars.put("vertexIndex",vertexIndex+2);
                break;
        }
    }
}
