import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
public class imageinsertionJDBC {
    public static void main(String[] args)throws ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="Pass@1234";
        String image_path="C:\\Users\\HP\\OneDrive\\Pictures\\Screenshots\\Screenshot 2024-02-26 194101.png";
        String query="INSERT INTO image_table(image_data)VALUES(?)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded sucessfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con=DriverManager.getConnection(url,username,password);
            System.out.println("Connection Estabulish sucessfully");
            FileInputStream fileInputStream=new FileInputStream(image_path);
            byte[]imageData=new byte[fileInputStream.available()];
            fileInputStream.read(imageData);
            PreparedStatement preparedStatement= con.prepareStatement(query);
            preparedStatement.setBytes(1,imageData);
            int affected_rows=preparedStatement.executeUpdate();
            if (affected_rows>0){
                System.out.println("Image Inserted Sucessfully !!!");
            }else{
                System.out.println("Image not Inserted");
            }



        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
