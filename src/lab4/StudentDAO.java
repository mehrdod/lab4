package lab4;
import java.util.ArrayList;
import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
import java.sql.*;

import lab4.Student;






public class StudentDAO {
	   private static Connection conn = null;
	   public StudentDAO (){
		   try {
			   Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3310/student","root", "root");
		 
			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }   
	  
	   /* Method to CREATE an Student in the database */
	   public Integer addStudent(String fname, String lname, int year){
	      PreparedStatement stmt = null;
	      try {
	    	 String sql = "INSERT INTO student(firstName, lastName, year) VALUES(?,?,?)";
	    	 stmt = conn.prepareStatement(sql);
	    	 stmt.setString(1, fname);
	    	 stmt.setString(2, lname);
	    	 stmt.setInt(3, year);
	    	 stmt.executeUpdate();
		     
	    	 sql = "select id from student where firstName = ? and lastName=? and year=?";
	    	 stmt = conn.prepareStatement(sql);
	    	 stmt.setString(1, fname);
	    	 stmt.setString(2, lname);
	    	 stmt.setInt(3, year);
	    	 ResultSet rs = stmt.executeQuery();
	    	 rs.next();
	    	 System.out.println(rs.getInt("id"));
	    	 return rs.getInt("id");
	    	
		     } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return 0;	      
	      
	   }
	   public ArrayList<Student> listStudents(){
		  Statement stmt = null;
		  ArrayList<Student> res = new ArrayList<Student>();
			 
		     try {
				stmt = conn.createStatement();
				String sql = "SELECT * FROM student";
			      ResultSet rs = stmt.executeQuery(sql);
			       
			      while(rs.next()){
			         //Retrieve by column name
			         int id  = rs.getInt("id");
			         int age = rs.getInt("year");
			         String first = rs.getString("firstName");
			         String last = rs.getString("lastName");
			         Student tmp = new Student();
			         tmp.setFirstName(first);
			         tmp.setLastName(last);
			         tmp.setYear(age);
			         tmp.setId(id);
			         
			         res.add(tmp);
			      
			         System.out.print("ID: " + id);
			         System.out.print(", Age: " + age);
			         System.out.print(", First: " + first);
			         System.out.println(", Last: " + last);
			      }
			      rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		      return res;
		  
	  }
	  public void update(Integer StudentID,  String fname, String lname, int year ){
		  PreparedStatement stmt = null;
	      try {
	    	 String sql = "UPDATE student set firstName = ?, lastName = ?, year = ? " 
	    			 + "WHERE id = ?";
	    	 stmt = conn.prepareStatement(sql);
	    	 stmt.setString(1, fname);
	    	 stmt.setString(2, lname);
	    	 stmt.setInt(3, year);
	     	 stmt.setInt(4, (int)StudentID);
	    	
		      stmt.executeUpdate();
		     } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	  public void deleteStudent(Integer StudentID){
		  PreparedStatement stmt = null;
		  try {
		    	 String sql = "DELETE from student WHERE id = ?";
		    	 stmt = conn.prepareStatement(sql);
		    	 stmt.setInt(1, (int)StudentID);	    	
			      stmt.executeUpdate();
			     } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
	  }
	   
	  
}