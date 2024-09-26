package ManyTOMany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepositiory extends JpaRepository<Students,Long> {

}
