package run.qontract.example.client;

public class Pet {
    private int id;
    private String name;
    private String type;
    private String status;

    public Pet() {}

    public Pet(int id, String name, String type, String status) {
        this.setId(id);
        this.setName(name);
        this.setType(type);
        this.setStatus(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
