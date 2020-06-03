package com.resources.BillManagementSystem;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import org.springframework.beans.factory.annotation.Autowired;


public class DataManager {

	@Autowired
	UserRepository userRepository;
	
	public List<User> findByName(String name)
	{
		return userRepository.findByName(name);
	}
	
	public String checkIfLogged(User user) throws ParseException//zwraca "logged" jesli uzytkownik jest zalogowany lub "notlogged" jesli nie jest
	{
		String formattedDate = getActualDate();
		 Date d1 = null;
		 Date d2 = null;
		
		 SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		 d1 = sdf.parse(user.getTokenExpireDate());
		 d2 = sdf.parse(formattedDate);
		 
		 Calendar now = Calendar.getInstance();
		 
		 String date1 = sdf.format(d1);
		 String date2 = sdf.format(d2);
		 
		 System.out.println(date1 + "  " +  date2);
		 
		 
		 //czy data tokena jest pozniejsza niz data aktualna?
		 boolean check = d1.after(d2);
		//jesli jego data jest starsza niz aktualna, to znaczy ze nie jest zalogowany
		if(check) return "logged";
		else return "notlogged"; 
	}
	
	public String newExpireDate() throws ParseException
	{
		 SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

		 Calendar now = Calendar.getInstance();
		 now.add(Calendar.MINUTE, 10);
		 Date teenMinutesFromNow = now.getTime();
		 String neewDate = sdf.format(teenMinutesFromNow);

		 return neewDate;	
	}
	
	public String logout() throws ParseException
	{
		 SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

		 Calendar now = Calendar.getInstance();
		 now.add(Calendar.MINUTE, -10);
		 Date teenMinutesFromNow = now.getTime();
		 String neewDate = sdf.format(teenMinutesFromNow);

		 return neewDate;	
	}
	
	public String getNewToken()
	{
		 SecureRandom secureRandom = new SecureRandom(); //threadsafe
		 Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
		
		 byte[] randomBytes = new byte[24];
		 secureRandom.nextBytes(randomBytes);
		 String token = base64Encoder.encodeToString(randomBytes);
		 
		 return token;
	}
	
	public String getActualDate()
	{
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
		String formattedDate = myDateObj.format(myFormatObj);
		
		return formattedDate;
	}
	
	public String getBillStringJson(String id, String date, String price, String descr)
	{
		String command = "{\"id\":\"" + id + "\",\"date\":\"" + date + "\", "
				+ "\"price\":\"" + price+ "\", \"desc\":\"" + descr+ "\"}";;
		
		return command;
	}
	
	public String getBillsStringJson(Object bills[])
	{
	        String completeJSON = "[";
	        
	        for(@SuppressWarnings("unused") Object o : bills)
	        {
	        	Bill newBill = (Bill)o;
	        
	        	String t1,t2,t3;
	        	t1 = String.valueOf(newBill.getId());
	        	t2 = newBill.getDate();
	        	
	        	t3 = String.valueOf(newBill.getPrice());
	            String temp = getTempJson(t1,t2,t3);
	            
	            completeJSON += temp;
	        }
	        completeJSON =  completeJSON.substring(0, completeJSON.length()-1);
        System.out.println(completeJSON);
		return completeJSON + "]";
	}
	
	public String getTempJson(String id, String date, String price )
	{
		String command = "{\"id\":\"" + id + "\",\"date\":\"" + date + "\", "
				+ "\"price\":\"" + price+ "\"},";;
				
				return command;
	}
	
	

}
