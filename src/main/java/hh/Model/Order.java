package hh.Model;

import java.util.Date;

public class Order {
    private int idOrder;
    private int id_customer;
    private Date order_at;
    private String name;
    private String address;
    private String phone;
    private String description;
    private float total_price;
    private boolean status = true;

    public Order(int idOrder, int id_customer, Date order_at, String name, String address, String phone, String description, float total_price, boolean status) {
        this.idOrder = idOrder;
        this.id_customer = id_customer;
        this.order_at = order_at;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.total_price = total_price;
        this.status = status;
    }

    public Order() {

    }


    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Date getOrder_at() {
        return order_at;
    }

    public void setOrder_at(Date order_at) {
        this.order_at = order_at;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
