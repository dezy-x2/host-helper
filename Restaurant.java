import java.util.ArrayList;

public class Restaurant {
    private ArrayList<TableGroup> tableGroups;
    private ArrayList<Table> tables;
    private ArrayList<Server> servers;

    Restaurant(Table[] tables, Server[] servers) {
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

    public void seatCustomers(boolean reservation) {
        if (!reservation) {
            System.out.println("Seatting customers...");
        } else {
            System.out.println("Reserving tables...");
        }
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
        return "Restraunt{\n" +
                "numTables=" + tables.size() +
                ", \ntableGroups=" + tableGroups +
                ", \ntables=" + tables +
                ", \nservers=" + servers +
                "\n}";
    }

    public static void main(String[] args) {
        Table table1 = new Table(0,4);
        Table table2 = new Table(1,2);
        Server server1 = new Server("Daniel", 100);
        Server server2 = new Server("John", 200);
        Table[] tables = {table1, table2};
        Server[] servers = {server1, server2};
        Restaurant restaurant1 = new Restaurant(tables, servers);
        System.out.println(restaurant1);
    }
}