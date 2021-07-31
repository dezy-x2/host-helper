import java.util.ArrayList;

public class WaitList {
    private ArrayList<Patron> waitList;

    public WaitList() {
        waitList = new ArrayList<Patron>();
    }

    public void add(String name, int phoneNumber , int groupCount) {
        Patron patron = new Patron(name, phoneNumber, groupCount);
        this.waitList.add(patron);
        System.out.println(patron.name + " has been added to the wait list.");
    }

    public boolean eligiblePatron(Table table) {
        for (Patron patron : waitList) {
            if (table.numSeats >= patron.groupCount) {
                return true;
            }
        }
        return false;
    }

    public Patron seatFromWaitList(Table table) {
        for (Patron patron : waitList) {
            if (table.numSeats >= patron.groupCount) {
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