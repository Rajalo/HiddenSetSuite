public class HiddenSetStep extends AlgorithmStep {

    public HiddenSetStep(String stepName) {
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
                    controller.steps.add(new HiddenSetStep("null"));
                }
                return;
            case "initialize":
                controller.vars.put("vertexIndex",2);
                controller.steps.add(new HiddenSetStep("loop"));
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
                    Vertex newVertex = new Vertex(
                            (Main.histogram.vertices.get(vertexIndex).getX()+Main.histogram.vertices.get(vertexIndex+1).getX())/2,
                            currY
                    );
                    controller.vertexStack.push(newVertex);
                    controller.vertexSet.add(newVertex);
                    controller.vars.put("vertexIndex",vertexIndex+2);
                }
                else
                {
                    controller.vertexStack.pop();
                }
                controller.steps.add(new HiddenSetStep("loop"));
                break;
        }
    }
}
