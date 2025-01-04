package SpringSequrity.SpringSequre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/adduser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        User userObj = userRepo.save(user);
        return ResponseEntity.ok(userObj);
    }
}
