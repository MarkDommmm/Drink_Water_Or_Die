package hh.Service;

import hh.Model.Customer;
import hh.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService_User implements IGenericService<Customer, Integer> {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminService_User adminServiceUser;

    @Override
    public List<Customer> findall() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Customer> customerList = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call FindAllUser}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setFullname(rs.getString("fullname"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                c.setCountry(rs.getString("country"));
                c.setCity(rs.getString("city"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setBirthdate(rs.getDate("birthdate"));
                c.setAvatar(rs.getString("avatar"));
                c.setRole(rs.getInt("role"));
                c.setStatus(rs.getBoolean("status"));
                c.setTotalpurchase(rs.getFloat("TotalPurchase"));
                customerList.add(c);

            }

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
        return customerList;
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
            if (customer.getId() == 0) {
                // thêm moi
                CallableStatement callSt = conn.prepareCall("{call AddCustomer(?,?,?,?,?,?,?,?)}");
                callSt.setString(1, customer.getFullname());
                callSt.setString(2, customer.getUsername());
                callSt.setString(3, customer.getPassword());
                callSt.setString(4, customer.getCountry());
                callSt.setString(5, customer.getCity());
                callSt.setString(6, customer.getPhone());
                callSt.setString(7, customer.getEmail());
                callSt.setDate(8, new Date(customer.getBirthdate().getTime()));
                callSt.executeUpdate();
            } else {
                // cap nhat
                CallableStatement callSt = conn.prepareCall("{call  UpdateUser(?,?,?,?,?,?,?,?,?,?,?,?)}");
                callSt.setInt(1, customer.getId());
                callSt.setString(2, customer.getFullname());
                callSt.setString(3, customer.getUsername());
                callSt.setString(4, customer.getPassword());
                callSt.setString(5, customer.getCountry());
                callSt.setString(6, customer.getCity());
                callSt.setString(7, customer.getPhone());
                callSt.setString(8, customer.getEmail());
                callSt.setDate(9, new Date(customer.getBirthdate().getTime()));
                callSt.setString(10, customer.getAvatar());
                callSt.setInt(11, customer.getRole());
                callSt.setBoolean(12, customer.isStatus());
                callSt.executeUpdate();
            }
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
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Customer c = null;
        try {
            CallableStatement callSt = conn.prepareCall("{call FindByIdUser(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                c = new Customer();
                c.setId(rs.getInt("id"));
                c.setFullname(rs.getString("fullname"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                c.setCountry(rs.getString("country"));
                c.setCity(rs.getString("city"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setBirthdate(rs.getDate("birthdate"));
                c.setAvatar(rs.getString("avatar"));
                c.setRole(rs.getInt("role"));
                c.setStatus(rs.getBoolean("status"));
            }

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
        return c;
    }


    @Override
    public void deleteById(Integer integer) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            // xóa ảnh phụ
            CallableStatement callSt = conn.prepareCall("{call DeleteUser(?)}");
            callSt.setInt(1, integer);
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

    public void signUp(Customer customer) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            CallableStatement callSt = conn.prepareCall("{call SignUp(?,?)}");
            callSt.setString(1, customer.getUsername());
            callSt.setString(2, customer.getPassword());
            callSt.executeQuery();
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

    public Customer login(Customer customer) {
        Connection conn = null;
        Customer c = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            CallableStatement callSt = conn.prepareCall("{call Login(?,?)}");
            callSt.setString(1, customer.getUsername());
            callSt.setString(2, customer.getPassword());
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                c = new Customer();
                c.setId(rs.getInt("id"));
                c.setFullname(rs.getString("fullname"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                c.setCountry(rs.getString("country"));
                c.setCity(rs.getString("city"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setBirthdate(rs.getDate("birthdate"));
                c.setAvatar(rs.getString("avatar"));
                c.setRole(rs.getInt("role"));
                c.setStatus(rs.getBoolean("status"));
                c.setTotalpurchase(rs.getFloat("TotalPurchase"));
            }
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
        return c;
    }

    public boolean checkValidate(Customer customer ) {
        List<Customer> customerList = adminServiceUser.findall();

        for (Customer c : customerList) {
            if (customer.getUsername().equals(c.getUsername())) {
                return true;
            }
        }
        return false;
    }
    public boolean check_name_pass(Customer customer ) {
        if (customer.getUsername().trim().equals("") || customer.getUsername().length() < 6 || customer.getUsername().contains(" ")) {
            return true;
        }
        if (customer.getPassword().length() < 6 || customer.getPassword().trim().equals("") || customer.getPassword().contains(" ")) {
            return true;
        }

        return false;
    }

}
