package springMVCHibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springMVCHibernate.model.User;
import springMVCHibernate.service.UserService;

import java.sql.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/")
    public String getHomePage(ModelMap modelMap) {
        List<User> userList = userServiceImpl.readAll();
        modelMap.addAttribute("userList", userList);
        return "index";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userServiceImpl.add(user.getName(), user.getSurname(), user.getBirthdate());
        return "redirect:/";
    }
}
