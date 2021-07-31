public class Patron {
    public String name;
    private int phoneNumber;
    public int groupCount;

    public Patron(String name, int phoneNumber, int groupCount) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.groupCount = groupCount;
    }

    public void callPatron() {
        System.out.println("Calling " + name + " at " + phoneNumber+"...");
    }

}