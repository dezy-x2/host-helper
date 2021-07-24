public class Table {
    public int numSeats;
    private boolean isReserved;
    private boolean isEmpty;

    public Table(int numSeats) {
        this.numSeats = numSeats;
        this.isReserved = false;
        this.isEmpty = true;
    }

    public void reserve() {
        this.isReserved = true;
        this.isEmpty = false;
    }

    public void empty() {
        this.isReserved = false;
        this.isEmpty = true;
    }

    public boolean isAvaliable() {
        return this.isEmpty && !this.isReserved;
    }

}