import java.io.*;
import java.sql.*;

public class imageretriveJDBC {
    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "Pass@1234";
        String folder_path = "C:\\Users\\HP\\OneDrive\\Pictures\\Screenshots\\";
        String query = "SELECT image_data from image_table where image_id=(?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded sucessfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Estabulish sucessfully");
            PreparedStatement preparedStatement= con.prepareStatement(query);
            preparedStatement.setInt(1,1);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                byte[]image_data=resultSet.getBytes("image_data");
                String image_path=folder_path+"extracted_imageJDBC.jpg";
                OutputStream outputStream=new FileOutputStream(image_path);
                outputStream.write(image_data);
            }else {
                System.out.println("Image not found");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
