package com.resources.BillManagementSystem;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



class LoginForm {
    public String username;
    public String password;
}

class VerifyForm {
    public String token;
}

class BillForm {
	public String token;
	public int id;
    
}

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class IndexController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BillRepository billRepository;
	
	DataManager manager = new DataManager();
	
	@PostMapping(value = "/login", headers="Content-Type=application/json")
	@ResponseBody
	public String login(@RequestBody LoginForm l) throws ParseException{
		
		
		//sprawdzenie czy jest user o takim nickname
		User user = userRepository.findOneByName(l.username);
		try {
			if(user.getName() == l.username);
	
		}catch(Exception e)
		{
			return sendMessage("error", "Incorrect username");
		}
		
		//sprawdzenie hasla
		if(!l.password.equals(user.getPassw()))
			return sendMessage("error", "Incorrect password");
		
		//sprawdzenie czy user nie jest juz zalogowany
		/*if(manager.checkIfLogged(user) == "logged") 
		{
		System.out.println("wyjatek");
		return sendMessage("error","User already connected");
		}*/
		
		user.setToken(manager.getNewToken());
		user.setTokenExpireDate(manager.newExpireDate());
		
		System.out.println(user.getToken() + user.getTokenExpireDate());
		userRepository.save(user);
		//funkcja przypisujaca token
		return sendMessage("token",user.getToken());
	}
	
	@PostMapping(value = "/verify", headers="Content-Type=application/json")
	@ResponseBody
	public String login(@RequestBody VerifyForm l){
		
		return sendMessage("verify", verifyByToken(l));
	}
	
	@PostMapping(value = "/getBills", headers="Content-Type=application/json")
	@ResponseBody
	public String getBills(@RequestBody VerifyForm l){
		
		String temp = verifyByToken(l);
		System.out.println(temp);
		if(verifyByToken(l) == "ok")
		{
			//pobierz liste wszystkie paragony
			User user = userRepository.findOneByToken(l.token);
			try {
				Set<Bill> bills = user.getBills();
				if(bills.size() > 0)
				{
					DataManager manager = new DataManager();
					
					return manager.getBillsStringJson(bills.toArray());
				}

			}catch(Exception e)
			{
				System.out.println(e);
				return sendMessage("error", "No logged user found");
			}
			
			
		}
		return "[]";

	}
	
	@PostMapping(value = "/deleteBill", headers="Content-Type=application/json")
	@ResponseBody
	public String deleteBill(@RequestBody BillForm l){
		
		
		//jezeli uzytkownik o podanym tokenie posiada rachunek o id = l.id to usuwany 
		//poprawny token
		//jezeli  wystapil blad to zwracamy {error: <opis>}
		
		
		//nie bylo bledu
		return "{}";
	}
	
	@PostMapping(value = "/getBill", headers="Content-Type=application/json")
	@ResponseBody
	public String getBill(@RequestBody BillForm l){
		
		//find user with token
		User user;
		
		//jezeli uzytkownik o podanym tokenie posiada rachunek o id = l.id to zwracamy mu szczegoly a jesli nie to 
		//zwracamy blad access denided {error: "access denided"}
		//jezeli  wystapil blad to zwracamy 
		
		try{
			user = userRepository.findOneByToken(l.token);
			Bill bill = user.getBillById(l.id);
			
			if(bill == null) {
				return "{}";
			}
			
			String t1,t2,t3,t4;
			t1 = String.valueOf(bill.getId());
			t2 = bill.getDate();
			t3 = String.valueOf(bill.getPrice());
			t4 = bill.getDescription();
			String message = manager.getBillStringJson(t1,t2,t3,t4);
			//nie bylo bledu
			return message;
			
		}catch(Exception e) {
			return sendMessage("error", "No logged user found");
		}		
	}
	
	@PostMapping(value = "/createBill", headers="Content-Type=application/json")
	@ResponseBody
	public String createBill(@RequestBody VerifyForm l){
		
		//tworzymy rachuenk z domyslnymi parametrami dla klienta o podanym tokenie. jesli wszystko pojdzie ok to
		//zwracamy pusty obiekt a jesli nie to {error: <opis>}
		
		

		
		//nie bylo bledu
		return "{}";
	}
	
	
	@PostMapping("/logout")
	@ResponseBody
	public String logout(@RequestBody VerifyForm l){
		
		DataManager manager = new DataManager();
		User user = userRepository.findOneByToken(l.token);
		try {
			if(user.getToken() == l.token);
			user.setTokenExpireDate(manager.logout());
			userRepository.save(user);
			return sendMessage("verify", "ok");
		}catch(Exception e)
		{
			return sendMessage("verify", "bad");
		}
		//wylogowanie klienta po tokenie
	}
	
	public String sendMessage(String name, String value)
	{
		String message = "{\"" + name + "\":\"" + value + "\"}";
		return message;
	}
	
	public String verifyByToken(VerifyForm l)
	{
		User user = userRepository.findOneByToken(l.token);
		try {
			if(user.getToken() == l.token);
			return "ok";
		}catch(Exception e)
		{
			return "bad";
		}
	}
	
	@PostMapping(value = "/userCount", headers="Content-Type=application/json")
	@ResponseBody
	public String userCount(){
		
		
		//jezeli uzytkownik o podanym tokenie posiada rachunek o id = l.id to usuwany 
		//poprawny token
		//jezeli  wystapil blad to zwracamy {error: <opis>}
		
		List<User> user = userRepository.findAll();
		int size = user.size();
		//nie bylo bledu
		return "{\"userCount\":\"" + size + "\"}";
	}
	
	@PostMapping(value = "/billCount", headers="Content-Type=application/json")
	@ResponseBody
	public String billCount(){
		
		return "{\"userCount\":\"" + billRepository.findAll().size() + "\"}";
	}
	
	@PostMapping(value = "/billMax", headers="Content-Type=application/json")
	@ResponseBody
	public String billMax(){
		
		List<Bill> bills = billRepository.findAll();
		
		float max = 0;
		
		for(Bill b : bills) {
			if(b.getPrice() > max){
				max = b.getPrice();
			}
		}
		
		return "{\"billMax\":\"" + max + "\"}";
	}
	
	@PostMapping(value = "/userSumMax", headers="Content-Type=application/json")
	@ResponseBody
	public String userSumMax(){
		
		List<User> users = userRepository.findAll();
		
		float max = 0;
		
		for(User u : users) {
			float buf = u.countBillsPrice();
			if(buf > max){
				max = buf;
			}
		}
		
		return "{\"userSumMax\":\"" + max + "\"}";
	}
}
