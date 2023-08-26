package hh.Model;

public class FormOrder {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String note;

    public FormOrder() {
    }

    public FormOrder(int id, String name, String address, String phone, String note) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.note = note;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

