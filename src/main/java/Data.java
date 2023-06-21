public class Data {

    private int id;
    private String first_name;
    private String last_name;

    public Data(int id, String firstName, String lastName) {
        this.id = id;
        this.first_name = firstName;
        this.last_name = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
} 