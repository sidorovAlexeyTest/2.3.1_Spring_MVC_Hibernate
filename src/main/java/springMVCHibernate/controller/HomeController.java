package springMVCHibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springMVCHibernate.model.User;
import springMVCHibernate.service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class HomeController {

    private UserService userServiceImpl;

    @Autowired
    public HomeController(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")
    public String getHomePage(ModelMap modelMap) {
        List<User> userList = userServiceImpl.readAll();
        modelMap.addAttribute("userList", userList);
        return "index";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute(name = "newUser") User newUser) {
        String userName = new String(newUser.getName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String userSurname = new String(newUser.getSurname().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        userServiceImpl.add(userName, userSurname, newUser.getBirthdate());
        return "redirect:/";
    }

    @PutMapping("/updateUser/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        String userName = new String(user.getName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String userSurname = new String(user.getSurname().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        userServiceImpl.update(id, userName, userSurname, user.getBirthdate());
        return "redirect:/";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.delete(id);
        return "redirect:/";
    }
}
