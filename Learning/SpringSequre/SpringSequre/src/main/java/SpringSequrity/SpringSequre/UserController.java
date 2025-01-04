package SpringSequrity.SpringSequre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/adduser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        User userObj = userRepo.save(user);
        return ResponseEntity.ok(userObj);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getName());
        }
        else {
            return "Login Failed";
        }
    }
}
