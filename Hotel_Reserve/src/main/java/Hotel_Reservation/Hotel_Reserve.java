package Hotel_Reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Hotel_Reserve 
{

		 
		 public static void main(String[] argv) throws SQLException 
		 {
			 Scanner sc=new Scanner(System.in);
			 boolean st=false;
		        Hotel_Reserve obj = new Hotel_Reserve();
		        
		        
		        while(true)
		        {
		        	st=true;
		        	System.out.println("Press 1 to add data");
			        System.out.println("Press 2 to show data");
			        System.out.println("Press 1 to delete data");
			        System.out.println("Enter your choice:-");
			        int choice=sc.nextInt();
		        	switch(choice)
		        	{
		        	 case 1:
		        		 obj.addInfo();
		        		 break;
		        		 
		        	 case 2:
		        		 obj.showInfo();
		        		 break;
		        		 
		        	 case 3:
		        		 obj.deleteInfo();
		        		 break;
		        	 default:
		        		 System.out.println("Wrong Input");
		        		 break;
		        	}
		        	
		        }
		        
		 }
		 public void addInfo() throws SQLException 
		 {
		        	Scanner sc=new Scanner(System.in);
		            
		            System.out.println("Enter guest name:-");
		            String name=sc.nextLine();
		            System.out.println("Enter aadhar no.:-");
		            Long aadhar=sc.nextLong();
		            System.out.println("Enter guest's age:-");
		            int age=sc.nextInt();
		            
		            String s1="INSERT INTO DATA" +
					        "  (cust_nm,aadhar_no,age) VALUES " +
					        " (?, ?, ?);";
		            try (Connection con = DriverManager
		                .getConnection("jdbc:mysql://localhost:3306/test","root","");
		            		
		            		PreparedStatement prsmt = con.prepareStatement(s1)) 
		            {
		                    
		                  prsmt.setString(1, name);
		                  prsmt.setLong(2,aadhar);
		                  prsmt.setInt(3, age);
		                  
		                prsmt.executeUpdate();
		            }
		            catch (SQLException e) {
		              System.out.println(e);
		            }


		    }
		 
		 public void showInfo() throws SQLException
		 {
			 String s2="SELECT * FROM data WHERE cust_nm=?";
			 Scanner sc=new Scanner(System.in);
			 System.out.println("Enter the customer name whose data you want to show:-");
			 String name1=sc.nextLine();
			 
			try(Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/test","root","");
					PreparedStatement prsmt = con.prepareStatement(s2))
			   
			{
				
				prsmt.setString(1,name1);
				ResultSet rs=prsmt.executeQuery();
				while(rs.next()) {
					String name2=rs.getString("cust_nm");
					Long aadhar_no2=rs.getLong("aadhar_no");
					int age2=rs.getInt("age");
					
					System.out.println("Data is:-"+name2+"\t"+aadhar_no2+"\t"+age2);
				}
			}
			catch (SQLException e) {
	              System.out.println(e);
	        }	
			 
		 }
		 
		 public void deleteInfo() throws SQLException
		 {
			 String s3="DELETE FROM data WHERE cust_nm=?";
			 Scanner sc=new Scanner(System.in);
			 System.out.println("Enter the name of guest whose data you want to delete:-");
			 String name1=sc.next();
			 
			try(Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/test","root","");
					PreparedStatement prsmt = con.prepareStatement(s3))
			{
				prsmt.setString(1, name1);
				prsmt.executeUpdate();
			}
			catch (SQLException e) {
	              System.out.println(e);
	        }	
			 
		 }

}

