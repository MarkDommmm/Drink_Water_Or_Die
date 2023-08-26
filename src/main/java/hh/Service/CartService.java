package hh.Service;

import hh.Model.CartItem;
import hh.Model.Customer;
import hh.Model.FormOrder;

import hh.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements IGenericService<CartItem, Integer> {
    @Autowired
    private DataSource dataSource;
    private List<CartItem> cart = new ArrayList<>();


    @Override
    public List<CartItem> findall() {
        return cart;
    }

    @Override
    public void save(CartItem cartItem) {
        if (findById(cartItem.getId()) == null) {
            cart.add(cartItem);
        } else {
            cart.set(cart.indexOf(findById(cartItem.getId())), cartItem);
        }
    }

    @Override
    public CartItem findById(Integer integer) {
        for (CartItem c : findall()) {
            if (c.getId() == integer) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void deleteById(Integer integer) {
        cart.remove(findById(integer));
    }

    public int getNewId() {
        int idmax = 0;
        for (CartItem c : cart) {
            if (idmax < c.getId()) {
                idmax = c.getId();
            }
        }
        return idmax + 1;
    }

    public CartItem findByIdProduct(int id) {
        for (CartItem c : cart) {
            if (c.getProduct().getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void addOrder(List<CartItem> cartItems, Order order) {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            try (CallableStatement callSt = conn.prepareCall("{call AddOrder(?,?,?,?,?,?,?)}")) {
                callSt.setInt(1, order.getId_customer());
                callSt.setString(2, order.getName());
                callSt.setString(3, order.getAddress());
                callSt.setString(4, order.getPhone());
                callSt.setString(5, order.getDescription());
                callSt.setDouble(6, order.getTotal_price());
                callSt.registerOutParameter(7, Types.INTEGER);
                callSt.executeUpdate();

                int orderId = callSt.getInt(7);

                for (CartItem c : cartItems) {
                    try (CallableStatement call2 = conn.prepareCall("{call AddOrder_Detail(?,?,?,?)}")) {
                        call2.setInt(1, orderId);
                        call2.setInt(2, c.getProduct().getId());
                        call2.setInt(3, c.getQuantity());
                        call2.setDouble(4, c.getPrice());
                        call2.executeUpdate();
                    }

                }



                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error while adding order and details", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting database connection", e);
        }
    }




}
