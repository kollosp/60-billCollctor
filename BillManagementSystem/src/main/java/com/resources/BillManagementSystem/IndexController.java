package com.resources.BillManagementSystem;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping(value = "/login", headers="Content-Type=application/json")
	@ResponseBody
	public String login(@RequestBody LoginForm l){
		
		//nieznany uzytkownik
		if(!l.username.equals("pawel")) {
			return "{\"error\":\"Incorrect username\"}";
		}
			
		//bledne haslo dla uzytkownika
		if(!l.password.equals("123")) {
			return "{\"error\":\"Incorrect password\"}";
		}
		
		return "{ \"token\": \"123456789\"}";
	}
	
	@PostMapping(value = "/verify", headers="Content-Type=application/json")
	@ResponseBody
	public String login(@RequestBody VerifyForm l){
		
		//sprawdzamy czy klient o podanym tokienie jest zalogowany czy nie
		if(l.token.equals("123456789")) {
			return "{\"verify\":\"ok\"}";
		}else {
			return "{\"verify\":\"bad\"}";
		}
	}
	
	@PostMapping(value = "/getBills", headers="Content-Type=application/json")
	@ResponseBody
	public String getBills(@RequestBody VerifyForm l){
		
		//poprawny token
		//token definuje klienta -> z bazy na jego podstawie wyciagamy rachunki klienta i zwracamy w postaci jsona
		if(l.token.equals("123456789")) {
			return "[{\"id\":\"1\",\"date\":\"6/2/2020, 9:13:58 PM\", \"price\":\"123.45\"},"
					+ "{\"id\":\"2\",\"date\":\"6/2/2020, 9:13:58 PM\", \"price\":\"45.45\"}]";
		}else {
			return "[]";
		}
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
		
		//jezeli uzytkownik o podanym tokenie posiada rachunek o id = l.id to zwracamy mu szczegoly a jesli nie to 
		//zwracamy blad access denided {error: "access denided"}
		//jezeli  wystapil blad to zwracamy 
		
		
		//nie bylo bledu
		return "{\"id\":\"1\",\"date\":\"6/2/2020, 9:13:58 PM\", \"price\":\"123.45\", \"desc\":\"adasdasdasdasdasdasdasd\"}";
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
		//wylogowanie klienta po tokenie
		return "ok";
	}
}
