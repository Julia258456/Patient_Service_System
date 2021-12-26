import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ObslugaBazyDanych {

    /**
     * The method which establishes the connection with the database.
     * @return Connection object (if connection was successful) or null (if connection was unsuccessful)
     */
    public static Connection RozpocznijPolaczenieDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/system_obslugi?serverTimezone=UTC", "root", "MY*sql22");
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The method which allows the user to log in the system.
     * @param connection Connection class object necessary to access the database
     * @param login username - String provided by the user
     * @param haslo password - String provided by the user
     * @return User object (if method was successful) or null (if user provided incorrect input/connection with database failed)
     */
    public static User ZalogujUzytkownika(Connection connection, String login, String haslo){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + login + "\"");
            while (resultSet.next()) {
                if (resultSet.getString("hasloUzytkownika").equals(haslo)){
                    System.out.println(resultSet.getString("idUzytkownika"));
                    User user = new User(resultSet.getInt("idUzytkownika"), resultSet.getString("nazwaUzytkownika"),
                            resultSet.getString("hasloUzytkownika"), resultSet.getString("imieUzytkownika"),
                            resultSet.getString("nazwiskoUzytkownika"), resultSet.getString("numerTelefonuUzytkownika"),
                            resultSet.getString("adresUzytkownika"), resultSet.getString("emailUzytkownika"), resultSet.getInt("poziomUprawnien"));
                    return user;
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The method which allows the orthodontist to get the list of his patients from the database.
     * @param connection Connection class object necessary to access the database
     * @param ortodonta User object - the orthodontist whose patients the method is supposed to return
     * @return List<User> object (if method was successful) or null (if user provided incorrect input/connection with database failed)
     */
    public static List<User> WyszukajPacjentowOrtodonty(Connection connection, User ortodonta){
        if (ortodonta.getPoziomUprawnien() == 0){
            return null;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM pacjenci WHERE user_id_ortodonty = " + ortodonta.getIdUzytkownika());
            List<User> userList = new ArrayList();
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1;
            while (resultSet.next()) {
                resultSet1 = statement1.executeQuery("SELECT * FROM users WHERE idUzytkownika = " + resultSet.getInt("user_id_pacjenta"));
                if( resultSet1.next()) {
                    User user = new User(resultSet1.getInt("idUzytkownika"), resultSet1.getString("nazwaUzytkownika"),
                            resultSet1.getString("hasloUzytkownika"), resultSet1.getString("imieUzytkownika"),
                            resultSet1.getString("nazwiskoUzytkownika"), resultSet1.getString("numerTelefonuUzytkownika"),
                            resultSet1.getString("adresUzytkownika"), resultSet1.getString("emailUzytkownika"), resultSet1.getInt("poziomUprawnien"));
                    userList.add(user);
                }
            }
            return userList;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
