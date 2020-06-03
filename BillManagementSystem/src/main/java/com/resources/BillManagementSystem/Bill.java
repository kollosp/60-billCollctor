package com.resources.BillManagementSystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class Bill {
	
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    //@ManyToOne(targetEntity = User.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userid", referencedColumnName="userId")
	private User userId;
    
    @Column(name="image")
	private byte image[];
    
    @Column(name="price")
	private int price;
    
    @Column(name="description")
	private String description;
    
    @Column(name="date")
	private String date;
	
    Bill(){};
	
	Bill(User userId, byte image[], int price, String description, String date)
	{
		this.userId = userId;
		this.image = image;
		this.price = price;
		this.description = description;
		this.date = date;
		
	}
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}


	public void setUserId(User userId) {
		this.userId = userId;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	

	

	
}
