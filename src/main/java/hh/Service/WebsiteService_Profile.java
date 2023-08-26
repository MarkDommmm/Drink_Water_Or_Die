package hh.Service;

import hh.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
@Service
public class WebsiteService_Profile implements  IGenericService<Customer,Integer> {
    @Autowired
    private DataSource dataSource;
    @Override
    public List<Customer> findall() {
        return null;
    }

    @Override
    public void save(Customer customer) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
                CallableStatement callSt = conn.prepareCall("{call  UpdateProfile(?,?,?,?,?,?,?,?,?)}");
                callSt.setInt(1, customer.getId());
                callSt.setString(2, customer.getFullname());
                callSt.setString(3, customer.getUsername());
                callSt.setString(4, customer.getPassword());
                callSt.setString(5, customer.getCountry());
                callSt.setString(6, customer.getCity());
                callSt.setString(7, customer.getPhone());
                callSt.setString(8, customer.getEmail());
                callSt.setDate(9, new Date(customer.getBirthdate().getTime()));
                callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void updateAvatar(Customer customer) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            CallableStatement callSt = conn.prepareCall("{call  UpdateAvatar(?,?)}");
            callSt.setInt(1, customer.getId());
            callSt.setString(2, customer.getAvatar());
            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    @Override
    public Customer findById(Integer integer) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
