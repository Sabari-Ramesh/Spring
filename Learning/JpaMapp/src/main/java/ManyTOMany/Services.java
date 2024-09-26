package ManyTOMany;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Services {

	@Autowired
	private StudentsRepositiory stRepo;
	
	@Autowired
	private CourseRepository coRepo;
	
	public void insertCourse() {
		
		Students st=new Students();		
		st.setName("praveen");
		
		ArrayList<Course> al=new ArrayList<>();
		
		Course c1=new Course();
		c1.setCourseName("Tamil");
		Course c2=new Course();
		c1.setCourseName("English");
		al.add(c1);
		al.add(c2);
		
		st.setCourse(al);
		
		stRepo.save(st);
	}
}
