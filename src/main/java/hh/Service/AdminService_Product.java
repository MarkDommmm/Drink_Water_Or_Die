package hh.Service;

import hh.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService_Product implements IGenericService<Product, Integer> {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Product> findall() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Product> productList = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call FindAllProduct}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setNameproduct(rs.getString("nameproduct"));
                p.setImage(rs.getString("image"));
                p.setVideo(rs.getString("video"));
                p.setIdm_id(rs.getInt("idm_id"));
                p.setPrice(rs.getFloat("price"));
                p.setStock(rs.getInt("stock"));
                p.setDate(rs.getDate("date"));
                p.setDescription(rs.getString("description"));
                p.setStatus(rs.getBoolean("status"));

                if (p.getStock() <= 0){
                    p.setStatus(false);
                }
                productList.add(p);

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
        return productList;
    }

    @Override
    public void save(Product product) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (product.getId() == 0) {
                // thêm moi
                CallableStatement callSt = conn.prepareCall("{call  AddProduct(?,?,?,?,?,?)}");
                callSt.setString(1, product.getNameproduct());
                callSt.setString(2, product.getImage());
                callSt.setFloat(3, product.getPrice());
                callSt.setInt(4, product.getStock());
                callSt.setDate(5, new Date(product.getDate().getTime()));
                callSt.setString(6, product.getDescription());
                callSt.executeUpdate();
            } else {
                // cap nhat
                CallableStatement callSt = conn.prepareCall("{call  UpdateProduct(?,?,?,?,?,?,?)}");
                callSt.setInt(1, product.getId());
                callSt.setString(2, product.getNameproduct());
                callSt.setString(3, product.getImage());
                callSt.setFloat(4, product.getPrice());
                callSt.setInt(5, product.getStock());
                callSt.setDate(6, new Date(product.getDate().getTime()));
                callSt.setString(7, product.getDescription());
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
    public Product findById(Integer integer) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Product p = null;
        try {
            CallableStatement callSt = conn.prepareCall("{call  FindByIdProduct(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                p = new Product();
                p.setId(rs.getInt("id"));
                p.setNameproduct(rs.getString("nameproduct"));
                p.setImage(rs.getString("image"));
                p.setPrice(rs.getFloat("price"));
                p.setStock(rs.getInt("stock"));
                p.setDate(rs.getDate("date"));
                p.setDescription(rs.getString("description"));
                p.setStatus(rs.getBoolean("status"));
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
        return p;
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
            CallableStatement callSt = conn.prepareCall("{call  DeleteProduct(?)}");
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

    public List<Product> SearchName(String searchName) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Product> productList = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call SearchNameProduct(?)}");
            callSt.setString(1, searchName);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setNameproduct(rs.getString("nameproduct"));
                p.setImage(rs.getString("image"));
                p.setVideo(rs.getString("video"));
                p.setIdm_id(rs.getInt("idm_id"));
                p.setPrice(rs.getFloat("price"));
                p.setStock(rs.getInt("stock"));
                p.setDate(rs.getDate("date"));
                p.setDescription(rs.getString("description"));
                p.setStatus(rs.getBoolean("status"));

                if (p.getStock() <= 0){
                    p.setStatus(false);
                }
                productList.add(p);

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
        return productList;
    }

    public List<Product> DESCPRODUCT() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Product> productList = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call DESCPRODUCT}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setNameproduct(rs.getString("nameproduct"));
                p.setImage(rs.getString("image"));
                p.setVideo(rs.getString("video"));
                p.setIdm_id(rs.getInt("idm_id"));
                p.setPrice(rs.getFloat("price"));
                p.setStock(rs.getInt("stock"));
                p.setDate(rs.getDate("date"));
                p.setDescription(rs.getString("description"));
                p.setStatus(rs.getBoolean("status"));

                if (p.getStock() <= 0){
                    p.setStatus(false);
                }
                productList.add(p);

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
        return productList;
    }
}
