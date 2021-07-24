public class Table {
    public int id;
    public int numSeats;
    private boolean isReserved;
    private boolean isEmpty;
    public Server server;

    public Table(int numSeats, int id) {
        this.id = id;
        this.numSeats = numSeats;
        this.isReserved = false;
        this.isEmpty = true;
        this.server = null;
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

    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public String toString() {
        return "Table{" +
                "numSeats=" + this.numSeats +
                ", server=" + this.server + 
                ", id=" + this.id +
                "}";
    }

}