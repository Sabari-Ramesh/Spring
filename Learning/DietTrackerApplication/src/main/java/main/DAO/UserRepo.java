package main.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users,Long>{

}
