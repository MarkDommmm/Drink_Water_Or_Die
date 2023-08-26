package hh.Model;

import hh.Service.AdminService_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Customer {
    @Autowired
    private  AdminService_User adminServiceUser;
    private int id;
    private String fullname;


    private String username;

    private String password;

    private String password2;
    private String country;
    private String city;
    private String phone;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    private String avatar;
    private int role = 2;
    private boolean status;
    private  float totalpurchase;

    public Customer() {
    }

    public Customer(String username, String password, String password2) {
        this.username = username;
        this.password = password;
        this.password2 = password2;
    }

    public Customer(int id, String fullname, String username, String password, String country, String city, String phone, String email, Date birthdate, String avatar, int role, boolean status, float totalpurchase) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.country = country;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.birthdate = birthdate;
        this.avatar = avatar;
        this.role = role;
        this.status = status;
        this.totalpurchase = totalpurchase;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public float getTotalpurchase() {
        return totalpurchase;
    }

    public void setTotalpurchase(float totalpurchase) {
        this.totalpurchase = totalpurchase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
