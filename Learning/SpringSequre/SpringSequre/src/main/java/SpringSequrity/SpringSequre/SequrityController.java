package SpringSequrity.SpringSequre;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SequrityController {

    ArrayList<StudentEntity> arrayList = new ArrayList<>();

    @GetMapping("/hello")
    public String testSite(){
        return "Hello World!!!";
    }
    @GetMapping("/allstudent")
    public List<StudentEntity> listAllStudent(){
        arrayList.add(new StudentEntity(1,"Sabari","Java"));
        arrayList.add(new StudentEntity(2,"praveen","C++"));
        return arrayList;
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody StudentEntity studentEntity){
        arrayList.add(studentEntity);
        return "Successfully Added";
    }

    @GetMapping("/getcsrftoken")
    public CsrfToken getCSRFToken(HttpServletRequest req){
        return (CsrfToken) req.getAttribute("_csrf");
    }

}
