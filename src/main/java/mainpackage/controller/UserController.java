package mainpackage.controller;

import mainpackage.model.LoginRequest;
import mainpackage.model.LoginResponse;
import mainpackage.model.SignupResponse;
import mainpackage.model.User;
import mainpackage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public SignupResponse signUp(@RequestBody User user){

        SignupResponse signupResponse = userService.register(user);
        return signupResponse;
    }

    @PostMapping(value = "/signin", consumes = "application/json", produces = "application/json")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){

        LoginResponse loginResponse = userService.authenticate(loginRequest);
        return loginResponse;
    }
}
