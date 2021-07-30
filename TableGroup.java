import java.util.ArrayList;
public class TableGroup {
    private ArrayList<Table> tables;
    private Server server;

    public TableGroup(Server server, ArrayList<Table> tables) {
        this.tables = tables;
        this.server = server;
        for (Table table : this.tables) {
            table.setServer(this.server);
        }
    }

    public boolean isFull() {
        for (Table table : tables) {
            if (table.isAvaliable()) {
                return false;
            }
        }
        return true;
    }

    public int emptyCount() {
        int count = 0;
        for (Table table : tables) {
            if (table.isAvaliable()) {
                count++;
            }
        }
        return count;
    }

    public void fillTable(boolean reserved) {
        if(reserved) {
            System.out.println("Reserving table...");
        } else {
            System.out.println("Filling table...");
        }
    }

    public Table bestTable() {
        Table bestTable = null;
        for (Table table : tables) {
            if (table.isAvaliable()) {
                bestTable = table;
                break;
            }
        }
        return bestTable;
    }

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
        return "TableGroup{\n" +
                "\ntables=" + tables +
                ", \nserver=" + server +
                "\n}";
    }

}