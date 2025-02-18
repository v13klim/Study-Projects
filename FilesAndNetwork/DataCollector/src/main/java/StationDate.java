public class StationDate {
    private final String name;
    private final String date;

    public StationDate (String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "StationsDates{" + "name='" + name + '\'' + ", date='" + date + '\'' + '}';
    }
}
