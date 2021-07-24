import java.util.ArrayList;
public class TableGroup {
    public int id;
    private ArrayList<Table> tables;
    private ArrayList<Server> servers;

    public TableGroup(int id) {
        this.id = id;
        this.tables = new ArrayList<Table>();
        this.servers = new ArrayList<Server>();
    }

    private void addTables(Table... tables) {
        for (Table table : tables) {
            this.tables.add(table);
        }
    }
    private void addServers(Server... servers) {
        for (Server server : servers) {
            this.servers.add(server);
        }
    }

}