public class Patron {
    public String name;
    private String phoneNumber;
    public int groupCount;

    public Patron(String name, String phoneNumber, int groupCount) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.groupCount = groupCount;
    }

    @Override
    public String toString() {
        return "Patron{" + "name='" + name + '\'' + ", phoneNumber=" + phoneNumber + ", groupCount=" + groupCount + '}';
    }

    public void callPatron() {
        System.out.println("Calling " + name + " at " + phoneNumber + "...");
    }

}