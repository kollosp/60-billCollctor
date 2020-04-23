package loginSetup;



import java.util.HashMap;
import java.sql.*;  

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



  public class DataSource {
  
  	private HashMap<String, String> userSource;
  
  	public DataSource() {
  		userSource = new HashMap<String, String>();

  	}
  
  	public String checkIfUserExist(User user)
  	{
  		String ans = "";
  		String tempUserLogin = userSource.get(user.getName());
  		String tempUserPassw = userSource.get(user.getPassword());
  		
  		if(tempUserLogin == "" || tempUserPassw == "")
  			ans = "Niepoprawny login lub haslo";
  		else
  		{
  			try {
  				

  		        // Create a variable for the connection string.
  		        String connectionUrl = "jdbc:sqlserver://localhost:1433;"
  		        		+ "databaseName=[ReceiptManagementSystem];user=test;password=test";

  		        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
  		            String sql = "INSERT INTO LOGINSETUP values (6, 'test', 'test', 'OlaCzapla@o2.pl')";
  		            ResultSet rs = stmt.executeQuery(sql);

  		            // Iterate through the data in the result set and display it.
  		            while (rs.next()) {
  		                System.out.println(rs.getString("username") + " " + rs.getString("passw"));
  		            }
  		        }
  		        // Handle any errors that may have occurred.
  		        catch (SQLException e) {
  		            e.printStackTrace();
  		        }
  				
  				
  			}catch(Exception e)
  			{
  				
  			}
  		}
  		
  		return ans;
  	}
  	
  	
  	public void register(String name, String password) {
  		userSource.put(name, password);
  	}
  
  	public boolean userExists(User user) {
  		String passwordFromSource = userSource.get(user.getName());
  		if(passwordFromSource != null) {
  			return user.getPassword().equals(passwordFromSource);
  		}else
  			return false;
  	}
  

  }
