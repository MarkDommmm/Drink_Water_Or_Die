package hh.Model;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Product {
    private int id;
    private String nameproduct;
    private String image;
    private String video;
    private  int idm_id;
    private float price;
    private int quantity;
    private int stock;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String description;
    private boolean status ;

    public Product() {
    }

    public Product(int id, String nameproduct, String image, float price, int stock, Date date, String description, boolean status) {
        this.id = id;
        this.nameproduct = nameproduct;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.date = date;
        this.description = description;
        this.status = status;
    }

    public Product(int id, String nameproduct, String image, String video, int idm_id, float price, int stock, Date date, String description, boolean status) {
        this.id = id;
        this.nameproduct = nameproduct;
        this.image = image;
        this.video = video;
        this.idm_id = idm_id;
        this.price = price;
        this.stock = stock;
        this.date = date;
        this.description = description;
        this.status = status;
    }

    public Product(int id, String nameproduct, String image, String video, int idm_id, float price, int quantity, int stock, Date date, String description, boolean status) {
        this.id = id;
        this.nameproduct = nameproduct;
        this.image = image;
        this.video = video;
        this.idm_id = idm_id;
        this.price = price;
        this.quantity = quantity;
        this.stock = stock;
        this.date = date;
        this.description = description;
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdm_id() {
        return idm_id;
    }

    public void setIdm_id(int idm_id) {
        this.idm_id = idm_id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
