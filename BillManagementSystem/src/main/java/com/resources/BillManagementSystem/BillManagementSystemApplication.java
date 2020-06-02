package com.resources.BillManagementSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.resources.BillManagementSystem.*;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BillManagementSystemApplication implements CommandLineRunner{

	
	// @Autowired
	 //UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(BillManagementSystemApplication.class, args);
	}
	
	@Override
	public void run(String... args) {
   
		
        
        /*
        List<User> user = userRepository.findAll();
        //user.forEach(System.out::println);
        
        int i = 0;
        for(@SuppressWarnings("unused") Object o : user)
        {
        	User newUser  = user.get(i);
        	i++;
            System.out.println(newUser.getValue());
        }
        
        System.out.println("\n\n");*/
        
        
        

        //User someUser = getUser();

        //userRepository.save(someUser);

        //List<User> users2 = userRepository.findAll();
        //users2.forEach(System.out::println);

       /* List<User> foundUsers = userRepository.findByUsername("kamil brzezinski");
        foundUsers.forEach(System.out::println);

        System.out.println();

        List<User> foundUsers2 = userRepository.findByUsernameContaining("kam");
        foundUsers2.forEach(System.out::println);

        System.out.println();

        List<User> foundUsers3 = userRepository.findByAgeGreaterThan(25);
        foundUsers3.forEach(System.out::println);

        System.out).println();

        List<String> cities = new ArrayList<>();
        cities.add("KrakĂłw");
        cities.add("Kielce");
        List<User> foundUsers4 = userRepository.findByCityIn(cities);
        foundUsers4.forEach(System.out::println);
       */ 
    }

}
