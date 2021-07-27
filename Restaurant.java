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

    // Add a table group to the restaurant
    public void generateTableGroups() {
        System.out.println("Generating table groups...");
        // need a copy i can modify
        ArrayList<Table> tablesCopy = (ArrayList<Table>) this.tables.clone();
        int tableAmount = tables.size();
        int serverAmount = servers.size();
        int tableGroupAmount = (int)((tableAmount / serverAmount)+0.5);
        // just so we can make sure every server has a table
        for (int i=0; i<serverAmount; i++) {
            ArrayList<Table> temp = new ArrayList<Table>();
            // makes sure that we pick an appropiate ammount of tables per group
            for (int j=0; j<tableGroupAmount; j++) {
                // this makes sure that every table gets assinged to a group
                // without this the if there are an odd number of tables one won't get assigned
                if(i == serverAmount-1 && j == 0) {
                    for (int k=0; k<tablesCopy.size(); k++) {
                        temp.add(tablesCopy.get(k));
                        // if you don't remove the index they will be added twice
                        tablesCopy.remove(k);
                    }
                } else {
                    // picks random tables from the list for each group
                    int index = (int)Math.floor(Math.random() * tablesCopy.size());
                    temp.add(tablesCopy.get(index));
                    // if you don't remove the index they will be added twice
                    tablesCopy.remove(index);
                }
            }
            // finally creates the table group and adds it to the Restaurants list
            Server currServer = this.servers.get(i);
            this.tableGroups.add(new TableGroup(currServer,temp));
        }
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
        Table table3 = new Table(2,4);
        Table table4 = new Table(3,2);
        Table table5 = new Table(4,6);
        // Table table6 = new Table(5,4);

        Server server1 = new Server("Daniel", 100);
        Server server2 = new Server("John", 200);

        Table[] tables = {table1, table2, table3, table4, table5};
        Server[] servers = {server1, server2};

        Restaurant restaurant1 = new Restaurant(tables, servers);
        restaurant1.generateTableGroups();
        System.out.println(restaurant1.tableGroups);
    }
}