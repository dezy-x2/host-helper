import java.util.*;

public class Restaurant {
    // table groups hold smaller ArrayLists of tables and one server who serves
    // those tables
    private ArrayList<TableGroup> tableGroups;
    // holds all of the restaurants tables
    private ArrayList<Table> tables;
    // holds all of the servers
    private ArrayList<Server> servers;
    // keeps track of the customers who have not been sat yet
    private WaitList waitList;

    Restaurant(Table[] tables, Server[] servers) {
        this.tableGroups = new ArrayList<TableGroup>();
        this.tables = new ArrayList<Table>();
        this.servers = new ArrayList<Server>();
        this.waitList = new WaitList();
        // loops over all of the tables and servers and adds them to the appropriate
        // lists
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
        // this prevents an unnecessary warning from showing up
        @SuppressWarnings("unchecked")
        // need a copy i can modify
        ArrayList<Table> tablesCopy = (ArrayList<Table>) this.tables.clone();
        int tableAmount = tables.size();
        int serverAmount = servers.size();
        int tableGroupAmount = (int) ((tableAmount / serverAmount) + 0.5);
        // just so we can make sure every server has a table
        for (int i = 0; i < serverAmount; i++) {
            ArrayList<Table> temp = new ArrayList<Table>();
            // makes sure that we pick an appropiate ammount of tables per group
            for (int j = 0; j < tableGroupAmount; j++) {
                // this makes sure that every table gets assinged to a group
                // without this the if there are an odd number of tables one won't get assigned
                if (i == serverAmount - 1 && j == 0) {
                    for (int k = 0; k < tablesCopy.size(); k++) {
                        temp.add(tablesCopy.get(k));
                        // if you don't remove the index they will be added twice
                        tablesCopy.remove(k);
                    }
                } else {
                    // picks random tables from the list for each group
                    int index = (int) Math.floor(Math.random() * tablesCopy.size());
                    // a bug arrises here with even amounts of tables
                    try {
                        temp.add(tablesCopy.get(index));
                        // if you don't remove the index they will be added twice
                        tablesCopy.remove(index);
                    } catch (IndexOutOfBoundsException e) {
                    }

                }
            }
            // finally creates the table group and adds it to the Restaurants list
            Server currServer = this.servers.get(i);
            this.tableGroups.add(new TableGroup(currServer, temp, i));
        }
    }

    // finds the maximum number of empty tables in a table group
    private int maxEmptyTables(ArrayList<TableGroup> tableGroups) {
        int max = Integer.MAX_VALUE;
        for (TableGroup tableGroup : tableGroups) {
            int emptyTables = (int) tableGroup.getEmptyRatio();
            System.out.println(emptyTables);
            if (emptyTables < max) {
                max = emptyTables;
            }
        }
        return max;
    }

    private TableGroup bestTableGroup(int groupCount) {
        ArrayList<TableGroup> availableGroups = new ArrayList<TableGroup>();
        // loops over all of the table groups and adds them to the list if they are not
        // full
        for (TableGroup tableGroup : this.tableGroups) {
            if (!tableGroup.isFull()) {
                availableGroups.add(tableGroup);
            }
        }
        int maxEmptyTables = this.maxEmptyTables(availableGroups);
        for (int i = 0; i < availableGroups.size(); i++) {
            TableGroup tableGroup = availableGroups.get(i);
            // removes table groups that do not have suitable tables for the customer
            if (tableGroup.emptyCount() < maxEmptyTables && tableGroup.hasProperTable(groupCount)) {
                availableGroups.remove(i);
                i--;
            }
        }
        // generates a random index to choose from
        int index = (int) Math.floor(Math.random() * availableGroups.size());
        try {
            return availableGroups.get(index);
        } catch (IndexOutOfBoundsException e) {
            // if there are no avaliable table groups (aka the restaurant is full) it
            // returns null
            // the null will be handled in the calling method
            return null;
        }
    }

