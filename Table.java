import java.util.Date;

public class Table {
    public int id;
    public int numSeats;
    private boolean isReserved;
    private boolean isEmpty;
    public Server server;
    private long seatingTime;

    public Table(int id, int numSeats) {
        this.id = id;
        this.numSeats = numSeats;
        this.isReserved = false;
        this.isEmpty = true;
        this.server = null;
        this.seatingTime = 0;
    }

    public void fillTable(boolean reservation) {
        if(this.isAvaliable() && reservation) {
            this.isReserved = true;
            Date now = new Date();
            this.seatingTime = now.getTime();
        } else if (this.isAvaliable() && !reservation) {
            this.isReserved = false;
            this.isEmpty = false;
            Date now = new Date();
            this.seatingTime = now.getTime();
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

    public long seatingTimePassed() {
        Date now = new Date();
        long timePassed = now.getTime() - this.seatingTime;
        return timePassed;
    }

    @Override
    public String toString() {
        return "Table{\n" +
                "id=" + id +
                "\nnumSeats=" + this.numSeats +
                ", \nserver=" + this.server + 
                "\n}";
    }

}