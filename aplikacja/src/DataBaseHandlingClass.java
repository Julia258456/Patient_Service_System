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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + login + "\" AND hasloUzytkownika = \"" + password + "\"");
            if(resultSet.isBeforeFirst()) {
                //System.out.println("result set is not empty");
                while (resultSet.next()) {
                    if (resultSet.getString("hasloUzytkownika").equals(password)) {
                        //System.out.println(resultSet.getString("idUzytkownika"));
                        return new User(resultSet.getInt("idUzytkownika"),
                                resultSet.getString("nazwaUzytkownika"),
                                resultSet.getString("hasloUzytkownika"),
                                resultSet.getString("imieUzytkownika"),
                                resultSet.getString("nazwiskoUzytkownika"),
                                resultSet.getString("numerTelefonuUzytkownika"),
                                resultSet.getString("adresUzytkownika"),
                                resultSet.getString("emailUzytkownika"),
                                resultSet.getInt("poziomUprawnien"));
                    }
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
     * @param orthodontist User object - the orthodontist whose patients the method is supposed to return
     * @return List<User> object (if method was successful) or null (if user provided incorrect input/connection with database failed)
     */
    public static List<User> SearchForPatientsOfOrthodontist(Connection connection, User orthodontist){
        if (orthodontist.getUserPermissionsLevel() == 0){
            return null;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM pacjenci WHERE user_id_ortodonty = " + orthodontist.getUserId());
            List<User> userList = new ArrayList();
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1;
            while (resultSet.next()) {
                resultSet1 = statement1.executeQuery("SELECT * FROM users WHERE idUzytkownika = " + resultSet.getInt("user_id_pacjenta"));
                if( resultSet1.next()) {
                    User user = new User(resultSet1.getInt("idUzytkownika"),
                            resultSet1.getString("nazwaUzytkownika"),
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
     * The method which allows the administrator to get the list of all orthodontists from the database.
     * @param connection Connection class object necessary to access the database
     * @param admin User object - the administrator's data
     * @return List<User> object (if method was successful) or null (if user provided incorrect input/connection with database failed)
     */
    public static List<User> SearchForAllOrthodontists(Connection connection, User admin){
        if (admin.getUserPermissionsLevel() != 2){
            return null;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE poziomUprawnien = 1 ");
            List<User> userList = new ArrayList();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("idUzytkownika"),
                        resultSet.getString("nazwaUzytkownika"),
                        resultSet.getString("hasloUzytkownika"),
                        resultSet.getString("imieUzytkownika"),
                        resultSet.getString("nazwiskoUzytkownika"),
                        resultSet.getString("numerTelefonuUzytkownika"),
                        resultSet.getString("adresUzytkownika"),
                        resultSet.getString("emailUzytkownika"),
                        resultSet.getInt("poziomUprawnien"));
                userList.add(user);
            }
            return userList;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The method which allows the administrator to get the list of all patients from the database.
     * @param connection Connection class object necessary to access the database
     * @param admin User object - the administrator's data
     * @return List<User> object (if method was successful) or null (if user provided incorrect input/connection with database failed)
     */
    public static List<User> SearchForAllPatients(Connection connection, User admin){
        if (admin.getUserPermissionsLevel() != 2){
            return null;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE poziomUprawnien = 0 ");
            List<User> userList = new ArrayList();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("idUzytkownika"),
                        resultSet.getString("nazwaUzytkownika"),
                        resultSet.getString("hasloUzytkownika"),
                        resultSet.getString("imieUzytkownika"),
                        resultSet.getString("nazwiskoUzytkownika"),
                        resultSet.getString("numerTelefonuUzytkownika"),
                        resultSet.getString("adresUzytkownika"),
                        resultSet.getString("emailUzytkownika"),
                        resultSet.getInt("poziomUprawnien"));
                userList.add(user);
            }
            return userList;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The method which allows the administrator to get the list of all users from the database.
     * @param connection Connection class object necessary to access the database
     * @param admin User object - the administrator's data
     * @return List<User> object (if method was successful) or null (if user provided incorrect input/connection with database failed)
     */
    public static List<User> SearchForAllUsers(Connection connection, User admin){
        if (admin.getUserPermissionsLevel() != 2){
            return null;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users ");
            List<User> userList = new ArrayList();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("idUzytkownika"),
                        resultSet.getString("nazwaUzytkownika"),
                        resultSet.getString("hasloUzytkownika"),
                        resultSet.getString("imieUzytkownika"),
                        resultSet.getString("nazwiskoUzytkownika"),
                        resultSet.getString("numerTelefonuUzytkownika"),
                        resultSet.getString("adresUzytkownika"),
                        resultSet.getString("emailUzytkownika"),
                        resultSet.getInt("poziomUprawnien"));
                userList.add(user);
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

    /**
     * The method which allows the user to change his own data in the database.
     * @param connection Connection class object necessary to access the database
     * @param userOldData User object - the old data which should be changed
     * @param userNewData User object - the new data which should be saved in the database
     * @return User object (if method was successful) or null (if user provided incorrect input/connection with database failed)
     */
    public static User EditUserInfoInDB(Connection connection, User userOldData, User userNewData){
        if ( userOldData.getUserId() != userNewData.getUserId()){
            return null;
        }
        try {
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            statement.executeUpdate("UPDATE users SET imieUzytkownika = \"" + userNewData.getUserName() + "\", nazwiskoUzytkownika = \"" + userNewData.getUserSurname() + "\", numerTelefonuUzytkownika = \"" + userNewData.getUserTelephoneNumber() + "\", adresUzytkownika = \"" + userNewData.getUserAddress() + "\", emailUzytkownika = \"" + userNewData.getUserEmail() + "\" WHERE idUzytkownika = " + userNewData.getUserId() + ";");
            return userNewData;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The method which allows the administrator to add new orthodontist/administrator to the database.
     * @param connection Connection class object necessary to access the database
     * @param admin User object - the administrator who is adding new orthodontist/administrator  to the database
     * @param newUser User object - the orthodontist/administrator whose data the method is supposed to insert into the database
     * @return boolean true (if method was successful) or false (if user provided incorrect input/connection with database failed)
     */
    public static boolean AddNewUserToDB(Connection connection, User admin, User newUser){
        if ((admin.getUserPermissionsLevel() == 2) && (newUser.getUserPermissionsLevel() == 1)) {
            try {
                Statement statement = connection.createStatement();
                Statement statement1 = connection.createStatement();
                Statement statement2 = connection.createStatement();
                Statement statement3 = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + newUser.getUserLogin() + "\";");
                if (resultSet.next()) {
                    //System.out.println("Result set is not empty");
                    return false;
                } else {
                    //System.out.println("result set is empty");
                    statement2.executeUpdate("INSERT INTO users (nazwaUzytkownika, hasloUzytkownika, imieUzytkownika, "
                            + "nazwiskoUzytkownika, numerTelefonuUzytkownika, adresUzytkownika, emailUzytkownika, "
                            + "poziomUprawnien) " + "VALUES (\"" + newUser.getUserLogin() + "\", \""
                            + newUser.getUserPassword() + "\",  \"" + newUser.getUserName() + "\",\""
                            + newUser.getUserSurname() + "\", \"" + newUser.getUserTelephoneNumber()
                            + "\", \"" + newUser.getUserAddress() + "\", \"" + newUser.getUserEmail()
                            + "\", " + newUser.getUserPermissionsLevel() + ");");
                    ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + newUser.getUserLogin() + "\"");
                    if (resultSet1.next()) {
                        statement3.executeUpdate("INSERT INTO ortodonci (user_id) VALUES (" + resultSet1.getInt("idUzytkownika") + ");");
                    }
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        if ((admin.getUserPermissionsLevel() == 2) && (newUser.getUserPermissionsLevel() == 2)) {
            try {
                Statement statement = connection.createStatement();
                Statement statement1 = connection.createStatement();
                Statement statement2 = connection.createStatement();
                Statement statement3 = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + newUser.getUserLogin() + "\";");
                if (resultSet.next()) {
                    //System.out.println("Result set is not empty");
                    return false;
                } else {
                    //System.out.println("result set is empty");
                    statement2.executeUpdate("INSERT INTO users (nazwaUzytkownika, hasloUzytkownika, imieUzytkownika, "
                            + "nazwiskoUzytkownika, numerTelefonuUzytkownika, adresUzytkownika, emailUzytkownika, "
                            + "poziomUprawnien) " + "VALUES (\"" + newUser.getUserLogin() + "\", \""
                            + newUser.getUserPassword() + "\",  \"" + newUser.getUserName() + "\",\""
                            + newUser.getUserSurname() + "\", \"" + newUser.getUserTelephoneNumber()
                            + "\", \"" + newUser.getUserAddress() + "\", \"" + newUser.getUserEmail()
                            + "\", " + newUser.getUserPermissionsLevel() + ");");
                    ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + newUser.getUserLogin() + "\"");
                    if (resultSet1.next()) {
                        statement3.executeUpdate("INSERT INTO administratorzy (user_id) VALUES (" + resultSet1.getInt("idUzytkownika") + ");");
                    }
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        return false;
    }

    /**
     * The method which allows the administrator to add new patient to the database.
     * @param connection Connection class object necessary to access the database
     * @param admin User object - the administrator who is adding new patient to the database
     * @param patient User object - the patient whose data the method is supposed to insert into the database
     * @param orthodontist User object - the orthodontist
     * @return boolean true (if method was successful) or false (if user provided incorrect input/connection with database failed)
     */
    public static boolean AddNewUserToDB(Connection connection, User admin, User patient, User orthodontist){
        if ((admin.getUserPermissionsLevel() != 2) || (patient.getUserPermissionsLevel() != 0) || (orthodontist.getUserPermissionsLevel() != 1)){
            return false;
        }
        try {

            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();
            Statement statement4 = connection.createStatement();
            int orthodontistUserId = 0;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + patient.getUserLogin() + "\";");
            if(resultSet.next()){
                //System.out.println("Result set is not empty");
                return false;
            } else {
                //System.out.println("result set is empty");
                statement3.executeUpdate("INSERT INTO users (nazwaUzytkownika, hasloUzytkownika, imieUzytkownika, "
                        + "nazwiskoUzytkownika, numerTelefonuUzytkownika, adresUzytkownika, emailUzytkownika, "
                        + "poziomUprawnien) " + "VALUES (\"" + patient.getUserLogin() + "\", \""
                        + patient.getUserPassword() + "\",  \"" + patient.getUserName() + "\",\""
                        + patient.getUserSurname() + "\", \"" + patient.getUserTelephoneNumber()
                        + "\", \"" + patient.getUserAddress() + "\", \"" + patient.getUserEmail()
                        + "\", " + patient.getUserPermissionsLevel() + ");");
                ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + patient.getUserLogin() + "\"");
                ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM ortodonci WHERE user_id = " + orthodontist.getUserId());
                while(resultSet2.next()) {
                    orthodontistUserId = resultSet2.getInt("idOrtodonty");
                }
                if(resultSet1.next()) {
                    statement4.executeUpdate("INSERT INTO pacjenci (user_id_pacjenta, user_id_ortodonty, ortodonta_id) "
                            + "VALUES (" + resultSet1.getInt("idUzytkownika") + ", " + orthodontist.getUserId() + ", "
                            + orthodontistUserId + ");");
                }
                return true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * The method which allows the administrator to add new patient to the database.
     * @param connection Connection class object necessary to access the database
     * @param admin User object - the administrator who is adding new patient to the database
     * @param patient User object - the patient whose data the method is supposed to insert into the database
     * @param orthodontist User object - the orthodontist
     * @return boolean true (if method was successful) or false (if user provided incorrect input/connection with database failed)
     */
    public static boolean AddNewPatientToDB(Connection connection, User admin, User patient, User orthodontist){
        if ((admin.getUserPermissionsLevel() != 2) || (patient.getUserPermissionsLevel() != 0) || (orthodontist.getUserPermissionsLevel() != 1)){
            return false;
        }
        try {

            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();
            Statement statement4 = connection.createStatement();
            int orthodontistUserId = 0;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + patient.getUserLogin() + "\";");
            if(resultSet.next()){
                //System.out.println("Result set is not empty");
                return false;
            } else {
                //System.out.println("result set is empty");
                statement3.executeUpdate("INSERT INTO users (nazwaUzytkownika, hasloUzytkownika, imieUzytkownika, "
                        + "nazwiskoUzytkownika, numerTelefonuUzytkownika, adresUzytkownika, emailUzytkownika, "
                        + "poziomUprawnien) " + "VALUES (\"" + patient.getUserLogin() + "\", \""
                        + patient.getUserPassword() + "\",  \"" + patient.getUserName() + "\",\""
                        + patient.getUserSurname() + "\", \"" + patient.getUserTelephoneNumber()
                        + "\", \"" + patient.getUserAddress() + "\", \"" + patient.getUserEmail()
                        + "\", " + patient.getUserPermissionsLevel() + ");");
                ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + patient.getUserLogin() + "\"");
                ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM ortodonci WHERE user_id = " + orthodontist.getUserId());
                while(resultSet2.next()) {
                    orthodontistUserId = resultSet2.getInt("idOrtodonty");
                }
                if(resultSet1.next()) {
                    statement4.executeUpdate("INSERT INTO pacjenci (user_id_pacjenta, user_id_ortodonty, ortodonta_id) "
                            + "VALUES (" + resultSet1.getInt("idUzytkownika") + ", " + orthodontist.getUserId() + ", "
                            + orthodontistUserId + ");");
                }
                return true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * The method which allows the administrator to add new orthodontist to the database.
     * @param connection Connection class object necessary to access the database
     * @param admin User object - the administrator who is adding new orthodontist to the database
     * @param orthodontist User object - the orthodontist whose data the method is supposed to insert into the database
     * @return boolean true (if method was successful) or false (if user provided incorrect input/connection with database failed)
     */
    public static boolean AddNewOrthodontistToDB(Connection connection, User admin, User orthodontist){
        if ((admin.getUserPermissionsLevel() != 2) || (orthodontist.getUserPermissionsLevel() != 1)){
            return false;
        }
        try {
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + orthodontist.getUserLogin() + "\";");
            if(resultSet.next()){
                //System.out.println("Result set is not empty");
                return false;
            } else {
                //System.out.println("result set is empty");
                statement2.executeUpdate("INSERT INTO users (nazwaUzytkownika, hasloUzytkownika, imieUzytkownika, "
                        + "nazwiskoUzytkownika, numerTelefonuUzytkownika, adresUzytkownika, emailUzytkownika, "
                        + "poziomUprawnien) " + "VALUES (\"" + orthodontist.getUserLogin() + "\", \""
                        + orthodontist.getUserPassword() + "\",  \"" + orthodontist.getUserName() + "\",\""
                        + orthodontist.getUserSurname() + "\", \"" + orthodontist.getUserTelephoneNumber()
                        + "\", \"" + orthodontist.getUserAddress() + "\", \"" + orthodontist.getUserEmail()
                        + "\", " + orthodontist.getUserPermissionsLevel() + ");");
                ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + orthodontist.getUserLogin() + "\"");
                if(resultSet1.next()) {
                    statement3.executeUpdate("INSERT INTO ortodonci (user_id) VALUES (" + resultSet1.getInt("idUzytkownika") + ");");
                }
                return true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * The method which allows the administrator to add new administrator to the database.
     * @param connection Connection class object necessary to access the database
     * @param admin User object - the administrator who is adding new administrator to the database
     * @param newAdmin User object - the new administrator whose data the method is supposed to insert into the database
     * @return boolean true (if method was successful) or false (if user provided incorrect input/connection with database failed)
     */
    public static boolean AddNewAdministratorToDB(Connection connection, User admin, User newAdmin){
        if ((admin.getUserPermissionsLevel() != 2) || (newAdmin.getUserPermissionsLevel() != 2)){
            return false;
        }
        try {
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + newAdmin.getUserLogin() + "\";");
            if(resultSet.next()){
                //System.out.println("Result set is not empty");
                return false;
            } else {
                //System.out.println("result set is empty");
                statement2.executeUpdate("INSERT INTO users (nazwaUzytkownika, hasloUzytkownika, imieUzytkownika, "
                        + "nazwiskoUzytkownika, numerTelefonuUzytkownika, adresUzytkownika, emailUzytkownika, "
                        + "poziomUprawnien) " + "VALUES (\"" + newAdmin.getUserLogin() + "\", \""
                        + newAdmin.getUserPassword() + "\",  \"" + newAdmin.getUserName() + "\",\""
                        + newAdmin.getUserSurname() + "\", \"" + newAdmin.getUserTelephoneNumber()
                        + "\", \"" + newAdmin.getUserAddress() + "\", \"" + newAdmin.getUserEmail()
                        + "\", " + newAdmin.getUserPermissionsLevel() + ");");
                ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM users WHERE nazwaUzytkownika = \"" + newAdmin.getUserLogin() + "\"");
                if(resultSet1.next()) {
                    statement3.executeUpdate("INSERT INTO administratorzy (user_id) VALUES (" + resultSet1.getInt("idUzytkownika") + ");");
                }
                return true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * The method which allows the administrator to remove patient's data from the database.
     * @param connection Connection class object necessary to access the database
     * @param admin User object - the administrator who is removing the patient from the database
     * @param patient User object - the patient whose data is being removed from the database
     * @return boolean true (if method was successful) or false (if user provided incorrect input/connection with database failed)
     */
    public static boolean RemovePatientFromDB(Connection connection, User admin, User patient){
        if ((admin.getUserPermissionsLevel() != 2) || (patient.getUserPermissionsLevel() != 0)){
            return false;
        }
        try {
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            statement.executeUpdate("DELETE FROM odbyte_wizyty WHERE user_id_pacjenta = " + patient.getUserId());
            statement1.executeUpdate("DELETE FROM pacjenci WHERE user_id_pacjenta = " + patient.getUserId());
            statement2.executeUpdate("DELETE FROM users WHERE idUzytkownika = " + patient.getUserId());
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * The method which allows the administrator to remove orthodontist's data from the database.
     * @param connection Connection class object necessary to access the database
     * @param admin User object - the administrator who is removing the orthodontist from the database
     * @param orthodontistToRemove User object - the orthodontist whose data is being removed from the database
     * @param orthodontistToPassPatients User object - the orthodontist who will treat the patients of removed orthodontist from now on
     * @return boolean true (if method was successful) or false (if user provided incorrect input/connection with database failed)
     */
    public static boolean RemoveOrthodontistFromDB(Connection connection, User admin, User orthodontistToRemove, User orthodontistToPassPatients){
        if ((admin.getUserPermissionsLevel() != 2) || (orthodontistToRemove.getUserPermissionsLevel() != 1) || (orthodontistToPassPatients.getUserPermissionsLevel() != 1)){
            return false;
        }
        try {
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ortodonci WHERE user_id = " + orthodontistToPassPatients.getUserId() + ";");
            while (resultSet.next()) {
                statement1.executeUpdate("UPDATE pacjenci SET ortodonta_id = " + resultSet.getInt("idOrtodonty")
                        + ", user_id_ortodonty = " + orthodontistToPassPatients.getUserId() + " WHERE user_id_ortodonty = "
                        + orthodontistToRemove.getUserId() + ";");
                statement2.executeUpdate("DELETE FROM ortodonci WHERE user_id = " + orthodontistToRemove.getUserId() + ";");
                statement2.executeUpdate("DELETE FROM users WHERE idUzytkownika = " + orthodontistToRemove.getUserId() + ";");
                return true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * The method which allows the administrator to remove other administrator's data from the database.
     * @param connection Connection class object necessary to access the database
     * @param admin User object - the administrator who is removing the other administrator from the database
     * @param adminToRemove User object - the other administrator whose data is being removed from the database
     * @return boolean true (if method was successful) or false (if user provided incorrect input/connection with database failed)
     */
    public static boolean RemoveAdministratorFromDB(Connection connection, User admin, User adminToRemove){
        if ((admin.getUserPermissionsLevel() != 2) || (adminToRemove.getUserPermissionsLevel() != 2)){
            return false;
        }
        try {
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            statement.executeUpdate("DELETE FROM administratorzy WHERE user_id = " + adminToRemove.getUserId());
            statement1.executeUpdate("DELETE FROM users WHERE idUzytkownika = " + adminToRemove.getUserId());
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
