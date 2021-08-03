import java.util.ArrayList;

public class WaitList {
    // list of the patrons waiting to be served
    private ArrayList<Patron> waitList;

    public WaitList() {
        waitList = new ArrayList<Patron>();
    }

    public void add(String name, String phoneNumber , int groupCount) {
        // create a new patron object and add it to the wait list
        Patron patron = new Patron(name, phoneNumber, groupCount);
        this.waitList.add(patron);
        System.out.println(patron.name + " has been added to the wait list.");
    }

    public boolean eligiblePatron(Table table) {
        // find out whether or not a patron can be sat at the passed in table
        for (Patron patron : waitList) {
            if (table.numSeats >= patron.groupCount) {
                return true;
            }
        }
        return false;
    }

    public Table eligibleTable(int groupCount, ArrayList<Table> tables) {
        // find the table with the shortest wait time
        ArrayList<Table> tablesWithSeats = new ArrayList<Table>();
        Table bestTime = null;
        long max = Long.MAX_VALUE;
        for (Table table : tables) {
            if (table.numSeats >= groupCount) {
                tablesWithSeats.add(table);
            }
        }

        for (Table table : tablesWithSeats) {
            if (table.seatingTimePassed() < max) {
                max = table.seatingTimePassed();
                bestTime = table;
            }
        }
        return bestTime;
    }

    // actually seat the patron
    public Patron seatFromWaitList(Table table) {
        for (int i=0; i<waitList.size(); i++) {
            if (table.numSeats >= waitList.get(i).groupCount) {
                Patron patron = waitList.get(i);
                // need to remove the patron from the waitlist
                waitList.remove(i);
                return patron;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "WaitList{";
        for (Patron patron : waitList) {
            str += patron.toString() + " ";
        }
        str += "}";
        return str;
    }
}