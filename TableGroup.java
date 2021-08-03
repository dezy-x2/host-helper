import java.util.ArrayList;

public class TableGroup {
    // list of the tables which is a subset of the tables in the restaurant
    private ArrayList<Table> tables;
    // holds one server that serves all of these tables
    private Server server;

    public TableGroup(Server server, ArrayList<Table> tables) {
        this.tables = tables;
        this.server = server;
        // this sets each tables server to the server that serves all tables in this
        // group
        for (Table table : this.tables) {
            table.setServer(this.server);
        }
    }

    // finds out if all of the tables in this group are full or not
    public boolean isFull() {
        for (Table table : tables) {
            if (table.isAvaliable()) {
                return false;
            }
        }
        return true;
    }

    // finds the number of empty tables in this group
    public int emptyCount() {
        int count = 0;
        for (Table table : tables) {
            if (table.isAvaliable()) {
                count++;
            }
        }
        return count;
    }

    // i don't think this is used anywhere lol
    public void fillTable(boolean reserved) {
        if (reserved) {
            System.out.println("Reserving table...");
        } else {
            System.out.println("Filling table...");
        }
    }

    // finds the first empty suitable table in this group
    public Table bestTable(int groupCount) {
        Table bestTable = null;
        for (Table table : tables) {
            if (table.isAvaliable() && table.numSeats >= groupCount) {
                bestTable = table;
                break;
            }
        }
        return bestTable;
    }

    // checks that there is a table in this group that can hold the given group size
    public boolean hasProperTable(int groupCount) {
        for (Table table : tables) {
            if (!(table.isAvaliable() || table.numSeats >= groupCount)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "TableGroup{\n" + "\ntables=" + tables + ", \nserver=" + server + "\n}";
    }

}