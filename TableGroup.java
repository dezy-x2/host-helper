import java.util.ArrayList;
public class TableGroup {
    private ArrayList<Table> tables;
    private Server server;

    public TableGroup(Server server, ArrayList<Table> tables) {
        this.tables = tables;
        this.server = server;
    }

    public boolean isFull() {
        for (Table table : tables) {
            if (table.isAvaliable()) {
                return false;
            }
        }
        return true;
    }

    public void fillTable(boolean reserved) {
        if(reserved) {
            System.out.println("Reserving table...");
        } else {
            System.out.println("Filling table...");
        }
    }

    @Override
    public String toString() {
        return "TableGroup{" +
                "tables=" + tables +
                ", server=" + server +
                '}';
    }

}