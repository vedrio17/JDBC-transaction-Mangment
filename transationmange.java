import java.sql.*;
public class transationmange {
    public static void main(String[] args)throws ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="Pass@1234";
        String withdrawQuery="UPDATE account SET  Balence = Balence - ? where account_number= ?";
        String depositeQuery="UPDATE account SET  Balence = Balence + ? where account_number= ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded sucessfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con=DriverManager.getConnection(url,username,password);
            System.out.println("Connected Sucessfully");
            con.setAutoCommit(false);
            try {
                PreparedStatement withdrawdtatement = con.prepareStatement(withdrawQuery);
                PreparedStatement depositestatement = con.prepareStatement(depositeQuery);
                withdrawdtatement.setDouble(1, 500.00);
                withdrawdtatement.setString(2, "account1");
                depositestatement.setDouble(1, 500.00);
                depositestatement.setString(2, "account2");
                int rowaffectedwithdrawl=withdrawdtatement.executeUpdate();
                int rowsaffecteddeposite=depositestatement.executeUpdate();
                if (rowaffectedwithdrawl>0 && rowsaffecteddeposite>0){
                    con.commit();
                    System.out.println("Transation Sucessfull");
                }else {
                    con.rollback();
                    System.out.println("Transation failed");
                }
            }catch (SQLException e){

                System.out.println(e.getMessage());
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }
}
