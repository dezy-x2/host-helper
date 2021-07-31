import java.util.ArrayList;

public class WaitList {
    private ArrayList<Patron> waitList;

    public WaitList() {
        waitList = new ArrayList<Patron>();
    }

    public void add(Patron patron) {
        this.waitList.add(patron);
        System.out.println(patron.name + " has been added to the wait list.");
    }
}