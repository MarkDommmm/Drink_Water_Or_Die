package hh.Controller;

import hh.Model.*;
import hh.Service.AdminService_Order;
import hh.Service.AdminService_Product;

import hh.Service.AdminService_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping({"/admin"})
@PropertySource("classpath:upload.properties")
public class AdminController {
    @Autowired
    private AdminService_Order adminServiceOrder;

    @Autowired
    private AdminService_User adminServiceUser;

    @Autowired
    private AdminService_Product adminService;

    @Autowired
    ServletContext context;

    @Value("C:\\Users\\Admin\\Desktop\\a\\ADMIN\\src\\main\\webapp\\WEB-INF\\upload\\")
    private String pathUpload;

    //    Điều hướng về home
    @GetMapping("/home")
    public String admin() {
        return "/admin/Dashboard";
    }

    // lấy tất cả products
    @GetMapping("/products")
    public String tableproduct(Model model) {
        model.addAttribute("list", adminService.findall());
        return "/admin/TableProduct";
    }

    // Thêm products
    @GetMapping("/addProduct")
    public ModelAndView addProduct() {
        return new ModelAndView("/admin/addProducts", "product", new Product());
    }

    @PostMapping("/addProduct")
    public String save(@ModelAttribute("product") ProductDto productDto) {
        File file = new File(pathUpload);
        if (!file.exists()) {
            file.mkdir();
        }
        String urlimage = productDto.getImage().getOriginalFilename();

        try {
            FileCopyUtils.copy(productDto.getImage().getBytes(), new File(pathUpload + urlimage));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Product p = new Product();
        p.setId(productDto.getId());
        p.setNameproduct(productDto.getNameproduct());
        p.setImage(urlimage);
        p.setPrice(productDto.getPrice());
        p.setStock(productDto.getStock());
        p.setDate(productDto.getDate());
        p.setDescription(productDto.getDescription());
        p.setStatus(productDto.isStatus());
        adminService.save(p);
        return "redirect:/admin/products";
    }

    // END thêm products
//    Xóa product
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        adminService.deleteById(id);
        return "redirect:/admin/products";
    }

    //    Cập nhật product
    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") int id, HttpSession session) {
        Product product = adminService.findById(id);
        session.setAttribute("imgOld", product.getImage());
        return new ModelAndView("/admin/updateProduct", "productUpdate", product);
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("productUpdate") ProductDto productDto, HttpSession session) {
        File file = new File(pathUpload);
        if (!file.exists()) {
            file.mkdir();
        }

        String urlimage = productDto.getImage().getOriginalFilename();
        String imgOld = (String) session.getAttribute("imgOld");

        try {
            if (!productDto.getImage().isEmpty()) {
                FileCopyUtils.copy(productDto.getImage().getBytes(), new File(pathUpload + urlimage));
            } else {
                urlimage = imgOld;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Product p = new Product();
        p.setId(productDto.getId());
        p.setNameproduct(productDto.getNameproduct());
        p.setImage(urlimage);
        p.setPrice(productDto.getPrice());
        p.setStock(productDto.getStock());
        p.setDate(productDto.getDate());
        p.setDescription(productDto.getDescription());
        p.setStatus(productDto.isStatus());
        adminService.save(p);
        return "redirect:/admin/products";
    }


    // lấy tất cả User
    @GetMapping("/users")
    public String tableUser(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
        int pageSize = 5; // Số bản ghi hiển thị trên mỗi trang
        List<Customer> customerList = adminServiceUser.findall();

        int totalRecords = customerList.size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        List<Customer> paginatedCustomerList = new ArrayList<>();
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalRecords);

        for (int i = startIndex; i < endIndex; i++) {
            paginatedCustomerList.add(customerList.get(i));
        }

        List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());

        model.addAttribute("list", paginatedCustomerList);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);

        return "/admin/TableUser";
    }


    //    // Thêm User
    @GetMapping("/addUser")
    public ModelAndView addUser() {
        return new ModelAndView("/admin/addUser", "users", new Customer());
    }

    @PostMapping("/addUser")
    public String save(@ModelAttribute("users") Customer customer) {
        adminServiceUser.save(customer);
        return "redirect:/admin/users";
    }

    //    // END thêm User
////    Xóa product
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        adminServiceUser.deleteById(id);
        return "redirect:/admin/users";
    }

    //    //    Cập nhật User
    @GetMapping("/updateUser/{id}")
    public ModelAndView updateUser(@PathVariable("id") int id, Model model) {
        return new ModelAndView("/admin/updateUser", "UserUpdate", adminServiceUser.findById(id));
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("UserUpdate") Customer customer) {
//        File file = new File(pathUpload);
//        if (!file.exists()) {
//            file.mkdir();
//        }
//        String urlimage = customerDto.getAvatar().getOriginalFilename();
//
//        try {
//            FileCopyUtils.copy(customerDto.getAvatar().getBytes(), new File(pathUpload + urlimage));
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//        Customer c = new Customer();
//        c.setId(customerDto.getId());
//        c.setFullname(customerDto.getFullname());
//        c.setUsername(customerDto.getPassword());
//        c.setCountry(customerDto.getCountry());
//        c.setCity(customerDto.getCity());
//        c.setPhone(customerDto.getPhone());
//        c.setEmail(customerDto.getEmail());
//        c.setBirthdate(customerDto.getBirthdate());
//        c.setAvatar(urlimage);
//        c.setRole(customerDto.getRole());
//        c.setStatus(customerDto.isStatus());
        adminServiceUser.save(customer);
        return "redirect:/admin/users";
    }

    @GetMapping("/order")
    public ModelAndView getOrder() {
        return new ModelAndView("/admin/TableOrders", "order", adminServiceOrder.findall());

    }

    @GetMapping("/editorder/{id}")
    public ModelAndView getOrder(@PathVariable int id) {
        return new ModelAndView("/admin/updateOrder", "orderedit", adminServiceOrder.findById(id));
    }

    @PostMapping("/editorder")
    public String updateOrder(@ModelAttribute("orderedit") Order order) {
        adminServiceOrder.save(order);
        return "redirect:/admin/order";
    }

    @GetMapping("/getproductdetail/{id}")
    public ModelAndView getProduct(@PathVariable int id) {

        return new ModelAndView("/admin/ProductDetails", "productDetails", adminServiceOrder.OrderDetailProduct(id));
    }

    @PostMapping("/searchname")
    public ModelAndView getSearchName(@RequestParam("nameproduct") String nameproduct) {
        return new ModelAndView("/admin/TableProduct", "list", adminService.SearchName(nameproduct));
    }

    @GetMapping("/sortproduct")
    public ModelAndView getSearchName() {
        return new ModelAndView("/admin/TableProduct", "list", adminService.DESCPRODUCT());
    }
}
