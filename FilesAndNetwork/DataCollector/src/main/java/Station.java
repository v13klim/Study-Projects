public class Station {
    private String name;
    private String line;
    private Boolean hasConnection;

    public Station(String name, String line, Boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.hasConnection = hasConnection;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", line='" + line + '\'' +
                ", hasConnection=" + hasConnection +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Boolean getHasConnection() {
        return hasConnection;
    }

    public void setHasConnection(Boolean hasConnection) {
        this.hasConnection = hasConnection;
    }
}