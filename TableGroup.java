import java.util.ArrayList;
public class TableGroup {
    public int id;
    private ArrayList<Table> tables;
    private Server server;

    public TableGroup(int id, Server server) {
        this.id = id;
        this.tables = new ArrayList<Table>();
        this.server = server;
    }

    public void addTables(Table... tables) {
        for (Table table : tables) {
            this.tables.add(table);
        }
    }

}