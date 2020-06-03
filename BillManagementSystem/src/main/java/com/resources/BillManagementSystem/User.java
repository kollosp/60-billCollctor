package com.resources.BillManagementSystem;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	
    @Id
    @Column(name="userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    
    @Column(name="name")
	private String name;
    
    @Column(name="password")
	private String passw;
    
    @Column(name="token")
	private String token;
    
    @Column(name="tokenexpiredate")
	private String tokenExpireDate;
    
    @Column(name="surname")
	private String surname;
    
    @Column(name="phone")
	private String phone;
    
    @Column(name="email")
    private String mail;
	
	@OneToMany(mappedBy="userId", fetch = FetchType.LAZY)
	private Set<Bill> bills = new HashSet<Bill>(0);
	
	User(){};
	
	User(String name, String passw, String token, 
			String tokenExpireDate, String surname, String phone, String mail)
	{
		this.name = name;
		this.passw = passw;
		this.token = token;
		this.tokenExpireDate = tokenExpireDate;
		this.surname = surname;
		this.phone = phone;
		this.mail = mail;
	}

	
	
	public int getUserId() {
		return userId;
	}

	public void setId(int userId) {
		this.userId = userId;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public String getPassw() {
		return passw;
	}



	public void setPassw(String passw) {
		this.passw = passw;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public String getTokenExpireDate() {
		return tokenExpireDate;
	}



	public void setTokenExpireDate(String tokenExpireDate) {
		this.tokenExpireDate = tokenExpireDate;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Bill getBillById(int id)
	{
		Object[] a = bills.toArray();
		
		for(Object o: a)
		{
			Bill b = (Bill)o;
			if(b.getId() == id)
			{
				return b;
			}
		}
		
		return null;
	}
	
	public float countBillsPrice()
	{
		float sum =0 ;
		
		Object[] a = bills.toArray();
		
		for(Object o: a)
		{
			Bill b = (Bill)o;
			sum += b.getPrice();
		}
		
		return sum;
	}

	public Set<Bill> getBills()
	{
		return bills;
	}
    public String getValue()
    {
    	String temp = "Id = " + userId + "    Login: " + name + "    pass:" + passw;
    	return temp;
    }
}

