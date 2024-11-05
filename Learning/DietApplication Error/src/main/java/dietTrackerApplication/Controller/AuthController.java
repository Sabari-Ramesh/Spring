package dietTrackerApplication.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dietTrackerApplication.DTO.LoginRequest;
import dietTrackerApplication.entity.User;
import dietTrackerApplication.service.CustomUserDetailsService;



@RestController
@RequestMapping("/auth")
public class AuthController {

	 @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private CustomUserDetailsService  userService;

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
	        // Retrieve the user by email from the database
	        UserDetails user = userService.loadUserByUsername(request.getEmail());
	        
	        // Check if the user exists
	        if (user == null) {
	            return ResponseEntity.status(401).body("Invalid Credentials");
	        }
	        
	        // Compare the plain text password with the stored password
	        if (user.getPassword().equals(request.getPassword())) {
	            // You may want to create a proper response object here
	            return ResponseEntity.ok("Login Successful!");
	        } else {
	            return ResponseEntity.status(401).body("Invalid Credentials");
	        }
	    }
}