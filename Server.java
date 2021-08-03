public class Server {
    public String name;
    private int salary;

    Server(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Server{\n" + "\nname='" + this.name + '\'' + ", \nsalary=" + this.salary + "\n}";
    }
}