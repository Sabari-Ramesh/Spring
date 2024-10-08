package main;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import main.Exception.CityException;
import main.Exception.DateException;
import main.Exception.GenderException;
import main.Exception.InvalidEmailException;
import main.Exception.NameException;
import main.Exception.NumberException;
import main.Exception.PasswordException;
import main.entity.CityInfo;
import main.entity.GenderInfo;
import main.entity.User;
import main.service.UserService;

@SpringBootApplication
@EnableJpaAuditing
public class DietApplication {

	@Autowired
	private  UserService userService;
	
	static Logger log=Logger.getLogger(DietApplication.class);
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DietApplication.class, args);        
    	PropertyConfigurator.configure("D:\\Spring-workspace-2\\DietApplication\\src\\main\\java\\log4j\\log4j.properties");
    	log.info("Application Started");
    	
    	DietApplication application=context.getBean(DietApplication.class);
    	//application.insertUser();
    }
    
/* 
    
    private void insertUser() {
        User user = new User();
        user.setUserName("Karan");
        user.setPassword("kara1PP@23");
        user.setMobileNumber("9674856321");
        user.setEmail("karan223@gmail.com");
        user.setDob(LocalDate.parse("2000-05-02"));
        
        CityInfo city = new CityInfo();
        city.setCityName("New York");
       // city.setCity(1);

        GenderInfo gender = new GenderInfo();
        gender.setGender("Male");
       // gender.setGenderId(1);
        
        user.setCity(city);
        user.setGender(gender);
        
        User insertedDetail;
		try {
			insertedDetail = userService.insertDetails(user);
			 System.out.println(insertedDetail);
		} catch (DateException | InvalidEmailException | NumberException | PasswordException | NameException
				| CityException | GenderException |DataIntegrityViolationException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} // Use the injected service
       
    }
    */
    
}
