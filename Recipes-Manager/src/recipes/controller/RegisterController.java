package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recipes.entity.User;
import recipes.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(RegisterController.ENDPOINT)
public class RegisterController {
    public static final String ENDPOINT = "api/register";

    @Autowired
    UserService userService;

    @PostMapping
    public void registerUser(@RequestBody @Valid User user) {
        user.setRoles("ROLE_USER");
        userService.createUser(user);
    }
}