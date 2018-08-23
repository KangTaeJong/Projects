import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost/sys?useSSL=false&serverTimezone=Asia/Seoul";
            String id = "root";
            String password = "123123";

            connection = DriverManager.getConnection(url, id, password);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SHOW TABLES");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString(1);
                System.out.println(title);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        try{connection.close();}catch (Exception e){}
        try{preparedStatement.close();}catch (Exception e){}
        try{resultSet.close();}catch (Exception e){}
    }
}
