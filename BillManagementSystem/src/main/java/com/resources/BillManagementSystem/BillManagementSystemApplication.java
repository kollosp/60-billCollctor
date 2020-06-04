package com.resources.BillManagementSystem;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

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
   
		//funkcja pozwala testowac serwer w konsoli
        
		//User user23 = userRepository.findOneByName("N3shi");
		//System.out.println(user23.getValue());
		
		//User user24 = userRepository.findOneByUserId(2);
		//System.out.println(user24.getValue());
		
		
		
		/*List<Bill> bill = billRepository.findByUserId(user23);
		billRepository.find();
		int i = 0;
		System.out.println(bill.size());
        for(@SuppressWarnings("unused") Object o : bill)
        {
        	Bill newBill  = bill.get(i);
        	i++;
        	System.out.println(newBill.getDescription());
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
