public class Table {
    public int numSeats;
    private boolean isReserved;
    private boolean isEmpty;
    public Server server;

    public Table(int numSeats, Server server) {
        this.numSeats = numSeats;
        this.isReserved = false;
        this.isEmpty = true;
        this.server = server;
    }

    public void fillTable(boolean reservation) {
        if(this.isAvaliable() && reservation) {
            this.isReserved = true;
        } else if (this.isAvaliable() && !reservation) {
            this.isReserved = false;
            this.isEmpty = false;
        }
        
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
                "}";
    }

}