    public void seatCustomers(boolean reservation, int groupCount) {
        System.out.println(reservation ? "Reserving tables..." : "Seatting customers...");
        TableGroup tableGroup = this.bestTableGroup(groupCount);
        // if there are no tables left bestTableGroup returns null
        if (tableGroup != null) {
            Table table = tableGroup.bestTable(groupCount);
            // if there are no tables left bestTable returns null
            if (table == null) {
                System.out.println("Not enough seats");
            } else {
                table.fillTable(reservation);
                System.out.println(reservation ? "Reserved table " + table.id : "Seating at table " + table.id + "...");
            }
        } else {
            System.out.println("No tables available. Press 'a' to add patron to waitlist.");
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
        return "Restraunt{\n" + "numTables=" + tables.size() + ", \ntableGroups=" + tableGroups + ", \ntables=" + tables
                + ", \nservers=" + servers + "\n}";
    }

    // prints each table id and whether it is available or not
    private String printTables() {
        String result = "{";
        for (Table table : tables) {
            String temp = table.isAvaliable() ? "available" : "unavailable";
            result += table.id + ": " + temp + ", ";
        }
        result += "}";
        return result;
    }

    private String printTableGroups() {
        String result = "";
        for (TableGroup tableGroup : tableGroups) {
            result += tableGroup.printTables() + ", \n";
        }
        return result;
    }

    // this method trys to seat patrons from the waitlist when a table opens up
    private void tryToSeat(int id) {
        for (Table table : tables) {
            if (table.id == id) {
                // first it checks if there is a patron that can be sat at the table
                boolean eligible = waitList.eligiblePatron(table);
                if (eligible) {
                    // then we seat the patron and print a message
                    Patron patron = waitList.seatFromWaitList(table);
                    table.fillTable(false);
                    System.out.println("Seated patron " + patron.name + " at table " + table.id);
                }
            }
        }
    }

    private long getWaitTime(int groupCount) {
        Table bestTable = waitList.eligibleTable(groupCount, this.tables);
        if (bestTable != null) {
            // seatingTimePassed() returns a time in miliseconds so we convert it to seconds
            // then minutes
            // then subtract that from 60 to get aprox how long the wait should be
            return 60 - ((bestTable.seatingTimePassed() / 1000) / 60);
        }
        return 0;
    }

    private void handleTableSwitch(int id, int tableGroup1Id, int tableGroup2Id) {
        Table table = null;
        TableGroup tableGroup1 = null;
        TableGroup tableGroup2 = null;
        for (Table tempTable : this.tables) {
            if (tempTable.id == id) {
                table = tempTable;
            }
        }
        for (TableGroup tempTableGroup : this.tableGroups) {
            if (tempTableGroup.id == tableGroup1Id) {
                tableGroup1 = tempTableGroup;
            } else if (tempTableGroup.id == tableGroup2Id) {
                tableGroup2 = tempTableGroup;
            }
        }
        tableGroup1.removeTable(table);
        tableGroup2.addTable(table);
    }

    // this method creates a CLI interface for the restaurant
    public void host() {
        // scanner is how we read the input from the user
        Scanner sc = new Scanner(System.in);
        this.generateTableGroups();
        while (true) {
            System.out.print("\nWhat would you like to do?(press 'h' for help menu) \n>");
            char res = sc.next().charAt(0);
            switch (res) {
                case 's':
                    System.out.print("How many in the group?\n>");
                    int groupCount = sc.nextInt();
                    this.seatCustomers(false, groupCount);
                    break;
                case 'r':
                    System.out.print("How many in the group?\n>");
                    groupCount = sc.nextInt();
                    this.seatCustomers(true, groupCount);
                    break;
                case 'h':
                    System.out.println(
                            "Options:\n's': Seat a customer right away\n'r': Reserve a seat for a customer\n'e': Empty a seat\n'X': End shift\n'E': Empty all tables\n'p': Print list of tables and their status\n'a': Add patron to waitlist\n'g': Print table groups\n'c': Change tables in table groups");
                    break;
                case 'e':
                    System.out.print("Enter ID of table to empty\n>");
                    int id = sc.nextInt();
                    for (Table table : this.tables) {
                        if (table.id == id) {
                            table.empty();
                            System.out.println("Table " + table.id + " is empty");
                        }
                    }
                    this.tryToSeat(id);
                    break;
                case 'X':
                    System.out.print("Are you sure you want to exit?(y/n)\n>");
                    char exit = sc.next().charAt(0);
                    if (exit == 'y') {
                        System.out.println("Goodbye :)");
                        sc.close();
                        return;
                    }
                    break;
                case 'E':
                    System.out.print("Are you sure you want to empty all tables?(y/n)\n>");
                    char empty = sc.next().charAt(0);
                    if (empty == 'y') {
                        for (Table table : this.tables) {
                            table.empty();
                        }
                        System.out.println("All tables are empty.");
                    }
                    break;
                case 'p':
                    System.out.println(this.printTables());
                    System.out.println("\n");
                    System.out.println(this.waitList);
                    break;
                case 'a':
                    System.out.print("Enter patron group size\n>");
                    int size = sc.nextInt();
                    long waitTime = this.getWaitTime(size);
                    System.out.println("Wait time: " + waitTime + " minutes");
                    System.out.print("Continue?(y/n)\n>");
                    char continue1 = sc.next().charAt(0);
                    if (continue1 != 'y') {
                        break;
                    }
                    System.out.print("Enter patron name\n>");
                    String name = sc.next();
                    System.out.print("Enter patron phone number\n>");
                    String phoneNumber = sc.next();
                    this.waitList.add(name, phoneNumber, size);
                    break;
                case 'g':
                    System.out.println(this.printTableGroups());
                    break;
                case 'c':
                    System.out.print("Enter table to be switched id\n>");
                    int tableId = sc.nextInt();
                    System.out.print("Enter current table group id\n>");
                    int currTableGroupId = sc.nextInt();
                    System.out.print("Enter new table group id\n>");
                    int newTableGroupId = sc.nextInt();
                    this.handleTableSwitch(tableId, currTableGroupId, newTableGroupId);
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Table table1 = new Table(0, 4);
        Table table2 = new Table(1, 2);
        Table table3 = new Table(2, 4);
        Table table4 = new Table(3, 2);
        Table table5 = new Table(4, 6);
        // Table table6 = new Table(5,4);

        Server server1 = new Server("Daniel", 100);
        Server server2 = new Server("John", 200);

        Table[] tables = { table1, table2, table3, table4, table5 };
        Server[] servers = { server1, server2 };

        Restaurant restaurant1 = new Restaurant(tables, servers);
        restaurant1.host();
    }
}