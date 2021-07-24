import java.util.ArrayList;

public class Restraunt {
    private ArrayList<TableGroup> tableGroups;
    private ArrayList<Table> tables;
    private ArrayList<Server> servers;

    Restraunt(Table[] tables, Server[] servers) {
        this.tableGroups = new ArrayList<TableGroup>();
        this.tables = new ArrayList<Table>();
        this.servers = new ArrayList<Server>();
        for (Table table : tables) {
            this.tables.add(table);
        }
        for (Server server : servers) {
            this.servers.add(server);
        }
    }

    public void generateTableGroups() {
        System.out.println("Generating table groups...");
    }

    public void seatCustomers() {
        System.out.println("Seating customers...");
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

    @Override
    public String toString() {
        return "Restraunt{" +
                "numTables=" + tables.size() +
                ", tableGroups=" + tableGroups +
                ", tables=" + tables +
                ", servers=" + servers +
                '}';
    }

    public static void main(String[] args) {

    }
}