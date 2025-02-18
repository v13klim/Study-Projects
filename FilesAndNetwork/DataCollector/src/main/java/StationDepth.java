public class StationDepth {
    private String name;
    private String depth;

    public StationDepth(String name, String depth) {
        this.name = name;
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "StationDepth{" +
                "name='" + name + '\'' +
                ", depth='" + depth + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }
}
