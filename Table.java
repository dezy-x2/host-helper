public class Table {
    public int id;
    public int numSeats;
    private boolean isReserved;
    private boolean isEmpty;
    public Server server;

    public Table(int id, int numSeats) {
        this.id = id;
        this.numSeats = numSeats;
        this.isReserved = false;
        this.isEmpty = true;
        this.server = null;
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
        return "Table{\n" +
                "id=" + id +
                "\nnumSeats=" + this.numSeats +
                ", \nserver=" + this.server + 
                "\n}";
    }

}