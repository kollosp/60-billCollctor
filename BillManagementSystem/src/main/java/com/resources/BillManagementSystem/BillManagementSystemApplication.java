package com.resources.BillManagementSystem;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.resources.BillManagementSystem.*;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BillManagementSystemApplication implements CommandLineRunner{

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BillRepository billRepository;
	public static void main(String[] args) {
		SpringApplication.run(BillManagementSystemApplication.class, args);
	}
	
	@Override
	public void run(String... args) {
   
		/*
		  
		Różne gotowe funkcje do testów
		
		String t1,t2;
		t1 = "test";
		t2 = "test";
		
		String pass = BCrypt.hashpw(t2, BCrypt.gensalt());
		
		DataManager data = new DataManager();
		
		System.out.println(data.test2(pass, t1));*/
		
		//User user = userRepository.findOneByName("a");
		//User user = userRepository.findOneByToken(l.token,Sort.by(sortBy).ascending());
		/*User user = userRepository.findOneByUserId(9);
		System.out.println(user.getValue());
		
		List<Bill> newBills = billRepository.findByUserIdAndDescriptionContaining(user, "", Sort.by("description").ascending());
		
		int i =0;
		for(@SuppressWarnings("unused") Object o : newBills)
        {
        	Bill newBill  = newBills.get(i);
        	i++;
        	System.out.println(newBill.getDescription());
        }*/
		//DataManager manager = new DataManager();
		//manager.sortBills(bills, "price");
		
		//funkcja pozwala testowac serwer w konsoli
        
		//User user23 = userRepository.findOneByName("a");
		//System.out.println(user23.getValue());
		
		//User user24 = userRepository.findOneByUserId(2);
		//System.out.println(user24.getValue());
		
		/*User user = userRepository.findOneById(12);
		
				List<Bill> bill = billRepository.findByUserId(user.getUserId());
				
					for(Object o : bill)
					{
						Bill b = (Bill) o;
						billRepository.delete(b);
					}
				
			
					userRepository.delete(user);
		*/
				
		
		//List<Bill> bill = billRepository.findByUserId(user23);
		/*List<Bill> bills = billRepository.findByUserId(user23,Sort.by("price")); 
		int i = 0;
		System.out.println(bills.size());
        for(@SuppressWarnings("unused") Object o : bills)
        {
        	Bill newBill  = bills.get(i);
        	i++;
        	System.out.println(newBill.getPrice());
        }*/
		
		//user23.setToken("N3shi");
		//user23.setTokenExpireDate("2020-03-20 12:20:21");
		//userRepository.save(user23);
		
		/*try {
			User user234 = userRepository.findOneByName("N3shii");
			System.out.println(user234.getValue());
		}catch(Exception e)
		{
			System.out.println("Nie ma takiego uzytkownika");
		}*/
	
		
     
    }

}
