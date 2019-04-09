package kr.hs.dgsw.web_190326.Controller;

import kr.hs.dgsw.web_190326.Domain.User;
import kr.hs.dgsw.web_190326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listuser")
    public List<User> listuser() {
        return this.userService.listUser();
    }

    @GetMapping("viewuser/{id}")
    public User viewuser(@PathVariable Long id) {
        return this.userService.viewUser(id);
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

    @DeleteMapping("/removeuser/{id}")
    public boolean removeUser(@PathVariable Long id) {
        return this.userService.removeUser(id);
    }

    @PutMapping("/edituser/{id}")
    public User editUser(@PathVariable Long id, @RequestBody User user) { return this.userService.editUser(id, user); }

}
