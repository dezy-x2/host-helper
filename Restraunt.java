import java.util.ArrayList;

public class Restraunt {
    public int numTables;
    private ArrayList<TableGroup> tableGroups;
    private ArrayList<Table> tables;
    private ArrayList<Server> servers;

    Restraunt(int numTables) {
        this.numTables = numTables;
        this.tableGroups = new ArrayList<TableGroup>();
        this.tables = new ArrayList<Table>();
        this.servers = new ArrayList<Server>();
    }

    private void generateTableGroups() {
        System.out.println("Generating table groups...");
    }

    public void addTables(Table... tables) {
        for (Table table : tables) {
            this.tables.add(table);
        }
    }

    public void addServer(Server... servers) {
        for (Server server : servers) {
            this.servers.add(server);
        }
    }

    public static void main(String[] args) {

    }
}