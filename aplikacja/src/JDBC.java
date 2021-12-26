import java.sql.Connection;
import java.util.List;

public class JDBC {
    public static void main(String[] args){
      /*try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_test?serverTimezone=UTC", "root", "MY*sql22");
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery("select * from new_table");
          while (resultSet.next()){
              System.out.println(resultSet.getString("name"));
          }
      } catch(Exception e){
          e.printStackTrace();
      }*/
        Connection c = ObslugaBazyDanych.RozpocznijPolaczenieDB();
        User user = ObslugaBazyDanych.ZalogujUzytkownika(c,"tnowak", "qwerty1");
        System.out.println(null == user);
        List<User> list = ObslugaBazyDanych.WyszukajPacjentowOrtodonty(c, user);

        System.out.println("ok");
    }
}
