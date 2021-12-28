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
        Connection c = DataBaseHandlingClass.StartConnectionWithDB();
        User user = DataBaseHandlingClass.LogInUser(c,"tnowak", "qwerty1");
        System.out.println(null == user);
        List<User> list = DataBaseHandlingClass.SearchForPatientsOfOrthodontist(c, user);
        List<Visit> list1 = DataBaseHandlingClass.SearchForVisitsOfPatient(c, list.get(0));

        User user1 = DataBaseHandlingClass.LogInUser(c,"jkowal", "qwerty");
        List<User> list2 = DataBaseHandlingClass.SearchForAllOrthodontists(c, user1);
        List<User> list3 = DataBaseHandlingClass.SearchForAllUsers(c, user1);
        List<User> list4 = DataBaseHandlingClass.SearchForAllPatients(c, user1);

        System.out.println("ok");
    }
}
