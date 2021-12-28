import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHandlingClass {

    /**
     * The method which establishes the connection with the database.
     * @return Connection object (if connection was successful) or null (if connection was unsuccessful)
     */
    public static Connection StartConnectionWithDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://34.116.157.22/system_obslugi", "root", "MY*sql22");
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The method which allows the user to log in the system.
     * @param connection Connection class object necessary to access the database
     * @param login username - String provided by the user
     * @param password password - String provided by the user
     * @return User object (if method was successful) or null (if user provided incorrect input/connection with database failed)
     */
    public static User LogInUser(Connection connection, String login, String password){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + login + "\"");
            while (resultSet.next()) {
                if (resultSet.getString("hasloUzytkownika").equals(password)){
                    System.out.println(resultSet.getString("idUzytkownika"));
                    User user = new User(resultSet.getInt("idUzytkownika"),
                            resultSet.getString("nazwaUzytkownika"),
                            resultSet.getString("hasloUzytkownika"),
                            resultSet.getString("imieUzytkownika"),
                            resultSet.getString("nazwiskoUzytkownika"),
                            resultSet.getString("numerTelefonuUzytkownika"),
                            resultSet.getString("adresUzytkownika"),
                            resultSet.getString("emailUzytkownika"),
                            resultSet.getInt("poziomUprawnien"));
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
     * @param otrhodontist User object - the orthodontist whose patients the method is supposed to return
     * @return List<User> object (if method was successful) or null (if user provided incorrect input/connection with database failed)
     */
    public static List<User> SearchForPatientsOfOrthodontist(Connection connection, User otrhodontist){
        if (otrhodontist.getUserPermissionsLevel() == 0){
            return null;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM pacjenci WHERE user_id_ortodonty = " + otrhodontist.getUserId());
            List<User> userList = new ArrayList();
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1;
            while (resultSet.next()) {
                resultSet1 = statement1.executeQuery("SELECT * FROM users WHERE idUzytkownika = " + resultSet.getInt("user_id_pacjenta"));
                if( resultSet1.next()) {
                    User user = new User(resultSet1.getInt("idUzytkownika"),
                            resultSet1.getString("nazwaUzytkownika"),
                            resultSet1.getString("hasloUzytkownika"),
                            resultSet1.getString("imieUzytkownika"),
                            resultSet1.getString("nazwiskoUzytkownika"),
                            resultSet1.getString("numerTelefonuUzytkownika"),
                            resultSet1.getString("adresUzytkownika"),
                            resultSet1.getString("emailUzytkownika"),
                            resultSet1.getInt("poziomUprawnien"));
                    userList.add(user);
                }
            }
            return userList;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * The method which allows the user to get the list of his/his patient's visits from the database.
     * @param connection Connection class object necessary to access the database
     * @param patient User object - the patient whose visits the method is supposed to return
     * @return List<Wizyta> object (if method was successful) or null (if user provided incorrect input/connection with database failed)
     */
    public static List<Visit> SearchForVisitsOfPatient(Connection connection, User patient){
        if (patient.getUserPermissionsLevel() != 0){
            return null;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM odbyte_wizyty WHERE user_id_pacjenta = " + patient.getUserId());
            List<Visit> visitList = new ArrayList();
            while (resultSet.next()) {
                Visit visit = new Visit(resultSet.getInt("idWizyty"),
                        resultSet.getInt("pacjent"),
                        resultSet.getInt("ortodonta"),
                        resultSet.getInt("user_id_pacjenta"),
                        resultSet.getInt("user_id_ortodonty"),
                        resultSet.getTimestamp("dataWizyty"),
                        resultSet.getString("komentarz"));
                visitList.add(visit);
            }
            return visitList;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean AddNewPatientToDB(Connection connection, User patient, User orthodontist){
        // jeszcze nie działa
        if (patient.getUserPermissionsLevel() != 0){
            return false;
        } else if (orthodontist.getUserPermissionsLevel() != 1){
            return false;
        }
        try {

            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(1) FROM users WHERE nazwaUzytkownika = \"" + patient.getUserLogin() + "\";");
            if(!resultSet.isBeforeFirst()){
                System.out.println("result set is empty");
                statement.executeQuery("INSERT INTO users (nazwaUzytkownika, hasloUzytkownika, imieUzytkownika, "
                        + "nazwiskoUzytkownika, numerTelefonuUzytkownika, adresUzytkownika, emailUzytkownika, "
                        + "poziomUprawnien) " + "VALUES (\"" + patient.getUserLogin() + "\", \""
                        + patient.getUserPassword() + "\",  \"" + patient.getUserName() + "\",\""
                        + patient.getUserSurname() + "\", \"" + patient.getUserTelephoneNumber()
                        + "\", \"" + patient.getUserAddress() + "\", \"" + patient.getUserEmail()
                        + "\", " + patient.getUserPermissionsLevel() + ");");
                ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = " + patient.getUserLogin());
                if(resultSet1.next()) {
                    statement.executeQuery("INSERT INTO pacjenci (user_id_pacjenta, user_id_ortodonty, ortodonta_id) " + "VALUES (\"" + resultSet1.getString("idUzytkownika") + "\", \""
                            + orthodontist.getUserId() + "\");");
                }
                return true;
            }
            else {
                System.out.println("Result set is not empty");
                return false;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
