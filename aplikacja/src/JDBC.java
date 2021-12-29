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
        User usero = DataBaseHandlingClass.LogInUser(c,"tnowak", "qwerty1");
        System.out.println(null == usero);
        List<User> list = DataBaseHandlingClass.SearchForPatientsOfOrthodontist(c, usero);
        List<Visit> list1 = DataBaseHandlingClass.SearchForVisitsOfPatient(c, list.get(0));

        User user1a = DataBaseHandlingClass.LogInUser(c,"jkowal", "qwerty");
        List<User> list2 = DataBaseHandlingClass.SearchForAllOrthodontists(c, user1a);
        List<User> list3 = DataBaseHandlingClass.SearchForAllUsers(c, user1a);
        List<User> list4 = DataBaseHandlingClass.SearchForAllPatients(c, user1a);

        //User user2o = DataBaseHandlingClass.LogInUser(c,"jnowic", "qwerty2");
        //System.out.println(DataBaseHandlingClass.RemoveOrthodontistFromDB(c, user1a, user2o, usero));

        //User user2a = DataBaseHandlingClass.LogInUser(c,"aakowal", "qwerty3");
        //System.out.println(DataBaseHandlingClass.RemoveAdministratorFromDB(c, user1a, user2a));

        //User user2 = list4.get(0);
        //user2.setUserLogin("asdfg");
        //System.out.println(DataBaseHandlingClass.AddNewPatientToDB(c, user1, user2, user));
        //System.out.println(DataBaseHandlingClass.RemovePatientFromDB(c, user1, user2));

        //user.setUserLogin("qazwsx");
        //System.out.println(DataBaseHandlingClass.AddNewOrthodontistToDB(c, user1, user));
        //User user3 = DataBaseHandlingClass.LogInUser(c,"admin", "admin");
        //user3.setUserLogin("zaqxsw");
        //System.out.println(DataBaseHandlingClass.AddNewAdministratorToDB(c, user1, user3));

        System.out.println("ok");
    }
}
