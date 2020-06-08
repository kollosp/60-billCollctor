package com.resources.BillManagementSystem;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

	Bill findOneByIdAndUserId(int id, int userId);
	
	
	List<Bill> findByUserId(User user);
	
	List<Bill> findByUserId(User user, Sort sort);
	//List<Bill> findAllByUserId(int userId);

	List<Bill> findByUserIdAndDescriptionContaining(User user, String description, Sort sort);
	
}

