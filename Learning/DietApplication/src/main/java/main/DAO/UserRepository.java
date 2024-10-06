package main.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
