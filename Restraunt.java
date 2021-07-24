import java.util.ArrayList;

public class Restraunt {
    public int numTables;
    private ArrayList<Table> tables;
    private ArrayList<Server> servers;

    Restraunt(int numTables) {
        this.numTables = numTables;
        this.tables = new ArrayList<Table>();
        this.servers = new ArrayList<Server>();
    }

    public void addTables(Table... tables) {
        for (Table table : tables) {
            this.tables.add(table);
        }
    }
    public void addServers(Server... servers) {
        for (Server server : servers) {
            this.servers.add(server);
        }
    }

    public static void main(String[] args) {

    }
}