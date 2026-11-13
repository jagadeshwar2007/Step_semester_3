abstract class GardenTool {
    public GardenTool() {
    }

    public abstract String use();

    protected String baseUse() {
        return "Using the tool in the garden";
    }
}

class CuttingTool extends GardenTool {
    public CuttingTool() {
        super();
    }

    public String use() {
        return super.baseUse() + ", blade sharpened first";
    }
}

class Pruner extends CuttingTool {
    public Pruner() {
        super();
    }

    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}

public class Week7AssignmentQuestion3 {
    public static void main(String[] args) {
        CuttingTool c = new CuttingTool();
        Pruner p = new Pruner();

        System.out.println(c.use());
        System.out.println(p.use());
    }
}
