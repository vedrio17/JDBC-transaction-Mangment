import java.util.Scanner;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class preperdprac {
    public static void main(String[] args)throws ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="Pass@1234";
        String sql="Select * from employees where name=?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded sucessfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con=DriverManager.getConnection(url,username,password);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"kunal");
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt( "eid");
                String name = rs.getString(  "name");
                String job_title = rs.getString(  "job_title");
                double salary = rs.getDouble (  "salary");
                System.out.println("ID: "+id);
                System.out.println("NAME: "+name);
                System.out.println("JOB TITLE: "+job_title);
                System.out.println("SALARY: "+salary);
            }
            rs.close();
            con.close();
            System.out.println();
            System.out.println("Connection closed sucessfully");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
