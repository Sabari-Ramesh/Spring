package example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	
	@RequestMapping("/Hello")
	public String sayHello() {
		return "Hello World";
	}
	
	@RequestMapping("/sayHello")
	public String Hellouser(@RequestParam(name="name" ,defaultValue="Jim") String name,
			@RequestParam(name="phone" , required=false) String phone) {
		return " Welcome "+name+" your Mobile Number "+phone;
	}
	
	@RequestMapping("/sayhellofolks/{empID}/{rollId}")
	public String sayHelloFolks(@PathVariable("empId") int empId,@PathVariable("rollno") int rollNo) {
		return "Hi Everybody "+empId+" Your RollNumber "+rollNo;
		
	}
	
	@RequestMapping("/Greetingmsg")
	public Greeting greet() {
		Greeting greet=new Greeting();
		greet.setGreeting("-------------Hello-----------");
		greet.setName("sabari");
		return greet;
	}
	
	@RequestMapping("/Greet")
	public Greeting greeting(@RequestParam(value="name",defaultValue="Tom") String name,
			@RequestParam(value="msg") String msg) {
		Greeting greet=new Greeting();
		greet.setGreeting(msg);
		greet.setName(name);
		return greet;
		
	}
	
	@RequestMapping("/ByList")
	public List<User> userList(){
		List<User> list=new ArrayList();
		
		User u1=new User();
		u1.setAge(20);
		u1.setName("Kin");
		
		User u2=new User();
		u2.setAge(25);
		u2.setName("Master");
		
		list.add(u1);
		list.add(u2);
		
		return list;
	}

	@RequestMapping("/UsingMap")
	public Map<String,User> mapUser(){

		Map<String,User> hm=new HashMap<>();
		User u1=new User();
		u1.setAge(20);
		u1.setName("Kin");
		
		User u2=new User();
		u2.setAge(25);
		u2.setName("Master");
		
		hm.put("Raghul", u2);
		hm.put("Kumar",u1);
		
		return hm;
	}
	
}
