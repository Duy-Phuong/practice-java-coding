package test.systemUnderTest;

public class Record {

    public Record() {
    }

    public Record(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public Record(String name) {
        this.name = name;
    }

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
