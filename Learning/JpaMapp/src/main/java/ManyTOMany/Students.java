package ManyTOMany;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Students {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
    		name="Student_table",
    		joinColumns=@JoinColumn(name="student_id"),
    		inverseJoinColumns=@JoinColumn(name="Course_id")
    		)
	private List<Course> courses;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourse() {
		return courses;
	}

	public void setCourse(List<Course> course) {
		this.courses = course;
	}
}
