package mainpackage.service;

import mainpackage.model.LoginRequest;
import mainpackage.model.LoginResponse;
import mainpackage.model.SignupResponse;
import mainpackage.model.User;
import mainpackage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private String pepper = "Rj7r8yfKe8frNeCw";

    @Autowired
    UserRepository userRepository;

    public SignupResponse register(User user){

        User currentUser = userRepository.findByEmail(user.getEmail());
        SignupResponse signupResponse = new SignupResponse();

        if(currentUser != null){
            signupResponse.setStatus(false);
            signupResponse.setMessage("User already exist");
        }
        else{
            String salt = BCrypt.gensalt();
            String hashedPassword = BCrypt.hashpw(user.getPassword() + pepper, salt);
            user.setPassword(hashedPassword);
            user.setSalt(salt);
            userRepository.save(user);
            signupResponse.setStatus(true);
            signupResponse.setMessage("User created successfully");
        }

        return signupResponse;
    }

    public LoginResponse authenticate(LoginRequest loginRequest){
        User user = userRepository.findByEmail(loginRequest.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        if(user == null){
            loginResponse.setStatus(false);
            loginResponse.setMessage("Invalid email");
        }
        else if(user.getPassword().equals(BCrypt.hashpw(loginRequest.getPassword() + pepper, user.getSalt()))){
            loginResponse.setStatus(true);
            loginResponse.setMessage("Login Successful");
        }
        else{
            loginResponse.setStatus(false);
            loginResponse.setMessage("Wrong password");
        }

        return loginResponse;
    }
}
