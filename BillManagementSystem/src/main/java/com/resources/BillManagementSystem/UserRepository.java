package com.resources.BillManagementSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	//sprawdzenie loginu
	List<User> findByName(String username);
	
	User findOneByName(String username);
	
	User findOneByToken(String token);
	
	User findOneByMail(String email);
	
	User findOneByUserId(int id);
	
	//sprawdzenie hasla
	List<User> findByPassw(String password);
	
	//sprawdzenie sprawdzenie tokenu
	List<User> findByToken(String token);
	
	//sprawdzenie telefonu
	List<User> findByPhone(String phone);
	

	
}

