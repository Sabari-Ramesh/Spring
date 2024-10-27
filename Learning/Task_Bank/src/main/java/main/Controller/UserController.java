package main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.Exception.InVailDateException;
import main.Exception.InvalidEmailException;
import main.Exception.NameException;
import main.Exception.PasswordException;
import main.entity.User;
import main.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/users/bulk-insert")
	public ResponseEntity<?> bulkInsert(@RequestBody List<User> users) {
	    
	        try {
				userService.bulkInsert(users);
				return ResponseEntity.ok("Users Details are inseted Sucessfully !!!");
			} catch (NameException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			} catch (InvalidEmailException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			} catch (PasswordException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			} catch (InVailDateException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
	        
	    
	}

}
