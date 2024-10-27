package main.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
	
	@Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
    Account findByAccountNumber(@Param("accountNumber") Long accountNumber);
	
	 @Query("SELECT a FROM Account a WHERE a.balance = (SELECT MAX(ac.balance) FROM Account ac)")
	 List<Account> findAccountWithMaxBalance();
	
}
