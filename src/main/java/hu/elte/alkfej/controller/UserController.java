package hu.elte.alkfej.controller;

import hu.elte.alkfej.entity.User;
import hu.elte.alkfej.service.UserNotValidException;
import hu.elte.alkfej.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;
   
    @GetMapping("")
    public ResponseEntity<User> user() {
        if(userService.isLoggedIn()) {
            return ResponseEntity.ok(userService.getLoggedInUser());
        }
        return ResponseEntity.badRequest().build();
    }
    
    
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try{
            return ResponseEntity.ok(userService.login(user));
        }
        catch(UserNotValidException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
