package hh.Model;


public class CartItem {
    private int idCart;
    private int idOrder;
    private Product product;

    private int quantity;

    private float price;

    public CartItem() {
    }

    public CartItem(int idCart, int idOrder, Product product, int quantity, float price) {
        this.idCart = idCart;
        this.idOrder = idOrder;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getId() {
        return idCart;
    }

    public void setId(int id) {
        this.idCart = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
