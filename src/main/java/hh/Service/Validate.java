package hh.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import hh.Model.Customer;

@Service
public class Validate {
    @Autowired
    private AdminService_User adminServiceUser;

    public boolean checkPasswordMatch(Customer customer) {
        if (customer.getPassword() != customer.getPassword2()) {
            return false;
        }
        return true;
    }

}
